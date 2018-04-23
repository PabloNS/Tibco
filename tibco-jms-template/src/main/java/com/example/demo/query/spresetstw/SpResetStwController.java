package com.example.demo.query.spresetstw;

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
@RequestMapping("/spresetstw")
public class SpResetStwController {

    private static final String DESTINATION_SEND = "ART.SPResetSTW.REQ";
    private static final String DESTINATION_RECEIVE_GESI = "ART.SPResetSTW.GESI.REP";

    private static final Map<String, String> originResponseQueues;
    static
    {
        originResponseQueues = new HashMap<>();
        originResponseQueues.put(Origins.GESI.name(), DESTINATION_RECEIVE_GESI);
    }

    @Autowired
    private Tibco tibco;

    @GetMapping("/{origin}")
    public String spresetstw(@PathVariable("origin") String origin, HttpServletRequest request){
        tibco.getJmsTemplate().send(DESTINATION_SEND, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                SpResetStwInputDto dtoRequest = new SpResetStwInputDto(1L, origin, new Date(), "123456");
                return session.createObjectMessage(dtoRequest);
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