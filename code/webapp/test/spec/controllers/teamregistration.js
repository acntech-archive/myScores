'use strict';

describe('Controller: TeamregistrationCtrl', function () {

  // load the controller's module
  beforeEach(module('myScoresApp'));

  var TeamregistrationCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TeamregistrationCtrl = $controller('TeamregistrationCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
