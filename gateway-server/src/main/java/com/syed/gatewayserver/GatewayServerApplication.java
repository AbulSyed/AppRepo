package com.syed.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				// AUTH SERVICE
				.route(p -> p
						.path("/API_GATEWAY/authservice/**")
						.filters(f -> f.rewritePath("/API_GATEWAY/authservice/(?<segment>.*)","/${segment}"))
						// redirect request with auth microservice registered on eureka server
						.uri("lb://AUTHSERVICE"))
				// REPO SERVICE
				.route(p -> p
						.path("/API_GATEWAY/reposervice/**")
						.filters(f -> f.rewritePath("/API_GATEWAY/reposervice/(?<segment>.*)","/${segment}"))
						.uri("lb://REPOSERVICE"))
				// FEEDBACK SERVICE
				.route(p -> p
						.path("/API_GATEWAY/feedbackservice/**")
						.filters(f -> f.rewritePath("/API_GATEWAY/feedbackservice/(?<segment>.*)","/${segment}"))
						.uri("lb://FEEDBACKSERVICE")).build();
	}
}
