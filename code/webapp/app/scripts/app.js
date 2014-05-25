'use strict';

angular
  .module('myScores', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/gameregistration', {
        templateUrl: 'views/gameregistration.html',
        controller: 'GameregistrationCtrl'
      })
      .when('/resultregistration', {
        templateUrl: 'views/resultregistration.html',
        controller: 'ResultregistrationCtrl'
      })
      .when('/teamregistration', {
        templateUrl: 'views/teamregistration.html',
        controller: 'TeamregistrationCtrl'
      })
      .when('/leagueregistration', {
        templateUrl: 'views/leagueregistration.html',
        controller: 'LeagueregistrationCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
