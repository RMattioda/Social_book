package com.mattioda.rodrigo.socialbook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialBookApplication implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(SocialBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
