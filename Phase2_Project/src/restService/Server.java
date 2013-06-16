package restService;

import javax.swing.JOptionPane;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
/**
 * Hierbei handelt es sich um den REST-Server. Dieser wird benštigt um die HTTP-Anfragen
 * zu verarbeiten. Der Server sucht das Projekt nach Klassen mit HTTP Methoden ab und
 * realisiert dann die sich dort befindlichen Funktionen.
 * @author CrackX
 *
 */
public class Server {

	//Sucht im Projekt nach Rest-Services
	public static void main(String[] args) throws Exception{

		HttpServer server = HttpServerFactory.create("http://localhost:8080/");
		server.start();

		Thread.sleep(1000000);
		JOptionPane.showMessageDialog(null, "Ende");
		server.stop(0);

	}

}
