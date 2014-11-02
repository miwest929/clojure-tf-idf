var express = require('express'),
  methodOverride = require('method-override'),
  morgan = require('morgan'),
  http = require('http'),
  pg = require('pg'),
  path = require('path');
var bodyParser = require('body-parser')
var app = module.exports = express();

// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', __dirname + '/views');
app.set('view engine', 'jade');
app.use(morgan('dev'));
app.use(methodOverride());
app.use(bodyParser());
app.use(express.static(path.join(__dirname, 'public')));

var env = process.env.NODE_ENV || 'development';

// development only
if (env === 'development') {
}
else if (env === 'production') {
}

// serve index and view partials
app.get('/', function(req, res) {
  res.render('index');
});

app.get('/article/:id', function(req, res) {
  res.render('article');
});

// redirect all others to the index (HTML5 history)
app.get('*', function(req, res) {
  res.render('index');
});

http.createServer(app).listen(app.get('port'), function () {
  console.log('Express server listening on port ' + app.get('port'));
});
