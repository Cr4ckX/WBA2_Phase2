package webservice;


import javax.swing.JOptionPane;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

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
