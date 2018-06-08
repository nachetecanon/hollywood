function() {
  var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'local';
  }
  var config = { // base config
    env: env,
    client_id: 'hollywood',
    realm: 'PI',
    urlBase: 'http://localhost:8080/api',
    urlBase1: 'http://localhost:8076/api'

  };
  if (env == 'dev') {
    config.username = 'hollywood.test';
    config.keycloakUrl = 'https://login-test-2.pibenchmark.com/auth';
    config.urlBase = 'https://hollywood-dev.pibenchmark.com/api';
    config.urlBase1 = 'http://localhost:8076/api';
    config.username = 'hollywood.be';
    config.password = 'test';

  } else if (env == 'qa') {
    config.keycloakUrl = 'https://login-test-2.pibenchmark.com/auth';
    config.urlBase = 'https://hollywood-qa.pibenchmark.com/api';
    config.urlBase1 = 'http://localhost:8076/api';
    config.username = 'hollywood.be';
    config.password = 'test';
  }
    else if (env == 'local') {
    config.keycloakUrl = 'https://login-test-2.pibenchmark.com/auth';
    config.urlBase = 'http://localhost:8075/api';
    config.urlBase1 = 'http://localhost:8076/api';
    config.username = 'hollywood.be';
    config.password = 'test';
    }


  // don't waste time waiting for a connection or if servers don't respond within 5 seconds
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  return config;

};
