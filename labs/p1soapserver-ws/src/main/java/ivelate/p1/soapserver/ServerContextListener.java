package ivelate.p1.soapserver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerContextListener implements ServletContextListener {

	    @Override
	    /**
	     * When WAR is destroyed JSON file will be saved in disk
	     */
	    public void contextDestroyed(ServletContextEvent event) {
	        ToDoFileManager.saveFile();
	    }

	    @Override
	    /**
	     * When WAR file is deployed JSON file will be read from disk
	     */
	    public void contextInitialized(ServletContextEvent event) {
	        ToDoFileManager.readFile();
	    }
	}
