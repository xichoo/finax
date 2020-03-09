package com.xichoo.finax;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinaxApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new SimpleHash("MD5", "admin", "admin", 1).toString());
	}

}
