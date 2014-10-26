'use strict';

/* Directives */

angular.module('tf-idf.directives', []).
  directive('appVersion', function (version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  });
