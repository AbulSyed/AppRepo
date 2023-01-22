package com.syed.reposervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RepoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepoServiceApplication.class, args);
	}

}
