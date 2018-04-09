package javaexercise.spring4.messaging17;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;

public class AlertServiceImpl implements AlertService
{
    private JmsOperations jmsOperations;

    @Autowired
    public AlertServiceImpl(JmsOperations jmsOperations)
    {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void sendSpittleAlert(final Spittle spittle)
    {
        /*jmsOperations.send("spittle.alert.queue", new MessageCreator()
        {
            public Message createMessage(Session session) throws JMSException
            {
                return session.createObjectMessage(spittle);
            }
        });*/
        
        jmsOperations.convertAndSend(spittle);
    }

    @Override
    public Spittle retrieveSpittleAlert()
    {
/*        try
        {
            ObjectMessage receivedMessage = (ObjectMessage) jmsOperations.receive("spittle.alert.queue");
            return (Spittle) receivedMessage.getObject();
        } catch (JMSException jmsException)
        {
            throw JmsUtils.convertJmsAccessException(jmsException);
        }*/
        Spittle s = new Spittle(-1L, null, "inital", new Date());
        try
        {
            s = (Spittle)jmsOperations.receiveAndConvert();
           //Object o = (Spittle)jmsOperations.receiveAndConvert();
        } catch (JmsException e)
        {
            s = null;
            System.out.println(e.getMessage());
        }
        
        return s;
    }
}
