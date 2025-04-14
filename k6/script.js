        import http from 'k6/http';
        import { check, sleep } from 'k6';

        // Define the base URL of your API
        const BASE_URL = __ENV.BASE_URL || 'http://orderservice.codersandbox.com';

        // Define the options for your test
        export let options = {
            stages: [
                { duration: '5s', target: 1 }, 
                { duration: '45m', target: 2 }, 
                { duration: '5s', target: 1 }  
            ],
        };

        // Define the main function that represents your test scenario
        export default function () {
            let body;
            let headers;
            let response;

            body = {
                "bookId": "cleancode",
                "quantity": 2
            };

            headers = {
                'Content-Type': 'application/json',
            };
            response = http.post(`${BASE_URL}/orders`, JSON.stringify(body), { headers: headers });

            check(response, {
                'Status 200': (r) => r.status === 200,
            });

            sleep(3);
        }