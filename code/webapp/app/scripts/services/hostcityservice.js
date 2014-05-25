'use strict';

angular.module('myScores')
  .service('Hostcityservice', function Hostcityservice() {
    var hostCities = [
        "Sao Paulo",
        "Natal",
        "Salvador",		
        "Curitiba",		
        "Belo Horizonte",		
        "Recife",		
        "Fortaleza",		
        "Manaus",		
        "Brasilia",		
        "Porto Alegre",		
        "Rio De Janeiro",		
        "Cuiaba"		

    ];
    
    this.getHostCities = function() {
        return hostCities;
    };
    
  });
