package com.xichoo.finax;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class FinaxApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(new SimpleHash("MD5", "admin", "admin", 1).toString());
		System.out.println(DigestUtils.md5DigestAsHex("adminadmin".getBytes()));
	}

}
