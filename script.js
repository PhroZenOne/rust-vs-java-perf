import http from 'k6/http';
import exec from 'k6/execution';
import { check, sleep } from 'k6';

export const options = {
  executor: 'ramping-arrival-rate',
  thresholds: {
    http_req_failed: [{
      threshold: 'rate<0.000001',
      abortOnFail: true,
    }], 
  },
  stages: [
    { duration: '2m', target: 20000 },
  ],
};

export default function() {
  const res = http.get('http://18.196.63.38/?start=0&end=10000');
  const nr = res.json().random_number;
  check(nr, {
    'is in range': (s) => nr < 10000 && nr >= 0
  });
  sleep(1);
}
