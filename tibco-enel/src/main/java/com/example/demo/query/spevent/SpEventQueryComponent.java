package com.example.demo.query.spevent;

import com.example.demo.Tibco;
import com.example.demo.query.QueryListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class SpEventQueryComponent {

    private Tibco tibco;

    private static final String DESTINATION_RECEIVE = "ART.SPEventQuery.REQ";
    private static final String DESTINATION_SEND_GESI = "ART.SPEventQuery.GESI.REP";

    private MessageConsumer msgConsumer = null;
    private Destination destinationReceive = null;

    private MessageProducer msgProducerGesi = null;
    private Destination destinationSendGesi = null;

    public SpEventQueryComponent(Tibco tibco) throws JMSException {
        this.tibco = tibco;
        destinationReceive = getTibco().getSession().createQueue(DESTINATION_RECEIVE);
        msgConsumer = getTibco().getSession().createConsumer(destinationReceive);
        destinationSendGesi = getTibco().getSession().createQueue(DESTINATION_SEND_GESI);
        msgProducerGesi = getTibco().getSession().createProducer(destinationSendGesi);

        QueryListener listener = new QueryListener(){
            @Override
            public void onMessage(Message message) {
                SpEventInputDto objectReceived = null;
                String origin = null;
                try {
                    objectReceived = (SpEventInputDto) ((ObjectMessage)message).getObject();
                    origin = objectReceived.getFendArtSPEventQueryRequest().getRequestSystem();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                TextMessage txtMessage = null;
                try {
                    txtMessage = getTibco().getSession().createTextMessage(objectReceived.toString());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                try {
                    switch (origin){
                        case "GESI":
                            msgProducerGesi.send(txtMessage);
                            break;
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        };
        msgConsumer.setMessageListener(listener);
    }

    public Tibco getTibco() {
        return tibco;
    }
}
