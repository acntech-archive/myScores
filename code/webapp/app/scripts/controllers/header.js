'use strict';

angular.module('myScores')
  .controller('HeaderCtrl', ['$scope', 'Userservice', function ($scope, Userservice) {
    $scope.isLoggedIn = Userservice.isLoggedIn;
    $scope.isAdmin = Userservice.isAdmin;
}]);
