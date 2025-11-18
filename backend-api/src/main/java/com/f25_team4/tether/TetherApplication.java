/*
 * TetherApplication
 * -----------------
 * Spring Boot entry point for the backend API. Run this class to start the REST server.
 */

package com.f25_team4.tether;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TetherApplication.class, args);
	}

}
