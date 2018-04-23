package com.example.demo.query.sp;

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
@RequestMapping("/sp")
public class SpController {

    private static final String DESTINATION_SEND= "ART.SPQuery.REQ";
    private static final String DESTINATION_RECEIVE_GESI = "ART.SPQuery.GESI.REP";
    private static final String DESTINATION_RECEIVE_AVI = "ART.SPQuery.AVI.REP";

    private static final Map<String, String> originResponseQueues;
    static
    {
        originResponseQueues = new HashMap<>();
        originResponseQueues.put(Origins.GESI.name(), DESTINATION_RECEIVE_GESI);
        originResponseQueues.put(Origins.AVI.name(), DESTINATION_RECEIVE_AVI);
    }

    @Autowired
    private Tibco tibco;

    @GetMapping("/{origin}")
    public String sp(@PathVariable("origin") String origin, HttpServletRequest request){
        Long communicationId = (long)Math.random()*1000;
        tibco.getJmsTemplate().send(DESTINATION_SEND, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                SpInputDto dtoRequest = new SpInputDto(1L, origin, new Date(), "123456");
                Message message = session.createObjectMessage(dtoRequest);
                message.setLongProperty("communicationId", communicationId);
                return message;
            }
        });

        String message = null;

        Message msg = tibco.getJmsTemplate().receive(originResponseQueues.get(origin));
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
