package com.example.demo;

import com.tibco.tibjms.TibjmsConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class Tibco {

    private JmsTemplate jmsTemplate;

    public Tibco() throws JMSException {
        jmsTemplate = new JmsTemplate();
        TibjmsConnectionFactory cf = new TibjmsConnectionFactory();
        cf.setServerUrl("tcp://localhost:7222");
        jmsTemplate.setConnectionFactory(cf);
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
}
