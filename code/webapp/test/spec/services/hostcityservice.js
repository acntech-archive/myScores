'use strict';

describe('Service: Hostcityservice', function () {

  // load the service's module
  beforeEach(module('myScoresApp'));

  // instantiate service
  var Hostcityservice;
  beforeEach(inject(function (_Hostcityservice_) {
    Hostcityservice = _Hostcityservice_;
  }));

  it('should do something', function () {
    expect(!!Hostcityservice).toBe(true);
  });

});
