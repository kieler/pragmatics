$klighdning = {

  /**
   * @param svg
   *          the compressed representation of the svg
   * @returns the uncompressed string representation of the svg
   */
  decompress : function(svg) {
    var gunzip = new Zlib.Gunzip($klighdning.stringToByteArray(svg));
    var plain = gunzip.decompress();

    return $klighdning.byteArrayToString(plain);
  },

  /**
   * @param str
   *          Arbitrary string.
   * @returns an array where the element[i] represents the byte value of the
   *          character at position i.
   */
  stringToByteArray : function(str) {
    var array = new (window.Uint8Array !== void 0 ? Uint8Array : Array)(str.length);
    for (var i = 0; i < str.length; ++i) {
      array[i] = str.charCodeAt(i) & 0xff;
    }
    return array;
  },

  /**
   * @param array
   *          An array of byte values.
   * @returns A string where the character at position i is derived from the
   *          byte value.
   */
  byteArrayToString : function(array) {
    var str = "";
    for (var i = 0; i < array.length; ++i) {
      str += String.fromCharCode(array[i]);
    }
    return str;
  },

  /**
   * Collects all parameters of the current url. Parameters can be accessed like
   * this: "queryString.transform"
   */
  queryString : function() {
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
      var pair = vars[i].split("=");
      // If first entry with this name
      if (typeof query_string[pair[0]] === "undefined") {
        query_string[pair[0]] = pair[1];
        // If second entry with this name
      } else if (typeof query_string[pair[0]] === "string") {
        var arr = [ query_string[pair[0]], pair[1] ];
        query_string[pair[0]] = arr;
        // If third or later entry with this name
      } else {
        query_string[pair[0]].push(pair[1]);
      }
    }
    return query_string;
  }()

}