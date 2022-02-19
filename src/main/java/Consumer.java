import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("saisir un code :");
        String code=scanner.nextLine();
        try {   ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //Destination dest = session.createQueue("enset.queue");
            Destination dest =session.createTopic("enset.topic");
            MessageConsumer consumer = session.createConsumer(dest,"code='"+code+"'");
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage) {
                        try {
                            TextMessage textMessage =(TextMessage) message ;
                            System.out.println("Reception du message : "+textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace(); } }} });
        } catch (JMSException e) { e.printStackTrace(); }}}
