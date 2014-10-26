angular.module('tf-idf', [
  'ui.router',
  'tf-idf.controllers'
//  'tf-idf.services',
//  'tf-idf.directives'
]).
config(['$httpProvider', function($httpProvider) {
  $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
}]).
config(function ($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise('/');

  $stateProvider.state('home', {
    templateUrl: 'index.html',
    controller: 'HomeCtrl'
  })/*.
  state('articles', {
    templateUrl: '/books_listing',
    controller: 'ArticlesCtrl'
  }).
  state('article', {
    templateUrl: '/readings',
    controller: 'ArticleCtrl'
  });*/
});

