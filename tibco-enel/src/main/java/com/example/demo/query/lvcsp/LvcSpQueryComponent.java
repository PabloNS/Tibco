package com.example.demo.query.lvcsp;

import com.example.demo.Tibco;
import com.example.demo.query.QueryListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class LvcSpQueryComponent {

    private Tibco tibco;

    private static final String DESTINATION_RECEIVE = "ART.LVCSPQuery.REQ";
    private static final String DESTINATION_SEND_SAC = "ART.LVCSPQuery.SAC.REP";

    private MessageConsumer msgConsumer = null;
    private Destination destinationReceive = null;

    private MessageProducer msgProducerSac = null;
    private Destination destinationSendSac = null;

    public LvcSpQueryComponent(Tibco tibco) throws JMSException {
        this.tibco = tibco;
        destinationReceive = getTibco().getSession().createQueue(DESTINATION_RECEIVE);
        msgConsumer = getTibco().getSession().createConsumer(destinationReceive);
        destinationSendSac = getTibco().getSession().createQueue(DESTINATION_SEND_SAC);
        msgProducerSac = getTibco().getSession().createProducer(destinationSendSac);

        QueryListener listener = new QueryListener(){
            @Override
            public void onMessage(Message message) {
                LvcSpInputDto objectReceived = null;
                String origin = null;
                try {
                    objectReceived = (LvcSpInputDto) ((ObjectMessage)message).getObject();
                    origin = objectReceived.getFendArtLVCQueryRequest().getRequestSystem();
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
                        case "SAC":
                            msgProducerSac.send(txtMessage);
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
