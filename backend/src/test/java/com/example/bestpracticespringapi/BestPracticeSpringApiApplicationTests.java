package com.example.bestpracticespringapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class BestPracticeSpringApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainMethodExecutesWithoutExceptions() {
		assertDoesNotThrow(() -> BestPracticeSpringApiApplication.main(new String[]{}));
	}

}
