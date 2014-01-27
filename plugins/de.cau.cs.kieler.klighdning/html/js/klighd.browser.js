(function (){

  // The zoom manager
  var zoomPanManager = null;
  
  /**
   * ----------------------------------------------------------------------------------------------------------
   * Utilities
   */
  function error(error) {
    $('#errors').html(error);
    $('#errors').show();
  }
  
  function hideErrors() {
    $('#errors').hide();
  }
  
  function sendJson(obj) {
    if (connection && connection.readyState === WebSocket.OPEN) {
      connection.send(JSON.stringify(obj));
    } else {
      error("Web Socket closed, please reconnect.");
    }
  }
  
  // register busy indicator for all ajax calls
  $.ajaxSetup({
    beforeSend : function() {
      $("#busy").show();
    },
    complete : function() {
      $("#busy").hide();
    }
  });
  
  /**
   * Call this when the websocket is disconnected or the page is reloaded.
   */
  function initState() {
  
    // show the join room menu
    $('#joinDiv').show();
    $('#leaveDiv').hide();
    hideErrors();
  
  }
  
  /**
   * ----------------------------------------------------------------------------------------------------------
   * The manual controls for zooming.
   */
  $('#zoomIn').click(function() {
    if (!zoomPanManager) {
      return;
    }
    zoomPanManager.each(function(k, m) {
      m.zpm.zoom(1.20);
    });
  });
  $('#zoomOut').click(function() {
    if (!zoomPanManager) {
      return;
    }
    zoomPanManager.each(function(k, m) {
      m.zpm.zoom(0.8);
    });
  });
  
  /**
   * ----------------------------------------------------------------------------------------------------------
   * The WebSocket Connection
   */
  
  // make sure a close event is triggerd on page close
  window.onbeforeunload = function() {
    websocket.onclose = function() {
    }; // disable onclose handler first
    websocket.close()
    initState();
  };
  
  // the actual connection
  var connection = null;
  
  var webSocketConnect = function() {
  
    // remove possibly old svg
    $('#viewport').html("");
    hideErrors();
  
    var host = window.location.host;
    connection = new WebSocket('ws://' + host + '/', "protocol.svgws");
  
    // -- Open
    connection.onopen = function() {
  
      // if a path and optional perma link is passed, open it
      var queryString = $klighdning.queryString;
      if (queryString.path) {
  
        connection.send(JSON.stringify({
          type : 'RESOURCE',
          path : queryString.path,
          viewport : queryString.transform,
          expand : queryString.perma
        }));
      }
    };
  
    // -- Log errors
    connection.onerror = function(error) {
      initState();
      error('WebSocket Error ' + error);
    };
  
    // -- Received message from server
    connection.onmessage = function(e) {
  
      // we receive a json obj
      var json = JSON.parse(e.data);
  
      if (json.type === "SVG") {
        // hide old errors
        hideErrors();
  
        // we have to unzip the data (gzip)
        var decrompressedSvg = $klighdning.decompress(json.data);
  
        // set the svg
        $('#viewport').html("");
        $('#viewport').html(decrompressedSvg);
  
        // attach zoom pan functionality
        zoomPanManager = $('#viewport').zoomPan();
  
        // attach a mousedown listener to handle expanding of hierarchical nodes
        var expandFun = function(d) {
          // get the id
          var hashcode = $(this).attr("data-id");
  
          // if starts with id
          if (hashcode) {
            // indicate busy
            $("#busy").show();
  
            // send expand toggle command
            sendJson({
              type : 'EXPAND',
              id : hashcode
            });
  
          }
        };
  
        // make hierarchical elements expandable
        $('.expandable').dblclick(expandFun);
        // for the mobile browser
        $('.expandable').bind("tap", expandFun);
  
        // translate events
        $('#group').change(function(e) {
          sendJson({
            type : 'TRANSFORM',
            transform : $(this).attr('transform')
          });
        });
  
      } else if (json.type === "TRANSFORM") {
        $('#group').attr("transform", json.transform);
  
      } else if (json.type === "PERMALINK") {
        // if we get a perma link, update
        if (json.perma) {
          $('#permaLink > a').prop("href", json.perma);
        } else {
          $('#permaLink > a').prop("href", "#");
        }
  
      } else if (json.type === "MOUSEPOS") {
        // only applicable if we are in a room
        if (inRoom) {
          // we need to add the offset to the viewport
          var offset = $('#viewport').offset();
          var posX = parseInt(json.posX) + offset.left;
          var posY = parseInt(json.posY) + offset.top;
          
          // move the cursor element
          $('.foreignCursor').css("top", posY + "px").css("left", posX + "px");
        }
        
      } else if (json.type === "MOUSEENTER") {
        $('.foreignCursor').show();
        
      } else if (json.type === "MOUSELEAVE") {
        $('.foreignCursor').hide();
         
      } else if (json.type === "ERROR") {
        var htmlified = json.data.replace(/\n/g, '<br />');
        error(htmlified);
      }
  
      // not busy anymore
      $("#busy").hide();
    };
  
    // -- Try to connect
    var wsTimeout = 1000;
    $('#messages').html("Connecting ...");
    setTimeout(function() {
      if (connection.readyState === WebSocket.OPEN) {
        $('#messages').html("Connected to " + window.location.host + ".");
        hideErrors();
      } else {
        $('#messages').html("Could not connect to web socket.");
        connection = null;
        // increase timeout for next try
        wsTimeout = wsTimeout * 2;
      }
    }, wsTimeout);
  
    
    $("#viewport").on('mousemove', function(e) {
      // only send position if we are in a room
      if (!inRoom) {
        return;
      }
      
      // calculate relative position
      var parentOffset = $(this).offset(); 
      var posX = e.pageX - parentOffset.left;
      var posY = e.pageY - parentOffset.top;
      
      sendJson({
        type : 'MOUSEPOS',
        posX : posX + "",
        posY : posY + ""
      });
    });
    
    $("#viewport").on('mouseleave', function(e) {
      sendJson({  
        type : 'MOUSELEAVE'
      });
    });
      
    $("#viewport").on('mouseenter', function(e) {
      sendJson({  
        type : 'MOUSEENTER'
      });
    });
    
    $("body").append('<div class="foreignCursor" style="display:none;">+</div>')
    
  };
  
  /**
   * ----------------------------------------------------------------------------------------------------------
   * Git Refresh
   */
  
  $('#gitRefresh').click(function() {
    $.ajax({
      type : 'PUT',
      url : '/refreshGit',
      success : function(res) {
        $('#messages').html(res.responseText);
        // refresh the repository view
        loadRepository();
      },
      error : function(res) {
        error(res.responseText);
      }
    });
  });
  
  /**
   * ----------------------------------------------------------------------------------------------------------
   * Rooms
   */
  var inRoom = false;
  
  function joined(name) {
    $('#currentRoom').html("You are in room " + name + ".");
    $('#joinDiv').hide();
    $('#leaveDiv').show();
    inRoom = true;
  }
  
  function left() {
    $('#joinDiv').show();
    $('#leaveDiv').hide();
    inRoom = false;
  }
  
  $('#join').click(function() {
    var room = $('#room').val();
    if (room === undefined || room === "") {
      return;
    }
  
    sendJson({
      type : "JOIN",
      room : room
    });
  
    joined(room);
  });
  
  $('#leave').click(function() {
  
    sendJson({
      type : "LEAVE"
    });
  
    left();
  });
  
  /**
   * ----------------------------------------------------------------------------------------------------------
   * Initial Setup
   */
  
  function loadRepository() {
  
    // create the tree
    $("#tree").dynatree({
      // animation
      fx : {
        height : "toggle",
        duration : 200
      },
      // Set focus to first child, when expanding or lazy-loading.
      autoFocus : false,
      // Init top level
      initAjax : {
        url : "/json/content/"
      },
      // load the diagram
      onActivate : function(node) {
        // ignore folders
        if (node.data.isFolder) {
          return;
        }
  
        // set busy
        $("#busy").show();
  
        // tell the websocket we need a new resource
        connection.send(JSON.stringify({
          type : 'RESOURCE',
          path : node.data.path
        }));
  
        // clear old diagram
        $('#viewport').html("");
  
        // clear the current transform
        if (zoomPanManager) {
          zoomPanManager.each(function(k, m) {
            m.zpm.reset();
          });
        }
      },
      // async load of the children
      onLazyRead : function(node) {
        node.appendAjax({
          url : "/json/content" + node.data.path
        });
      },
      // no debug output
      debugLevel : 0
    });
    
    // initial textual example
    $('#textualInput').html('knode n1 { \nsize: width = 100 height = 200\n }\nknode n2 {\n   size: width = 40 height = 20  \n klabel "hello" {  } \n kedge ( -> n1 )\n} ');
    // init editor
    var codeArea = CodeMirror.fromTextArea($('#textualInput')[0], {
      lineNumbers: true,
      viewportMargin: Infinity
    });
    
    // hack to show the editor
    // this is required as the editor's parent (the tab) is initially invisible
    $('#textualTab').click(function() {
      setTimeout(function() {
        codeArea.setSize($('.CodeMirror').width(), $('.CodeMirror').height());
        codeArea.refresh();
      }, 100);
    });
    
    var refreshModel = function() {
      connection.send(JSON.stringify({
        type: 'RESOURCE',
        text: codeArea.getValue(),
        textFormat: $('select#textualFormats').val()
      }));
    };
    
    // create a timer
    var timeout = 700;
    var timer = {};
    
    codeArea.on("change", function(inst, obj) {
      window.clearTimeout(timer);
      timer = setTimeout(refreshModel, timeout);
    });
  
    // init the textual fields
    $.ajax({
      type : 'GET',
      url : '/textualFormats',
      success : function(res) {
        // add the received formats as possibilities
        $.each(res, function(i, format) {
          if (format == "kgt") {
            $('#textualFormats').append('<option selected=selected value=' + format + '>' + format + '</option>');
          } else {
            $('#textualFormats').append('<option value=' + format + '>' + format + '</option>');          
          }
        });
        
        // add action upon convert click
        $('#textualConvert').click(function() {
          connection.send(JSON.stringify({
            type: 'RESOURCE',
            text: codeArea.getValue(),
            textFormat: $('select#textualFormats').val()
          }));
        })
      },
      error : function(res) {
        console.log("Error: " + res.responseText);
        console.log(res);
        error(res.responseText);
      }
    });
  }
  
  // executed
  $(function() {
    
    // init the splitter
    $('#main-splitter').splitter({
      anchorToWindow : true,
      minLeft : 100,
      sizeLeft : 250,
      minRight : 100,
    });
  
    // get the initial content
    loadRepository();
  
    // try to connect automatically
    webSocketConnect();
  
    // register reconnect button
    $('#connect').click(webSocketConnect);
  
  });

})();
