
  ];
// Wrapper stuff to support loading via browser and nodejs.
  if( typeof exports !== 'undefined' ) {
    if( typeof module !== 'undefined' && module.exports ) {
      exports = module.exports = tests
    }
    exports.tests = tests
  } 
  else {
    root.tests = tests
  }
}).call(this);

