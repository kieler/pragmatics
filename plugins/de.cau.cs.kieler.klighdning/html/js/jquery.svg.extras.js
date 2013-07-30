(function($) {

  $.fn.zoomPan = function(opts) {

    // create the manager and register the element
    return this.each(function() {
      this.zpm = new ZoomPanManager($(this));
      return this;
    });

  };

  var ZoomPanManager = function(ele, opts) {
    this.jElement = ele;

    var zm = this;

    // register for mouse panning
    ele.drag("init", function(event, dd) {

      // remember the reference point of the dragging
      zm._dragRefX = zm.ctm.e;
      zm._dragRefY = zm.ctm.f;

      // return the object to continue drag action
      return this;
    });

    // listen to the actual dragging
    ele.drag(function(event, dd) {

      // get the targets offsets to adapt the drag offset
      // FIXME
      var targetOffset = $(dd.target).offset();

      zm.dumpCTM();
      // adjust the drag offsets depending on the current scale
      var xOffset = dd.offsetX;
      var yOffset = dd.offsetY;
      var left = targetOffset.left;
      var top = targetOffset.top;

      // set the new translate values
      zm.ctm.e = zm._dragRefX + xOffset - left;
      zm.ctm.f = zm._dragRefY + yOffset - top;

      // set the new matrix
      var element = document.getElementById("group");
      element.setAttribute('transform', zm.joinMatrix());

      // fire a change event
      $('g#group').change();
    });

    ele.drag("end", function() {

      // clear reference point
//      zm._dragRefX = 0;
//      zm._dragRefY = 0;
      zm._dragRefX = zm.ctm.e;
      zm._dragRefY = zm.ctm.f;

      return this;
    });

    // register for mouse zooming

    // omit the original mouse wheel, otherwise scrolling the svg also scrolls
    // the window
    ele.hover(function() {
      $(document).bind('mousewheel DOMMouseScroll', function(e) {
        stopWheel(e);
      });
    }, function() {
      $(document).unbind('mousewheel DOMMouseScroll');
    });

    function stopWheel(e) {
      console.log("stop");
      // only stop if there actually exists a svg
      if (ele.find('svg').length == 0) {
        return;
      }
      if (!e) { /* IE7, IE8, Chrome, Safari */
        e = window.event;
      }
      if (e.preventDefault) { /* Chrome, Safari, Firefox */
        e.preventDefault();
      }
      e.returnValue = false; /* IE7, IE8 */
    }

    // add mouse wheel scaling to all elements
    ele.bind('mousewheel', function(event, delta) {

      var oldScaleX = zm.ctm.a;
      var oldScaleY = zm.ctm.d;

      if (delta > 0) {
        // zoom in
        zm.ctm.a += 0.1;
        zm.ctm.d += 0.1;
      } else {
        // zoom out
        zm.ctm.a -= 0.1;
        zm.ctm.d -= 0.1;
        // cap at a minimum value
        if (zm.ctm.a <= 0.2) {
          zm.ctm.a = 0.2;
        }
        if (zm.ctm.d <= 0.2) {
          zm.ctm.d = 0.2;
        }
      }
      //zm.ctm.a = Math.pow(1 + 0.2, delta);
      //zm.ctm.d = Math.pow(1 + 0.2, delta);

      

      // get the mouse position
      // FIXME
      var parentOffset = ele.offset(); // ele.offset();
      var relX = event.pageX - parentOffset.left;
      var relY = event.pageY - parentOffset.top;

      // adapt the translate
      var offsetX = -(relX / oldScaleX + ((-zm._dragRefX) || 0) - relX / zm.ctm.a);
      var offsetY = -(relY / oldScaleY + ((-zm._dragRefX) || 0) - relY / zm.ctm.d);
      zm.ctm.e = offsetX;
      zm.ctm.f = offsetY;
      zm._dragRefX = zm.ctm.e;
      zm._dragRefY = zm.ctm.f;
      /*
       * $('#xc').text(svg._oldTransX + " " + offsetX + " ");
       * $('#yc').text(svg._oldTransY + " " + offsetY);
       */

      // set the new translate
      // translate = "translate(" + offsetX + ", " + offsetY + ")";
      // svg._oldTransX = offsetX;
      // svg._oldTransY = offsetY;
      var element = document.getElementById("group");
      element.setAttribute('transform', zm.joinMatrix());

      // set the new transform
      // g.setAttribute('transform', 'scale(' + g._scale + ')' + " " +
      // (translate || ""));
      // fire a change event
      $('g#group').change();

      // svg._scale = g._scale;

      return true;
    });

  };

  // the internal matrix
  ZoomPanManager.prototype.pan = function(dx, dy) {
    this.ctm.e += dx;
    this.ctm.f += dy;
    var element = document.getElementById("group");
    element.setAttribute("transform", this.joinMatrix());
  };

  ZoomPanManager.prototype.zoom = function(scale) {
    for (key in this.ctm) {
      this.ctm[key] *= scale;
    }

    var width = this.jElement.width();
    var height = this.jElement.height();
    console.log("w " + width + " h " + height)

    this.ctm.e += (1 - scale) * width / 2;
    this.ctm.f += (1 - scale) * height / 2;

    var element = document.getElementById("group");
    element.setAttribute("transform", this.joinMatrix());
  };

  ZoomPanManager.prototype.ctm = {
    a : 1,
    b : 0,
    c : 0,
    d : 1,
    e : 0,
    f : 0
  };

  ZoomPanManager.prototype.dumpCTM = function() {
    console.log(this.joinMatrix());
  }

  ZoomPanManager.prototype.reset = function() {
    this.ctm.a = 1, this.ctm.b = 0, this.ctm.c = 0, this.ctm.d = 1, this.ctm.e = 0, this.ctm.f = 0;
  };

  ZoomPanManager.prototype.joinMatrix = function() {
    return "matrix(" + this.ctm.a + " " + this.ctm.b + " " + this.ctm.c + " " + this.ctm.d + " " + this.ctm.e + " "
        + this.ctm.f + ")";
  };

}(jQuery));