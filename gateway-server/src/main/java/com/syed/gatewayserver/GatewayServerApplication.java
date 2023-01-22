package com.syed.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						// predicate bool (if path matches)
						.path("/API_GATEWAY/authservice/**")
						/* accepts /API_GATEWAY/authservice/...
						 * but replace with /...
						 * e.g. /API_GATEWAY/authservice/hello/1 will convert to /hello/1 */
						.filters(f -> f.rewritePath("/API_GATEWAY/authservice/(?<segment>.*)","/${segment}")
								// adding response header
								.addResponseHeader("X-Response-Time", new Date().toString()))
						// redirect request with account microservice registered on eureka server
						.uri("lb://AUTHSERVICE"))
				.route(p -> p
						.path("/API_GATEWAY/reposervice/**")
						.filters(f -> f.rewritePath("/API_GATEWAY/reposervice/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", new Date().toString()))
						.uri("lb://REPOSERVICE")).build();
	}
}
