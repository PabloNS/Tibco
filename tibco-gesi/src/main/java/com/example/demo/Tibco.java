package com.example.demo;

import com.tibco.tibjms.Tibjms;
import com.tibco.tibjms.TibjmsConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Tibco {

    private static final String DESTINATION_RECEIVE = "enel";
    private static final String DESTINATION_SEND = "gesi";

    private Connection connection = null;
    private Session session = null;
    private MessageConsumer msgConsumer = null;
    private Destination destinationReceive = null;

    private static final int ackMode = Session.AUTO_ACKNOWLEDGE;

    private MessageProducer msgProducer = null;
    private Destination destinationSend = null;



    public Tibco() throws JMSException {
        ConnectionFactory factory = new TibjmsConnectionFactory("tcp://localhost:7222");
        connection = factory.createConnection(null,null);
        session =    connection.createSession(ackMode);
        destinationReceive = session.createQueue(DESTINATION_RECEIVE);
        msgConsumer = session.createConsumer(destinationReceive);
        connection.start();

        destinationSend = session.createQueue(DESTINATION_SEND);
        msgProducer = session.createProducer(destinationSend);
    }

    public Connection getConnection() {
        return connection;
    }

    public Session getSession() {
        return session;
    }

    public Destination getDestinationSend() {
        return destinationSend;
    }

    public MessageProducer getMsgProducer() {
        return msgProducer;
    }

    public MessageConsumer getMsgConsumer() {
        return msgConsumer;
    }
}
