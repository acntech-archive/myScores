'use strict';

angular.module('myScores')
    .controller('GameregistrationCtrl', ['$scope', 'Hostcityservice', 'Groupservice', function ($scope, Hostcityservice, Groupservice) {
        
        $scope.cities = Hostcityservice.getHostCities();
        $scope.city = $scope.cities[0];
        
        
        
        $scope.groups = Groupservice.getGroups();
        
        $scope.calculateTotal = function(team) {
            return team.wins * 3 + team.draws;
        };
        
    }]);
