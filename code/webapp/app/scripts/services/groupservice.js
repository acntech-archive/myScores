'use strict';

angular.module('myScores')
    .service('Groupservice', function Groupservice() {

    var groups = [
        {
            name : 'A',
            teams : [
                {
                    name: 'Brazil',
                    wins: 1,
                    losses: 1,
                    draws: 1,
                    flag: 'bra.png'
                },
                {
                    name: 'Mexico',
                    wins: 1,
                    losses: 0,
                    draws: 0,
                    flag: 'mex.png'
                },
                {
                    name: 'Croatia',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'cro.png'
                },
                {
                    name: 'Cameroon',
                    wins: 2,
                    losses: 0,
                    draws: 0,
                    flag: 'cmr.png'
                }
            ]
        },
        {
            name : 'B',
            teams: [
                {
                    name: 'Spain',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'esp.png'
                },
                {
                    name: 'Chile',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'chi.png'
                },
                {
                    name: 'Netherlands',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'ned.png'
                },
                {
                    name: 'Australia',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'aus.png'
                }
            ]
        },
        {
            name: 'C',
            teams: [
                {
                    name: 'Colombia',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'col.png'
                },
                {
                    name: 'Greece',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'gre.png'
                },
                {
                    name: 'Côte d\'Ivoire',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'civ.png'
                },
                {
                    name: 'Japan',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'jpn.png'
                }
            ]
        },
        {
            name: 'D',
            teams: [
                {
                    name: 'Uruguay',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'uru.png'
                },
                {
                    name: 'Italy',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'ita.png'
                },
                {
                    name: 'England',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'eng.png'
                },
                {
                    name: 'Costa Rica',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'crc.png'
                }
            ]
        },
        {
            name : 'E',
            teams: [
                {
                    name: 'Switzerland',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'sui.png'
                },
                {
                    name: 'France',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'fra.png'
                },
                {
                    name: 'Ecuador',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'ecu.png'
                },
                {
                    name: 'Honduras',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'hon.png'
                }
            ]
        },
        {
            name: 'F',
            teams: [
                {
                    name: 'Argentina',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'arg.png'
                },
                {
                    name: 'Bosnia-Herzegovina',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'bih.png'
                },
                {
                    name: 'Iran',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'irn.png'
                },
                {
                    name: 'Nigeria',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'nga.png'
                }
            ]
        },
        {
            name: 'G',
            teams: [
                {
                    name: 'Germany',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'ger.png'
                },
                {
                    name: 'Portugal',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'por.png'
                },
                {
                    name: 'USA',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'usa.png'
                },
                {
                    name: 'Ghana',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'gha.png'
                }
            ]
        },
        {
            name: 'H',
            teams: [
                {
                    name: 'Belgium',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'bel.png'
                },
                {
                    name: 'Russia',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'rus.png'
                },
                {
                    name: 'Algeria',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'alg.png'
                },
                {
                    name: 'Korea Republic',
                    wins: 0,
                    losses: 0,
                    draws: 0,
                    flag: 'kor.png'
                }
            ]
        }
    ];
     
    this.getGroups = function() {
        return groups;
    };
});
