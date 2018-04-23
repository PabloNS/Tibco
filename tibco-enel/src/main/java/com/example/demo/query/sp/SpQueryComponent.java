package com.example.demo.query.sp;

import com.example.demo.Tibco;
import com.example.demo.query.QueryListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class SpQueryComponent {

    private Tibco tibco;

    private static final String DESTINATION_RECEIVE= "ART.SPQuery.REQ";
    private static final String DESTINATION_SEND_GESI = "ART.SPQuery.GESI.REP";
    private static final String DESTINATION_SEND_AVI = "ART.SPQuery.AVI.REP";

    private MessageConsumer msgConsumer = null;
    private Destination destinationReceive = null;
    private Destination destinationSendGesi = null;
    private Destination destinationSendAvi = null;

    private MessageProducer msgProducerGesi = null;
    private MessageProducer msgProducerAvi = null;

    public SpQueryComponent(Tibco tibco) throws JMSException {
        this.tibco = tibco;
        destinationReceive = getTibco().getSession().createQueue(DESTINATION_RECEIVE);
        destinationSendGesi = getTibco().getSession().createQueue(DESTINATION_SEND_GESI);
        destinationSendAvi = getTibco().getSession().createQueue(DESTINATION_SEND_AVI);
        msgConsumer = getTibco().getSession().createConsumer(destinationReceive);
        msgProducerGesi = getTibco().getSession().createProducer(destinationSendGesi);
        msgProducerAvi = getTibco().getSession().createProducer(destinationSendAvi);

        QueryListener listener = new QueryListener(){
            @Override
            public void onMessage(Message message) {
                SpInputDto objectReceived = null;
                String origin = null;
                try {
                    objectReceived = (SpInputDto) ((ObjectMessage)message).getObject();
                    origin = objectReceived.getFendArtSpQueryRequest().getRequestSystem();
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
                        case "AVI":
                            msgProducerAvi.send(txtMessage);
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
