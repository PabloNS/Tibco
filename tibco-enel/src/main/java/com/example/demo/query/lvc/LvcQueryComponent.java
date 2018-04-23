package com.example.demo.query.lvc;

import com.example.demo.Tibco;
import com.example.demo.query.QueryListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class LvcQueryComponent {

    private Tibco tibco;

    private static final String DESTINATION_RECEIVE = "ART.LVCQuery.REQ";
    private static final String DESTINATION_SEND_GESI = "ART.LVCQuery.GESI.REP";
    private static final String DESTINATION_SEND_AVI = "ART.LVCQuery.AVI.REP";
    private static final String DESTINATION_SEND_SAC = "ART.LVCQuery.SAC.REP";

    private MessageConsumer msgConsumer = null;
    private Destination destinationReceive = null;

    private MessageProducer msgProducerGesi = null;
    private MessageProducer msgProducerAvi = null;
    private MessageProducer msgProducerSac = null;
    private Destination destinationSendGesi = null;
    private Destination destinationSendAvi = null;
    private Destination destinationSendSac = null;

    public LvcQueryComponent(Tibco tibco) throws JMSException {
        this.tibco = tibco;
        destinationReceive = getTibco().getSession().createQueue(DESTINATION_RECEIVE);
        msgConsumer = getTibco().getSession().createConsumer(destinationReceive);
        destinationSendGesi = getTibco().getSession().createQueue(DESTINATION_SEND_GESI);
        destinationSendAvi = getTibco().getSession().createQueue(DESTINATION_SEND_AVI);
        destinationSendSac = getTibco().getSession().createQueue(DESTINATION_SEND_SAC);
        msgProducerGesi= getTibco().getSession().createProducer(destinationSendGesi);
        msgProducerAvi = getTibco().getSession().createProducer(destinationSendAvi);
        msgProducerSac = getTibco().getSession().createProducer(destinationSendSac);

        QueryListener listener = new QueryListener(){
                @Override
                public void onMessage(Message message) {
                    LvcInputDto objectReceived = null;
                    String origin = null;
                    try {
                        objectReceived = (LvcInputDto) ((ObjectMessage)message).getObject();
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
                            case "GESI":
                                msgProducerGesi.send(txtMessage);
                                break;
                            case "AVI":
                                msgProducerAvi.send(txtMessage);
                                break;
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
