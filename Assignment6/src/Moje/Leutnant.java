package Moje;

import Moje.MessageRabbitMq.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

public class Leutnant {

    public User nachricht;
    Stack<String> post = new Stack<>();

    ArrayList<String> dorList = new ArrayList<>();
    ArrayList<String> odeList = new ArrayList<>();

    public Leutnant() throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        nachricht = new User();
    }

    public void messageBekommen() throws InterruptedException, ExecutionException, IOException {
        String nach = nachricht.consume();
        System.out.println("Leutnant got: -> " + nach);
    }

    public void botenWeiterleiten() throws IOException, InterruptedException {

    }

    public String Angriff() {

        return null;
    }
}
