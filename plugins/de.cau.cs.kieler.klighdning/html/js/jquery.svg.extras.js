(function(factory) {
  "use strict";
  if (typeof define === 'function' && define.amd) {
    define([ 'jquery' ], factory);
  } else {
    factory(jQuery);
  }
}(function($) {
  "use strict";

  /**
   * The Zoom Pan Manager
   */
  var ZoomPanManager = function(ele, opts) {

    // handle the options
    this.viewport = opts.viewport;

    // init the root elements
    this._svgr = $('svg')[0];
    this._group = document.getElementById(this.viewport);
    this._element = ele;

    // apply a possibly existing transform
    var transform = this._group.getAttribute('transform');

    if (transform && transform.indexOf("matrix") != -1) {
      // however, currently only the matrix transform is supported
      var outerChunks = transform.split(/\(|\)/g);
      var innerChunks = outerChunks[1].split(" ");
      // make sure we work with floats
      console.log(innerChunks);
      innerChunks = $.map(innerChunks, function(e, i) {
        return parseFloat(e);
      });
      this.setMatrix({
        a : innerChunks[0],
        b : innerChunks[1],
        c : innerChunks[2],
        d : innerChunks[3],
        e : innerChunks[4],
        f : innerChunks[5],
      });
      this.dumpCTM();
    } else {
      // reset is currently required due to prototype
      // FIXME extract the state information from the prototype
      this.reset();
    }

    // me myself and I
    var zm = this;

    // Pan start event
    var dragStart = function(event, dd) {
      // remember the reference point of the dragging
      zm._dragRefX = zm._ctm.e;
      zm._dragRefY = zm._ctm.f;

      // return the object to continue drag action
      return this;
    },

    // Panning event
    drag = function(event, dd) {
      // get the targets offsets to adapt the drag offset
      var targetOffset = $(dd.target).offset();

      // calculate the screen position without offsets
      var screenX = dd.offsetX - targetOffset.left;
      var screenY = dd.offsetY - targetOffset.top;

      // set the new translate values
      zm._ctm.e = zm._dragRefX + screenX;
      zm._ctm.f = zm._dragRefY + screenY;

      // set the new matrix
      zm.setMatrix();

      // fire a change event
      $('#' + zm.viewport).change();
    },

    dragEnd = function(event) {

    },

    // Used to catch the mousewheel event during zooming
    stopWheel = function(e) {
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
    },

    // The zoom event
    mousewheel = function(event, delta) {

      var z = 0;
      if (delta > 0) {
        // zoom in
        z = 1.2;
      } else {
        // zoom out
        z = 0.8;
      }

      // get the screen pos of the event
      var point = zm._svgr.createSVGPoint();

      if (typeof event.offsetX === "undefined" || typeof event.offsetY === "undefined") {
        var offset = $('#' + zm.viewport).offset();
        point.x = event.pageX - offset.left;
        point.y = event.pageY - offset.top;
      } else {
        point.x = event.offsetX;
        point.y = event.offsetY;
      }

      // transform
      point = point.matrixTransform(zm._group.getCTM().inverse());
      // adjust the translation to the new zoom level
      var adjustMatrix = zm._svgr.createSVGMatrix().translate(point.x, point.y).scale(z).translate(
          -point.x, -point.y);
      var newCTM = zm._group.getCTM().multiply(adjustMatrix);

      // set the new matrix
      zm.setMatrix(newCTM);

      // fire a change event
      $('#' + this.viewport).change();

      return true;
    };

    /*
     * Register the events
     */
    ele.drag("init", dragStart);
    ele.drag(drag);
    ele.bind('mousewheel', mousewheel);

    // omit the original mouse wheel, otherwise scrolling the svg also scrolls
    // the window
    ele.hover(function() {
      $(document).bind('mousewheel DOMMouseScroll', function(e) {
        stopWheel(e);
      });
    }, function() {
      $(document).unbind('mousewheel DOMMouseScroll');
    });

  };

  /**
   * Zoom Manager Prototype
   */
  ZoomPanManager.prototype = {

    // options
    viewport : "",

    // internal state
    _group : undefined,
    _svgr : undefined,
    _element : undefined,

    _ctm : {
      a : 1,
      b : 0,
      c : 0,
      d : 1,
      e : 0,
      f : 0
    },

    _dragRefX : 0,
    _dragRefY : 0,

    // function
    pan : function(dx, dy) {
      this._ctm.e += dx;
      this._ctm.f += dy;

      this.setMatrix();
    },

    zoom : function(scale) {
      this._ctm.a *= scale;
      this._ctm.b *= scale;
      this._ctm.c *= scale;
      this._ctm.d *= scale;
      this._ctm.e *= scale;
      this._ctm.f *= scale;

      var width = this._element.width();
      var height = this._element.height();

      this._ctm.e += (1 - scale) * width / 2;
      this._ctm.f += (1 - scale) * height / 2;

      this.setMatrix();
    },

    dumpCTM : function() {
      console.log(this.joinMatrix());
    },

    reset : function() {
      this._ctm.a = 1, this._ctm.b = 0, this._ctm.c = 0, this._ctm.d = 1, this._ctm.e = 0,
          this._ctm.f = 0;
      this.setMatrix();
    },

    joinMatrix : function() {
      return "matrix(" + this._ctm.a + " " + this._ctm.b + " " + this._ctm.c + " " + this._ctm.d
          + " " + this._ctm.e + " " + this._ctm.f + ")";
    },

    setMatrix : function(matrix) {

      // if a matrix is passed us it otherwise just set the current matrix
      if (matrix) {
        this._ctm.a = matrix.a;
        this._ctm.b = matrix.b;
        this._ctm.c = matrix.c;
        this._ctm.d = matrix.d;
        this._ctm.e = matrix.e;
        this._ctm.f = matrix.f;
      }

      this._group.setAttribute('transform', this.joinMatrix());

      // make sure a change event is fired on the group
      $('#' + this.viewport).change();
    }
  };

  /**
   * Register the zoomPan element
   * 
   * Options: - viewport: the id of the outermost group of the svg.
   */
  $.fn.zoomPan = function(opts) {

    if (!opts) {
      opts = {};
    }

    var options = {
      viewport : opts.viewport || "group"
    };

    // create the manager and register the element
    return this.each(function() {
      this.zpm = new ZoomPanManager($(this), options);
      return this;
    });

  };

}));