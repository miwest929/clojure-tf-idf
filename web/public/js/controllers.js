var tf_idf = angular.module('tf-idf.controllers', []);

tf_idf.controller('HomeCtrl', function ($scope, $http, $state) {
  $http({
    method: 'GET',
    url: "http://localhost:8080/v1/articles"
  }).
  success(function (data, status, headers, config) {
    console.log(data)
    $scope.articles = data.articles;
  }).
  error(function (data, status, headers, config) {
    console.log("ERROR: " + data);
  });
});

