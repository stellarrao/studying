package javaexercise.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

class JmsSender
{
    public static void sendOperation()
    {
        // ConnectionFactory:连接工厂,JMS 用它创建连接
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer producer;

        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
        try
        {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            producer = session.createProducer(destination);
            //TextMessage message = session.createTextMessage("ActiveMq 发送的第三条消息");
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //producer.send(message);
            sendMessage(session, producer);
            session.commit();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {

            try
            {
                if (null != connection)
                {
                    connection.close();
                }
            } catch (JMSException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private static void sendMessage(Session session, MessageProducer producer)  
            throws Exception {  
        for (int i = 1; i <= 10; i++) {  
            TextMessage message = session.createTextMessage("ActiveMq 发送的消息"  
                    + i);  
           
  
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);  
            producer.send(message);  
        }  
    }  
}

class JmsReceiver
{
    public static void receiveOperation()
    {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer consumer;

        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");

        try
        {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("FirstQueue");
            consumer = session.createConsumer(destination);

            while (true)
            {
                TextMessage message = (TextMessage) consumer.receive(100000);
                if (null != message)
                {
                    System.out.println("收到消息: " + message.getText());
                } else
                {
                    break;
                }
            }
        } catch (JMSException e)
        {
            e.printStackTrace();
            
        } finally
        {
            try
            {
                if (null != connection)
                {
                    connection.close();
                }
            } catch (JMSException e)
            {
                e.printStackTrace();
            }
        }
    }
}

public class SimpleJms
{
    private static void createThread() {
        
        Thread producer = new Thread("Producer") {

            @Override
            public void run()
            {
               JmsSender.sendOperation();
            }
            
        };
        
        Thread consumer = new Thread("Consumer") {
            @Override
            public void run()
            {
                JmsReceiver.receiveOperation();
            }  
        };

        
        producer.start();
        
        consumer.start();
    }
    
    public static void main(String[] args)
    {
        createThread();
        
//        JmsSender.sendOperation();
//        JmsReceiver.receiveOperation();
    }
}
