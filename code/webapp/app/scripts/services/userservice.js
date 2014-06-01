'use strict';

angular.module('myScores')
    .service('Userservice', function Userservice() {
    // AngularJS will instantiate a singleton by calling "new" on this function
      
    var currentUser = {
        data: null,
        isLoggedIn : false,
        isAdmin : false
    };
     
    this.changeUser = function() {
        console.log('vi er i changeuser');
        currentUser.isLoggedIn = !currentUser.isLoggedIn;
        if (currentUser.isLoggedIn === false) {
            currentUser.isAdmin = false;
        }
    };
     
    this.changeAdmin = function() {
        console.log('vi er i changeadmin');
        if (currentUser.isLoggedIn) {
            currentUser.isAdmin = !currentUser.isAdmin;
        }
    };
     
    this.isLoggedIn = function() {
        return currentUser.isLoggedIn;
    };
     
    this.isAdmin = function() {
        return currentUser.isAdmin;
    };
     
});
