package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DemoApplication {

    static final Random random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RestController
    public static class HelloController {
        @GetMapping("/")
        public Response HelloWorld(@RequestParam("start") Integer start, @RequestParam("end") Integer end) {
            return new Response(random.nextInt(start, end));
        }
    }

	public record Response(@JsonProperty("random_number") Integer randomNumber) {

		public Response(Integer randomNumber) {
			this.randomNumber = randomNumber;
		}
	}
}