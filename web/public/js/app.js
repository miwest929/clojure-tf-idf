angular.module('tf-idf', [
  'ui.router',
  'tf-idf.controllers'
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
  }).
  state('article', {
    url: '/article/:id',
    templateUrl: '/article/:id',
    controller: 'ArticleCtrl'
  })
});

