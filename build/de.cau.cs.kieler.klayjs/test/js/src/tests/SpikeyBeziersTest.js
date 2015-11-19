{
  active: true,
  name: 'SpikeyBeziersLayoutTest',
  options: {
    "edgeRouting": "SPLINES",
    "thoroughness": 1000,
    "debugMode": true,
    "aspectRatio": 0.2,
    "intCoordinates": true,
    "edgeSpacingFactor": 1.2,
    "spacing": 10,
    "direction": "DOWN",
    "nodePlace": "BRANDES_KOEPF",
    "nodeLayering": "NETWORK_SIMPLEX",
    "layoutHierarchy": true
  },
  graph: {
    "id": "root",
    "children": [{
      "properties": {
        "portConstraints": "FIXED_SIDE",
        "portAlignment": "JUSTIFIED",
        "portSpacing": 20,
        "labelSpacing": 20,
        "portLabelPlacement": "INSIDE",
        "sizeConstraint": "PORTS PORT_LABELS NODE_LABELS MINIMUM_SIZE",
        "sizeOptions": "COMPUTE_INSETS APPLY_ADDITIONAL_INSETS MINIMUM_SIZE_ACCOUNTS_FOR_INSETS",
        "minHeight": 20,
        "nodeLabelPlacement": "INSIDE V_TOP H_CENTER",
        "minWidth": 50,
        "additionalPortSpace": "top=30,left=10,right=10,bottom=30"
      },
      "ports": [{
        "properties": {
          "offset": 0,
          "portSide": "WEST"
        },
        "name": "recipe",
        "labels": [],
        "id": "Oven-0recipe",
        "width": 1,
        "height": 1
      }, {
        "properties": {
          "offset": 0,
          "portSide": "WEST"
        },
        "name": "ingredients",
        "labels": [],
        "id": "Oven-0ingredients",
        "width": 1,
        "height": 1
      }, {
        "properties": {
          "offset": 0,
          "portSide": "SOUTH"
        },
        "name": "cookie_batch",
        "labels": [{
          "text": "cookie_batch",
          "width": 89.390625,
          "height": 15
        }],
        "id": "Oven-0cookie_batch",
        "width": 1,
        "height": 1
      }],
      "labels": [{
        "text": "Oven",
        "width": 67.296875,
        "height": 41
      }],
      "name": "Oven-0",
      "width": 10,
      "height": 10,
      "id": "0"
    }, {
      "properties": {
        "portConstraints": "FIXED_SIDE",
        "portAlignment": "JUSTIFIED",
        "portSpacing": 20,
        "labelSpacing": 20,
        "portLabelPlacement": "INSIDE",
        "sizeConstraint": "PORTS PORT_LABELS NODE_LABELS MINIMUM_SIZE",
        "sizeOptions": "COMPUTE_INSETS APPLY_ADDITIONAL_INSETS MINIMUM_SIZE_ACCOUNTS_FOR_INSETS",
        "minHeight": 20,
        "nodeLabelPlacement": "INSIDE V_TOP H_CENTER",
        "minWidth": 50,
        "additionalPortSpace": "top=30,left=10,right=10,bottom=30"
      },
      "ports": [{
        "properties": {
          "offset": 0,
          "portSide": "SOUTH"
        },
        "name": "recipe",
        "labels": [{
          "text": "recipe",
          "width": 44.34375,
          "height": 15
        }],
        "id": "RecipeBook-0recipe",
        "width": 1,
        "height": 1
      }],
      "labels": [{
        "text": "RecipeBook",
        "width": 125.765625,
        "height": 41
      }],
      "name": "RecipeBook-0",
      "width": 10,
      "height": 10,
      "id": "1"
    }, {
      "properties": {
        "portConstraints": "FIXED_SIDE",
        "portAlignment": "JUSTIFIED",
        "portSpacing": 20,
        "labelSpacing": 20,
        "portLabelPlacement": "INSIDE",
        "sizeConstraint": "PORTS PORT_LABELS NODE_LABELS MINIMUM_SIZE",
        "sizeOptions": "COMPUTE_INSETS APPLY_ADDITIONAL_INSETS MINIMUM_SIZE_ACCOUNTS_FOR_INSETS",
        "minHeight": 20,
        "nodeLabelPlacement": "INSIDE V_TOP H_CENTER",
        "minWidth": 50,
        "additionalPortSpace": "top=30,left=10,right=10,bottom=30"
      },
      "ports": [{
        "properties": {
          "offset": 0,
          "portSide": "WEST"
        },
        "name": "recipe",
        "labels": [],
        "id": "Shop-0recipe",
        "width": 1,
        "height": 1
      }, {
        "properties": {
          "offset": 0,
          "portSide": "SOUTH"
        },
        "name": "ingredients",
        "labels": [{
          "text": "ingredients",
          "width": 77.40625,
          "height": 15
        }],
        "id": "Shop-0ingredients",
        "width": 1,
        "height": 1
      }],
      "labels": [{
        "text": "Shop",
        "width": 65.265625,
        "height": 41
      }],
      "name": "Shop-0",
      "width": 10,
      "height": 10,
      "id": "2"
    }],
    "edges": [{
      "sourcePort": "RecipeBook-0recipe",
      "source": "1",
      "targetPort": "Oven-0recipe",
      "target": "0",
      "id": "e3"
    }, {
      "sourcePort": "Shop-0ingredients",
      "source": "2",
      "targetPort": "Oven-0ingredients",
      "target": "0",
      "id": "e4"
    }, {
      "sourcePort": "RecipeBook-0recipe",
      "source": "1",
      "targetPort": "Shop-0recipe",
      "target": "2",
      "id": "e5"
    }]
  },
  expected: {
    e3: {
      bendPointCount: 14
    },
    e4: {
      bendPointCount: 8
    },
    e5: {
      bendPointCount: 17
    }
  },
  check: function(result) {
    if (typeof result.id === 'undefined' || result.id != 'root') {
      return false;
    }
    var expected = this.expected;
    var valid = true;
    result.edges.forEach(function(edge) {
      valid &= edge.bendPoints.length == expected[edge.id].bendPointCount;
    });

    return valid;
  },
  error_msg: function(result) {
    return 'Positions of nodes and edge bendpoints should remain the same!';
  }
}