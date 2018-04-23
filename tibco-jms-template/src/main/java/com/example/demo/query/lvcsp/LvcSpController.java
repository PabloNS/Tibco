package com.example.demo.query.lvcsp;

import com.example.demo.Origins;
import com.example.demo.Tibco;
import com.example.demo.query.lvc.LvcInputDto;
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
@RequestMapping("/lvcsp")
public class LvcSpController {

    private static final String DESTINATION_SEND = "ART.LVCSPQuery.REQ";
    private static final String DESTINATION_RECEIVE_SAC = "ART.LVCSPQuery.SAC.REP";

    private static final Map<String, String> originResponseQueues;
    static
    {
        originResponseQueues = new HashMap<>();
        originResponseQueues.put(Origins.SAC.name(), DESTINATION_RECEIVE_SAC);
    }

    @Autowired
    private Tibco tibco;

    @GetMapping("/{origin}")
    public String lvcsp(@PathVariable("origin") String origin, HttpServletRequest request){
        tibco.getJmsTemplate().send(DESTINATION_SEND, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                LvcSpInputDto dtoRequest = new LvcSpInputDto(1L, origin, new Date(), "CAT", 1);
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
