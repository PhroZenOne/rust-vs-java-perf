import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  executor: 'ramping-arrival-rate',
  stages: [
    { duration: '1m', target: 5000 },
  ],
};

export default function() {
  const res = http.get('http://localhost:8080/?start=0&end=10000');
  const nr = res.json().random_number;
  check(nr, {
    'is in range': (s) => nr < 10000 && nr >= 0
  });
  sleep(1);
}
