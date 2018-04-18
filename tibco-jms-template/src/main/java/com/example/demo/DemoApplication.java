package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

@SpringBootApplication
@RestController
@RequestMapping("gesi")
public class DemoApplication {

	@Autowired
	private Tibco tibco;

	private static final String DESTINATION_SEND = "gesi";
	private static final String DESTINATION_RECEIVE = "enel";

	@GetMapping("/{message}")
	public String test(@PathVariable("message") String mensaje){
		tibco.getJmsTemplate().send(DESTINATION_SEND, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(mensaje);
			}
		});

		String message = null;

		Message msg = tibco.getJmsTemplate().receive(DESTINATION_RECEIVE);
		if(msg instanceof TextMessage) {
			try {
				message = ((TextMessage) msg).getText();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

		return message;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
