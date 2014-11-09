var tf_idf = angular.module('tf-idf.services', []);

tf_idf.factory('ArticleService', ['$http', function($http) {
  getAllArticles = function(successFn, errorFn) {
    $http({
      method: 'GET',
      url: "http://localhost:8080/v1/articles"
    }).
    success(function (data, status, headers, config) {
      successFn(data);
    }).
    error(function (data, status, headers, config) {
      errorFn(data);
    });
  };

  getArticle = function(articleId, successFn, errorFn) {
    $http({
      method: 'GET',
      url: "http://localhost:8080/v1/article/" + articleId
    }).
    success(function (data, status, headers, config) {
      successFn(data);
    }).
    error(function (data, status, headers, config) {
      errorFn(data);
    });
  };

  addArticle = function(articleTitle, successFn, errorFn) {
    $http({
      method: 'POST',
      url: "http://localhost:8080/v1/wiki-article",
      data: {title: articleTitle}
    }).
    success(function (data, status, headers, config) {
      successFn(data);
    }).
    error(function (data, status, headers, config) {
      errorFn(data);
    });
  }

  return {
    getAllArticles: getAllArticles,
    getArticle: getArticle,
    addArticle: addArticle
  };
}]);
