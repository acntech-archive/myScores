'use strict';

angular.module('myScores')
    .controller('LoginCtrl', ['$scope', 'Userservice', function ($scope, Userservice) {
        $scope.changeUser = Userservice.changeUser;
        $scope.changeAdmin = Userservice.changeAdmin;
    }]);
