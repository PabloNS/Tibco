package com.example.demo.query.lvc;

import com.example.demo.Origins;
import com.example.demo.Tibco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lvc")
public class LvcController {

    private static final String DESTINATION_SEND = "ART.LVCQuery.REQ";
    private static final String DESTINATION_RECEIVE_GESI = "ART.LVCQuery.GESI.REP";
    private static final String DESTINATION_RECEIVE_AVI = "ART.LVCQuery.AVI.REP";
    private static final String DESTINATION_RECEIVE_SAC = "ART.LVCQuery.SAC.REP";

    private static final Map<String, String> originResponseQueues;
    static
    {
        originResponseQueues = new HashMap<>();
        originResponseQueues.put(Origins.GESI.name(), DESTINATION_RECEIVE_GESI);
        originResponseQueues.put(Origins.AVI.name(), DESTINATION_RECEIVE_AVI);
        originResponseQueues.put(Origins.SAC.name(), DESTINATION_RECEIVE_SAC);
    }

    @Autowired
    private Tibco tibco;

    @GetMapping("/{origin}")
    public String lvc(@PathVariable("origin") String origin, HttpServletRequest request){
        Long communicationId = (long)Math.random()*1000;
        tibco.getJmsTemplate().send(DESTINATION_SEND, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                LvcInputDto dtoRequest = new LvcInputDto(1L, origin, new Date(), "CAT", 1);
                Message message = session.createObjectMessage(dtoRequest);
                message.setLongProperty("communicationId", communicationId);
                return message;
            }
        });

        String message = null;

        Message msg = tibco.getJmsTemplate().receiveSelected(originResponseQueues.get(origin),
                "communicationId = " + communicationId);
        if(msg instanceof TextMessage) {
            try {
                message = ((TextMessage) msg).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        return message;
    }
}
