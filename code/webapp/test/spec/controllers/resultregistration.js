'use strict';

describe('Controller: ResultregistrationCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var ResultregistrationCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ResultregistrationCtrl = $controller('ResultregistrationCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
