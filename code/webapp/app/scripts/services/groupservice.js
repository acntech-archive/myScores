'use strict';

angular.module('myScores')
  .service('Groupservice', function Groupservice() {

    var groups = [
        { 
            name : "A",
            teams : [
                { name: "Brazil",
                    wins: 1,
                    losses: 1,
                    draws: 1
                    
                },
                {
                    name: "Mexico",
                    wins: 1,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Croatia",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Cameroon",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]
        },
        {
            name : "B",
            teams: [
                {
                    name: "Spain",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Chile",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Netherlands",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Australia",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]
        },
        {
            name: "C",
            teams: [
                {
                    name: "Colombia",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Greece",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Côte d'Ivoire",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                { 
                    name: "Japan",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]
        },
        {
            name: "D",
            teams: [
                {
                    name: "Uruguay",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Italy",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "England",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Costa Rica",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]
        },
        {
            name : "E", 
            teams: [
                {
                    name: "Switzerland",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "France",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Ecuador",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Honduras",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]
        },
        {
            name: "F",
            teams: [
                {
                    name: "Argentina",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Bosnia-Herzegovina",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Iran",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Nigeria",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]    
        },
        {
            name: "G",
            teams: [
                {
                    name: "Germany",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Portugal",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "USA",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Ghana",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]
        },
        {
            name: "H",
            teams: [
                {
                    name: "Belgium",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Russia",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Algeria",
                    wins: 0,
                    losses: 0,
                    draws: 0
                },
                {
                    name: "Korea Republic",
                    wins: 0,
                    losses: 0,
                    draws: 0
                }
            ]
        }
    ];
     
     this.getGroups = function() {
        return groups;
     };
  });
