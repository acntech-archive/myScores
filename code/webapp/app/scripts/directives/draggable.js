'use strict';

angular.module('myScores')
    .directive('draggable', function() {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
//                element.text('this is the draggable directive');
                console.log(attrs);
                var el = element[0];
                el.draggable = true;

                el.addEventListener('dragstart',
                    function(e) {
                        e.dataTransfer.effectAllowed = 'move';
                        e.dataTransfer.setData('Text', this.id);
                        this.classList.add('drag');
                        return false;
                    },
                    false
                );

                el.addEventListener('dragend',
                    function(e) {
                        console.log(e.type);
                        this.classList.remove('drag');
                        return false;
                    },
                    false
                );
            }
        };
    });
