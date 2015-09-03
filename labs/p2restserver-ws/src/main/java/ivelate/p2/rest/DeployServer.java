package ivelate.p2.rest;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;


public class DeployServer 
{
	private static final Logger LOGGER = Grizzly.logger(DeployServer.class);
	
	public static void main(String[] args) {
		LOGGER.setLevel(Level.FINER);
		
		URI uri = UriBuilder.fromUri("http://localhost/").port(8081).build();
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
				uri, 
				new ResourceConfig().register(TodoRestService.class));
		try {
			server.start();
			LOGGER.info("Press enter to shutdown now the server...");
			System.in.read();
		} catch (IOException ioe) {
			LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
		} finally {
			server.stop();
			LOGGER.info("Server stopped");
		}
	}
}
