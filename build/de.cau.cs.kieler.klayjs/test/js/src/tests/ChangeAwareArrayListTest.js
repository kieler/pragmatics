{
  active: true,
  name: 'ChangeAwareArrayList',
  graph:
  {
    id: "root",
    children: [{
      id: "n1",
      labels: [ { text: "n1" } ],
      width: 50, 
      height: 50
    },{
      id: "n2",
      labels: [ { text: "n2" } ],
      width: 50,
      height: 50
    },{
      id: "n3",
      labels: [ { text: "n3" } ],
      width: 50,
      height: 50
    },{
      id: "n4",
      labels: [ { text: "n4" } ],
      width: 50,
      height: 50
    },{
      id: "n5",
      labels: [ { text: "n5" } ],
      width: 50,
      height: 50
    },{
      id: "n6",
      labels: [ { text: "n6" } ],
      width: 50,
      height: 50
    }],
    edges: [{
      id: "e1",
      labels: [ { text: "e1" } ],
      source: "n1",
      target: "n2"
    },{
      id: "e2",
      labels: [ { text: "e2" } ],
      source: "n2",
      target: "n3"
    },{
      id: "e3",
      labels: [ { text: "e3" } ],
      source: "n3",
      target: "n4"
    },{
      id: "e4",
      labels: [ { text: "e4" } ],
      source: "n4",
      target: "n5"
    },{
      id: "e5",
      labels: [ { text: "e5" } ],
      source: "n5",
      target: "n6"
    },{
      id: "e6",
      labels: [ { text: "e6" } ],
      source: "n6",
      target: "n3"
    }]
  },
  check: function(result) {
    return result.id === 'root';
  },
  errorMsg: function(result) {
    return 'Path-like graph failed to layout.\nError was: ' + result.type;
  }
}