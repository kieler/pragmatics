<!--

  var AJAX_GET               = "GET";
  var AJAX_POST              = "POST";

  var AJAX_SYNC              = false;
  var AJAX_ASYNC             = true;

  // ajax options

  var AJAX_OPT_CREATEREQUEST = 1;
  var AJAX_OPT_NOPOST        = 2;
  var AJAX_OPT_NOFUNC        = 4;
  var AJAX_OPT_RETURNREQUEST = 8;

  //

  function Ajax_IsBrowserAjaxCapable() {
    return ( Ajax_CreateRequest() != null );
  }

  //

  function Ajax_CreateRequest() {
    var request = null;
    try {
      request = new XMLHttpRequest();
    } catch ( e ) {
      try {
        request = new ActiveXObject( "Msxml2.XMLHTTP" );
      } catch ( e ) {
        try {
          request = new ActiveXObject( "Microsoft.XMLHTTP" );
        } catch ( e ) {
        }
      }
    }
    return request;
  }

  function Ajax_Call(Options, Request, Type, Uri, Post, Async, Func) {
    if ( Options & AJAX_OPT_CREATEREQUEST )
      Request = Ajax_CreateRequest();
    if ( Request == null )
      throw "Ajax::Ajax_Call: AJAX_ERR_NOREQUEST";
    if (Type != AJAX_GET && Type != AJAX_POST) {
      throw "Ajax::Ajax_Call: AJAX_ERR_INVTYPE";
    }
    if (Uri == null || Uri.length == 0) {
      throw "Ajax::Ajax_Call: AJAX_ERR_NOURI";
    }
    if (Type == AJAX_POST) {
      if (Post == null || Post.length == 0) {
        if (!(Options & AJAX_OPT_NOPOST))
          throw "Ajax::Ajax_Call: AJAX_ERR_NOPOST";
      }
    }
    if (Async != AJAX_SYNC && Async != AJAX_ASYNC) {
      throw "Ajax::Ajax_Call: AJAX_ERR_INVSYNC";
    }
    if (Func == null) {
      if ( !(Options & AJAX_OPT_NOFUNC)) {
        throw "Ajax::Ajax_Call: AJAX_ERR_NOFUNC";
      }
    }

    var Result = 0;

    Request.open(Type, Uri, Async);

    Request.onreadystatechange = function() {
      switch(Request.readyState) {
        case 4:
          if (Request.status != 200) {
            throw "Ajax::Ajax_Call: AJAX_ERR_SERVERERROR (" + Request.status + " " + Request.statusText + ")";
          } else {
            if ( !( Options & AJAX_OPT_NOFUNC ) )
              Result = Func( Request );
          }
          break;
        default:
          break;
      }
    }

    Request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    if (Type ==  AJAX_GET) {
      Request.send( null );
    } else if ( Type == AJAX_POST ) {
      Request.send( Post );
    }

    return Result;

  }

  //

  function Ajax_GetAsync(Uri, Func) {
    return Ajax_Call( AJAX_OPT_CREATEREQUEST, null,  AJAX_GET, Uri, null, AJAX_ASYNC, Func );
  }

  function Ajax_GetSync(Uri, Func) {
    return Ajax_Call( AJAX_OPT_CREATEREQUEST, null,  AJAX_GET, Uri, null,  AJAX_SYNC, Func );
  }

  function Ajax_PostAsync(Uri, Post, Func) {
    return Ajax_Call( AJAX_OPT_CREATEREQUEST, null, AJAX_POST, Uri, Post, AJAX_ASYNC, Func );
  }

  function Ajax_PostSync(Uri, Post, Func) {
    return Ajax_Call( AJAX_OPT_CREATEREQUEST, null, AJAX_POST, Uri, Post,  AJAX_SYNC, Func );
  }

-->