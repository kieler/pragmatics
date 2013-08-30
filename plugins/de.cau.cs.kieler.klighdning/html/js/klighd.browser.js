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
  $('errors').hide();
}

function sendJson(obj) {
  if (connection.readyState === WebSocket.OPEN) {
    connection.send(JSON.stringify(obj));
  } else {
    error("Web Socket closed, please reconnect.");
  }
}

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
    // currently nothing to do
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
      // set the svg
      $('#viewport').html("");
      $('#viewport').html(json.data);

      // attach zoom pan functionality
      zoomPanManager = $('#viewport').zoomPan();

      // attach a mousedown listener to handle expanding of hierarchical nodes
      var expandFun = function(d) {
        // get the id
        var hashcode = $(this).attr("data-id");

        // if starts with id
        if (hashcode) {
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

    } else if (json.type === "ERROR") {
      error(json.data);
    }
  };

  // -- Try to connect
  $('#messages').html("Connecting ...");
  setTimeout(function() {
    if (connection.readyState === WebSocket.OPEN) {
      $('#messages').html("Connected to " + window.location.host + ".");
      hideErrors();
    } else {
      $('#messages').html("Could not connect to web socket.");
      connection = null;
    }
  }, 500);

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
function joined(name) {
  $('#currentRoom').html("You are in room " + name + ".");
  $('#joinDiv').hide();
  $('#leaveDiv').show();
}

function left() {
  $('#joinDiv').show();
  $('#leaveDiv').hide();
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
  $.ajax({
    type : 'GET',
    url : 'content/',
    success : function(res) {
      $('#data').html(res);

      // register listener if a file is clicked
      $('.file').click(function(e) {
        e.preventDefault();
        var path = $(this).attr("data-path");

        connection.send(JSON.stringify({
          type : 'RESOURCE',
          path : path
        }));

        // clear old diagram
        $('#viewport').html("");

        // clear the current transform
        if (zoomPanManager) {
          zoomPanManager.each(function(k, m) {
            m.zpm.reset();
          });
        }
      });

      // init tree
      $("#tree").tree();
      $("#tree_icons").tree(
          {
            toggle : function(evt, ui) {
              var expanded = (ui.nodes.attr("aria-expanded") == "true");
              ui.nodes.children("a").children("span.ui-icon").removeClass(
                  "ui-icon-folder-" + (expanded ? "collapsed" : "open")).addClass(
                  "ui-icon-folder-" + (expanded ? "open" : "collapsed"));
            }
          });
    },
    error : function(e) {
      $('#errors').html(e);
    }
  });
}

// executed
$(function() {

  // get the initial content
  loadRepository();

  // try to connect automatically
  webSocketConnect();

  // register reconnect button
  $('#connect').click(webSocketConnect);

});
