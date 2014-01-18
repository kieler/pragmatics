var kstats = {};

(function() {

  var _url = "http://localhost:1337/stats";

  kstats.setUrl = function(url) {
    _url = url;
  }

  /**
   * props: url: optional String url to retrieve the data, otherwise the
   * internally specified url is used collection: String document: String unit:
   * String e.g., day or month noOfUnits: Int for days e.g. 31, months e.g. 12
   * dateFormat: String e.g., YYYY-MM-DD id: String the figure's id in the html
   * document
   */
  kstats.createBarChart = function(props) {

    var dateFormat = props.dateFormat;
    var noUnits = props.noOfUnits;
    var lstUnit = moment().format(dateFormat);
    var fstUnit = moment().subtract(props.unit, noUnits - 1).format(dateFormat);

    // assemble query object here as we need to access it via [] notation
    var queryObj = {
      name : props.document
    }
    queryObj[props.unit] = {
      '$gte' : fstUnit,
      '$lte' : lstUnit
    }

    // same is true for fields
    var fields = {
      'count' : true
    }
    fields[props.unit] = true;

    $.ajax({
      type : 'GET',
      dataType : 'json',
      url : props.url || _url,
      data : {
        coll : props.collection,
        obj : queryObj,
        fields : fields,
        opts : {
          sort : props.unit
        }
      },
      success : function(data) {

        // create object for every possible element even if there is no db
        // object
        var units = {};
        var chartUnits = [];
        var count = noUnits - 1;
        while (count >= 0) {
          var curUnit = moment().subtract(props.unit, count--).format(dateFormat);
          units[curUnit] = 0;
          chartUnits.push({
            x : curUnit,
            y : 0
          });
        }

        // map with the retrieved data from the db
        $.each(data, function(i, row) {
          units[row[props.unit]] = row.count;
        });

        // convert data to xChart representation
        for (var i = 0; i < chartUnits.length; ++i) {
          chartUnits[i].y = units[chartUnits[i].x];
        }

        // create the chart
        var chartData = {
          "xScale" : "ordinal",
          "yScale" : "linear",
          "main" : [ {
            "className" : ".pizza",
            "data" : chartUnits
          } ]
        };

        var opts = {
          "mouseover" : function(d, i) {
            var parent = $('#' + props.id);
            var x = d3.event.pageX - parent.offset().left;
            var y = d3.event.pageY - (d3.event.pageY - parent.offset().top);
            
            $("#tt").html("<small>" + d.x + "</small>" + " (" + d.y + ")").css({
              top : y - 10,
              left : x + 5
            }).show();
          },
          "mouseout" : function(x) {
            $("#tt").hide();
          }
        }

        new xChart('bar', chartData, '#' + props.id, opts);
      }
    });
  }

  /**
   * props: url: optional String url to retrieve the data, otherwise the
   * internally specified url is used collection: String document: String the
   * document's name key: String the key of the document which we additionally
   * query for. it will serve as the basis for the multi bars unit: String e.g.,
   * day or month noOfUnits: Int for days e.g. 31, months e.g. 12 dateFormat:
   * String e.g., YYYY-MM-DD id: String the figure's id in the html document
   */
  kstats.createMultiBarChart = function(props) {

    var dateFormat = props.dateFormat;
    var noUnits = props.noOfUnits;
    var lstUnit = moment().format(dateFormat);
    var fstUnit = moment().subtract(props.unit, noUnits - 1).format(dateFormat);

    // assemble query object here as we need to access it via [] notation
    var queryObj = {
      name : props.document,
      key : props.key
    }
    queryObj[props.unit] = {
      '$gte' : fstUnit,
      '$lte' : lstUnit
    }

    // same is true for fields
    var fields = {
      'count' : true,
      'key' : true,
      'value' : true
    }
    fields[props.unit] = true;

    $.ajax({
      type : 'GET',
      dataType : 'json',
      url : props.url || _url,
      data : {
        coll : props.collection,
        obj : queryObj,
        // fields: fields,
        opts : {
          sort : props.unit
        }
      },
      success : function(data) {

        // create object for every possible element even if there is no db
        // object
        var units = {};
        var chartUnits = [];
        var count = noUnits - 1;
        while (count >= 0) {
          var curUnit = moment().subtract(props.unit, count--).format(dateFormat);
          units[curUnit] = {};
          chartUnits.push({
            x : curUnit,
            y : 0
          });
        }

        var algs = {};

        // map with the retrieved data from the db
        $.each(data, function(i, row) {
          // units[row[props.unit]] = {};
          // count of a certain alg (row.value) at the date (props.unit)
          units[row[props.unit]][row.value] = row.count;
          algs[row.value] = {}; // which alg exists?
        });

        // convert data to xChart representation
        var topLevelData = [];
        for (alg in algs) {
          var algData = [];
          for (var i = 0; i < chartUnits.length; ++i) {
            algData.push({
              x : chartUnits[i].x,
              y : units[chartUnits[i].x][alg] || 0,
              alg : alg
            });
          }
          topLevelData.push(algData);
        }

        // combine the data
        var mainData = [];
        for (var i = 0; i < topLevelData.length; ++i) {
          mainData.push({
            // IMPORTANT to add a second class here, otherwise the multi bar
            // does not show
            "className" : ".pizza.l" + i,
            "data" : topLevelData[i]
          });
        }

        // create the chart
        var chartData = {
          "xScale" : "ordinal",
          "yScale" : "linear",
          "comp" : [],
          "type" : "bar",
          "main" : mainData
        };

        var opts = {
          "mouseover" : function(d, i) {
            var x = d3.event.pageX;
            var y = d3.event.pageY;
            
            $("#tt").html("<small>" + d.x + " " + d.alg + "</small> (" + d.y + ")").css({
              top : y - 10,
              left : x + 5
            }).show();
          },
          "mouseout" : function(x) {
            $("#tt").hide();
          }
        }

        var chart = new xChart('line-dotted', chartData, '#' + props.id, opts);
        chart.setData(chartData);
      }
    });
  }

})();