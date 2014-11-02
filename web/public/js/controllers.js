var tf_idf = angular.module('tf-idf.controllers', []);

tf_idf.controller('HomeCtrl', function ($scope, $http, $state) {
  $http({
    method: 'GET',
    url: "http://localhost:8080/v1/articles"
  }).
  success(function (data, status, headers, config) {
    console.log(data)
    $scope.articles = data;
  }).
  error(function (data, status, headers, config) {
    console.log("ERROR: " + data);
  });

  $scope.loadArticle = function(articleId) {
    $state.go('article', {id: articleId});
  }
});

tf_idf.controller('ArticleCtrl', function($scope, $http, $state) {
  $http({
    method: 'GET',
    url: "http://localhost:8080/v1/article/" + $state.params.id
  }).
  success(function (data, status, headers, config) {
    console.log( data );
    $scope.article = data[0];
  }).
  error(function (data, status, headers, config) {
    console.log("ERROR: " + data);
  });
});
