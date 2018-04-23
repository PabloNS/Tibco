package com.example.demo.query.spresetstw;

import com.example.demo.Tibco;
import com.example.demo.query.QueryListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class SpResetStwComponent {

    private Tibco tibco;

    private static final String DESTINATION_RECEIVE = "ART.SPResetSTW.REQ";
    private static final String DESTINATION_SEND_GESI = "ART.SPResetSTW.GESI.REP";

    private MessageConsumer msgConsumer = null;
    private Destination destinationReceive = null;

    private MessageProducer msgProducerGesi = null;
    private Destination destinationSendGesi = null;

    public SpResetStwComponent(Tibco tibco) throws JMSException {
        this.tibco = tibco;
        destinationReceive = getTibco().getSession().createQueue(DESTINATION_RECEIVE);
        msgConsumer = getTibco().getSession().createConsumer(destinationReceive);
        destinationSendGesi = getTibco().getSession().createQueue(DESTINATION_SEND_GESI);
        msgProducerGesi = getTibco().getSession().createProducer(destinationSendGesi);

        QueryListener listener = new QueryListener(){
            @Override
            public void onMessage(Message message) {
                SpResetStwInputDto objectReceived = null;
                String origin = null;
                try {
                    objectReceived = (SpResetStwInputDto) ((ObjectMessage)message).getObject();
                    origin = objectReceived.getFendArtSPResetSTWRequest().getRequestSystem();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                TextMessage txtMessage = null;
                try {
                    txtMessage = getTibco().getSession().createTextMessage(objectReceived.toString());
                    txtMessage.setLongProperty("communicationId", message.getLongProperty("communicationId"));
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
