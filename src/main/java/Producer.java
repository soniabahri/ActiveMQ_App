import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.util.Scanner;
public class Producer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("envoyer vers :");
        String code=scanner.nextLine();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = null;
        try {   connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //Destination dest = session.createQueue("enset.queue");
            Destination dest =session.createTopic("enset.topic");
            MessageProducer producer = session.createProducer(dest);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(" helloooo i'm Khalil Dridi  !  ");
            textMessage.setStringProperty("code",code);
            producer.send(textMessage);
            System.out.println(" Envoi du message .. ");
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace(); }};}
