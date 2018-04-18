package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@SpringBootApplication
@RestController
@RequestMapping("enel")
public class DemoApplication {

//	private int messageNumber = 0;

//	@GetMapping
//	public void sendTest() throws JMSException {
//		TextMessage msg;
//
//		msg = tibco.getSession().createTextMessage();
//
//		/* set message text */
//		msg.setText("HOLA DESDE ENEL " + messageNumber++);
//
//		tibco.getMsgProducer().send(msg);
//	}

//	@Autowired
//	private Tibco tibco;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
