$(function() {

  var visualize = function() {
    var model = $('#modelInput').val();
    $.ajax({
      url : '/textualModel',
      data : {
        model : model
      },
      success : function(res) {
        $('#content').html("");
        $('#content').html(res);
      }
    });
  };
  
  $('#visualize').click(function() {
    console.log();
    visualize();
  });

  var timer = $.timer(function() {
    //alert('This message was sent by a timer.');
    console.log("timer");
    timer.stop();
    visualize();
  });
  timer.set({
    time : 1000,
    autostart : false
  });
  

  $('#modelInput').keypress(function() {
    timer.reset();
  })

});