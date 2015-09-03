package ivelate.p3.websocket;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.DeploymentException;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.tyrus.server.Server;


public class DeployServer 
{
	private static final Logger LOGGER = Grizzly.logger(DeployServer.class);
	
	public static void main(String[] args) {
		LOGGER.setLevel(Level.FINER);
		
		Server server=new Server("localhost",8081,"",new HashMap<String,Object>(), TodoWebSocketService.class);
		try {
			server.start();
			LOGGER.info("Press enter to shutdown now the server...");
			System.in.read();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		} finally {
			server.stop();
			LOGGER.info("Server stopped");
		}
	}
}
