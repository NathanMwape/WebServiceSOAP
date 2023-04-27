import service.Serveur;

import javax.xml.ws.Endpoint;

public class ServerMain {
    public static void main(String[] args) {
        String url = "http://localhost:8585/";
        Endpoint.publish(url, new Serveur());
        System.out.println("Serveur demarré !");
    }
}