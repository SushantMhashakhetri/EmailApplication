package com.email.sushant;

//import com.email.sushant.Services.EmailService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;

@SpringBootTest
class SushantApplicationTests {

//	@Autowired
//	private EmailService emailService;
//
//	@Test
//	void sendMailTest() {
//		System.out.println("running dfsdfsd");
//		emailService.send("sushantmhashakhetri3@gmail.com","Testing","Hi Good Morning");
//
//	}
//	@Test
//	void sendWithHtml() {
//		System.out.println("running dfsdfsd");
//		String html =
//				"<h1 style='color:red;border:1px solid red'>Hello Good Morning</h1>";
//		emailService.sendWithHtml("sushantmhashakhetri3@gmail.com","Good Morning",html);
//	}
//	@Test
//	void sendWithFile() {
//		System.out.println("running dfsdfsd");
//
//		emailService.sendWithFile("sushantmhashakhetri3@gmail.com","Good Morning","GoodMorning",
//				new File("/home/pratiti/IdeaProjects/sushant/src/main/resources/static/pic.png"));
//	}	@Test
//	void sendWithFileStream() {
//		System.out.println("running dfsdfsd");
//		File file = new File("/home/pratiti/IdeaProjects/sushant/src/main/resources/static/pic.png");
//        try {
//            InputStream is = new FileInputStream(file);
//			emailService.sendWithFileWithStream("sushantmhashakhetri3@gmail.com","Good Morning","GoodMorning",is);
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
