tf_idf.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
  $stateProvider.state('overview ', {
    url: '/overview',
    templateUrl: '/templates/overview.html'
  })
});
