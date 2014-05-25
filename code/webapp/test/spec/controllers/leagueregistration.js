'use strict';

describe('Controller: LeagueregistrationCtrl', function () {

  // load the controller's module
  beforeEach(module('myScoresApp'));

  var LeagueregistrationCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LeagueregistrationCtrl = $controller('LeagueregistrationCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
