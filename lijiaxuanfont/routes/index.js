var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});
router.get('/order', function(req, res, next) {
  res.render('order', { title: 'Express' });
});
router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Express' });
});
router.get('/demo', function(req, res, next) {
  res.render('demo', { title: 'Express' });
});
router.get('/menu', function(req, res, next) {
  res.render('menu', { title: 'Express' });
});
router.get('/user', function(req, res, next) {
  res.render('user', { title: 'Express' });
});
module.exports = router;
