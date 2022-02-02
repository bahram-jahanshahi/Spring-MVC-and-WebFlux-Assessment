# The Assessment of Spring MVC and WebFlux
This attempt aims to assess Spring MVC and WebFlux in terms of performance.

## Spike of Requests
### Prerequisites
It's needed to have two REST endpoints by MVC and WebFlux. Each endpoint should wait for 100 ms to response.
### Actions
It's supposed to send 100 requests to both of the endpoints and check the time
### Expectations
It's expected to have a considerable reduce of time consumed for the WebFlux