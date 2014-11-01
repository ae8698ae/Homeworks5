package newTestRabbitMW;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by jm on 11/1/2014.
 */
public class Both {
    public static final String ExchangevarNameQue = "Aufgabe5Chat";

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://test:test@b40.cz:5672");
        Connection connection = factory.newConnection();
        /*
        Part of prod
         */
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("chat", "fanout");
        channel.queueDeclare(ExchangevarNameQue, false, false, false, null);

        /*
        Part of consumer
         */
        Channel channeltoBind = connection.createChannel();
        channeltoBind.queueBind(ExchangevarNameQue, "chat", "");
        QueueingConsumer qr = new QueueingConsumer(channeltoBind);
        channeltoBind.basicConsume(ExchangevarNameQue, true, qr);

        while (true) {
            String yourmsg = sc.next();
            channel.basicPublish("chat", "", null, yourmsg.getBytes());

            QueueingConsumer.Delivery delivery = qr.nextDelivery();
            String delMsg = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();
            System.out.println(" [x] Received '" + routingKey + "':'" + delMsg + "'");

        }

    }
}
