package com.example.demo;

import com.tibco.tibjms.TibjmsConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Tibco {

    private static final String DESTINATION_RECEIVE = "gesi";
    private static final String DESTINATION_SEND = "enel";

    private Connection connection  = null;
    private Session session = null;
    private MessageProducer msgProducer = null;
    private Destination destinationSend = null;
    private static final int ackMode = Session.AUTO_ACKNOWLEDGE;

    private MessageConsumer msgConsumer = null;
    private Destination destinationReceive = null;



    public Session getSession() {
        return session;
    }

    public Tibco() throws JMSException {
        ConnectionFactory factory = new TibjmsConnectionFactory("tcp://localhost:7222");
        connection = factory.createConnection(null,null);
        session = connection.createSession(ackMode);
        destinationSend = session.createQueue(DESTINATION_SEND);
        msgProducer = session.createProducer(destinationSend);

        destinationReceive = session.createQueue(DESTINATION_RECEIVE);
        msgConsumer = session.createConsumer(destinationReceive);
        connection.start();
        while(true){
			Message m = msgConsumer.receive();
            TextMessage msg;

            msg = session.createTextMessage();

            /* set message text */
            msg.setText("HOLA DESDE ENEL. MENSAJE RECIBIDO " + ((TextMessage)m).getText());

            msgProducer.send(msg);
		}
//        connection.close();
    }

    public MessageProducer getMsgProducer() {
        return msgProducer;
    }
}
