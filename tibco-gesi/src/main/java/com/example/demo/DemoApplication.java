package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@SpringBootApplication
@RestController
@RequestMapping("gesi")
public class DemoApplication {

	@Autowired
	private Tibco tibco;

//	@GetMapping
//	public String test() throws JMSException {
////		while(true){
////			Message m = tibco.getMsgConsumer().receive();
////			System.out.println(((TextMessage)m).getText());
////		}
//		Message m = tibco.getMsgConsumer().receive();
//
//		tibco.getConnection().close();
//
//		return ((TextMessage)m).getText();
//	}

	@GetMapping("/{message}")
	public String test(@PathVariable("message") String mensaje) throws JMSException {
//		tibco.getConnection().start();

		TextMessage msg;

		msg = tibco.getSession().createTextMessage();

		/* set message text */
		msg.setText(mensaje);

		tibco.getMsgProducer().send(msg);

		Message m = tibco.getMsgConsumer().receive();

//		tibco.getConnection().close();

		return ((TextMessage)m).getText();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
