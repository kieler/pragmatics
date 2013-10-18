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
  }
}