package com.example.demo;

import com.tibco.tibjms.TibjmsConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Tibco {

    private Connection connection  = null;
    private Session session = null;

    private static final int ackMode = Session.AUTO_ACKNOWLEDGE;

    public Tibco() throws JMSException {
        ConnectionFactory factory = new TibjmsConnectionFactory("tcp://localhost:7222");
        connection = factory.createConnection(null, null);
        session = connection.createSession(ackMode);
        connection.start();
    }

    public Session getSession() {
        return session;
    }
}
