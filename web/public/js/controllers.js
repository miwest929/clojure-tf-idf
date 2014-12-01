var tf_idf = angular.module('tf-idf.controllers', []);

tf_idf.controller('HomeCtrl', function ($scope, $state, ArticleService) {
  $scope.selected = -1;

  ArticleService.getAllArticles(function(data) { $scope.articles = data }, function(data) {});

  $scope.select = function(index) {
    $scope.selected = index;
  }

  $scope.loadOverview = function() {
    $state.go('overview');
  }

  $scope.addWikiArticle = function() {
    ArticleService.addArticle($scope.wikiArticleName, function(data) {
      console.log(data);
    }, function(data) {
      console.log("ERROR: " + data);
    });
  }

  $scope.loadArticle = function(articleId, articleTitle, index) {
    $state.go('article', {id: articleId, title: articleTitle});
  }
});

tf_idf.controller('ArticleCtrl', function($scope, $state, ArticleService) {
  console.log($state.params);
  $scope.articleTitle = "ARTICLE";

  ArticleService.getArticle($state.params.id, function(data) {
    $scope.article_words = data;
    $scope.maxTFIDF = $scope.article_words[0].tf_idf;
  }, function(data) {});

  $scope.greenProgress = function(maxValue, currentValue) {
    var greenGradients = {
      20: "#81F79F",
      30: "#00FF80",
      40: "#04B45F",
      60: "#088A08",
      80: "#0B3B0B",
      100: "#0A2A0A"
    };
    var percentageOf = (currentValue / maxValue);

    for(gradientLevel in greenGradients) {
      if (percentageOf < gradientOf)
        return greenGradients[gradientLevel];
    }
  };
});

tf_idf.controller('OverviewCtrl', function($scope, $http, $state) {
});
