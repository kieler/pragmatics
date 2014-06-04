var graph = {
  "id" : "root",
  properties : {
    "direction" : "DOWN",
    "spacing" : 40,
    "de.cau.cs.kieler.nodeLabelPlacement" : "[INSIDE, V_TOP, H_LEFT]"
  },
  "children" : [ {
    "id" : "n1",
    "width" : 40,
    "height" : 40
  }, {
    "id" : "n2",
    "width" : 40,
    "height" : 40
  }, {
    "id" : "n3",
    "width" : 40,
    "height" : 40
  } ],
  "edges" : [ {
    "id" : "e1",
    "source" : "n1",
    "target" : "n2"
  }, {
    "id" : "e2",
    "source" : "n1",
    "target" : "n3"
  }, {
    "id" : "e3",
    "source" : "n2",
    "target" : "n3"
  } ]
};

var graph2 = {
  id : "root",
  properties : {
    spacing : 100,
    edgeRouting : "ORTHOGONAL",
    layoutHierarchy : true
  },
  children : [ {
    id : "n1",
    labels : [ {
      text : "n1"
    } ],
    width : 100,
    height : 100
  }, {
    id : "n2",
    labels : [ {
      text : "n2"
    } ],
    width : 100,
    height : 50,
    properties : {
      spacing : 100,
      edgeRouting : "ORTHOGONAL"
    },
    ports : [ {
      id : "n2_p1",
      width : 10,
      height : 10
    } ],
    children : [ {
      id : "n3",
      labels : [ {
        text : "n3"
      } ],
      width : 40,
      height : 40
    }, {
      id : "n4",
      padding : {
        left : 100,
        right : 200,
        top : 400
      },
      labels : [ {
        text : "n4"
      } ],
      width : 40,
      height : 40,
      children : [ {
        id : "n10",
        labels : [ {
          text : "n10"
        } ],
        width : 100,
        height : 100
      }, {
        id : "n11",
        labels : [ {
          text : "n11"
        } ],
        width : 100,
        height : 100,
        ports : [ {
          id : "n11_p1",
          width : 10,
          height : 10
        }, {
          id : "n11_p2",
          width : 10,
          height : 10
        } ]
      } ],
      edges : [ {
        id : "e10",
        source : "n10",
        target : "n11",
        targetPort : "n11_p1"
      } ]
    } ],
    edges : [ {
      id : "e4",
      source : "n3",
      // target: "n4"
      target : "n11",
      targetPort : "n11_p2"
    } ]
  } ],
  edges : [ {
    id : "e1",
    labels : [ {
      text : "e1"
    } ],
    source : "n1",
    target : "n2",
    targetPort : "n2_p1"
  } ]
};

var graph3 = {
  id : "root", // root node
  children : [ {
    id : "n1", // node n1
    labels : [ {
      text : "n1"
    } ],
    // node n1 has fixed port constraints
    properties : {
      "de.cau.cs.kieler.portConstraints" : "FIXED_SIDE"
    },
    width : 100,
    height : 100,
    ports : [ {
      id : "p1",
      width : 10,
      height : 10,
      // port p1 should be located on the north side
      properties : {
        "de.cau.cs.kieler.portSide" : "NORTH"
      }
    } ]
  }, {
    id : "n2", // node n2
    labels : [ {
      text : "n2"
    } ],
    properties : {
      "de.cau.cs.kieler.portConstraints" : "FIXED_SIDE"
    },
    width : 100,
    height : 50,
    ports : [ {
      id : "p2",
      width : 10,
      height : 10,
      properties : {
        "de.cau.cs.kieler.portSide" : "SOUTH"
      }
    } ]
  } ],
  // children end
  edges : [ {
    id : "e1", // edge n1 -> n2
    source : "n1",
    target : "n2",
    sourcePort : "p1", // p1 -> p2
    targetPort : "p2"
  } ]
};

// with fixed positions
var graph4 = {
  id : "root",
  properties : {
    spacing : 100,
    edgeRouting : "ORTHOGONAL"
  },
  children : [ {
    id : "n1",
    labels : [ {
      text : "n1"
    } ],
    width : 50,
    height : 50
  }, {
    id : "n2",
    labels : [ {
      text : "n2"
    } ],
    width : 100,
    height : 50,
    properties : {
      spacing : 100,
      edgeRouting : "ORTHOGONAL",
      algorithm : "de.cau.cs.kieler.fixed"
    },
    ports : [ {
      id : "n2_p1",
      width : 10,
      height : 10
    } ],
    children : [ {
      id : "n3",
      labels : [ {
        text : "n3"
      } ],
      width : 40,
      height : 40,
      properties : {
        position : "20, 40"
      }
    }, {
      id : "n4",
      labels : [ {
        text : "n4"
      } ],
      width : 40,
      height : 40,
      properties : {
        position : "(140, 95)"
      }
    } ],
    edges : [ {
      id : "e4",
      source : "n3",
      target : "n4",
      properties : {
        // first and last are src and target
        bendPoints : "60, 60, 80, 10, 140, 115"
      }
    } ]
  } ],
  edges : [ {
    id : "e1",
    labels : [ {
      text : "e1"
    } ],
    source : "n1",
    target : "n2",
    targetPort : "n2_p1"
  } ]
};
