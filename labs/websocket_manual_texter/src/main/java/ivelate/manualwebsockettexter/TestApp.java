package ivelate.manualwebsockettexter;


	import java.net.URI;
	import java.net.URISyntaxException;
import java.util.Scanner;

import ivelate.manualwebsockettexter.WebsocketClientEndpoint.MessageHandler;

	public class TestApp {

	    public static void main(String[] args) {
	    	for(int i=0;i<1;i++){
	    		final int id=i;
	    	Thread t=new Thread(){
	    		@Override public void run()
	    		{
	    			try {
	    	            // add listener
	    	            MessageHandler ms=new WebsocketClientEndpoint.MessageHandler() {
	    	                public void handleMessage(String message) {
	    	                    System.out.println(id+"--> "+message);
	    	                }
	    	            };
	    	            
	    	            // open websocket
	    	            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8081/todoWebSocketService"),ms);

	    	            // send message to websocket
	    	            //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

	    	            // wait 5 seconds for messages from websocket
	    	            
	    	            synchronized(clientEndPoint) {if(!clientEndPoint.isClosed()) clientEndPoint.wait();}

	    	        } catch (InterruptedException ex) {
	    	            System.err.println("InterruptedException exception: " + ex.getMessage());
	    	        } catch (URISyntaxException ex) {
	    	            System.err.println("URISyntaxException exception: " + ex.getMessage());
	    	        }
	    		}
	    	};
	    	t.start();
	    	}
	    	
	    	final int id=7;
	    			try {
	    	            // add listener
	    	            MessageHandler ms=new WebsocketClientEndpoint.MessageHandler() {
	    	                public void handleMessage(String message) {
	    	                    System.out.println(id+"--> "+message);
	    	                }
	    	            };
	    	            
	    	            // open websocket
	    	            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8081/todoWebSocketService"),ms);

	    	            // send message to websocket
	    	            //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");
	    	            // wait 5 seconds for messages from websocket
	    	            Scanner s=new Scanner(System.in);
	    	            while(true) clientEndPoint.sendMessage(s.nextLine());
	    	            
	    	        } catch (URISyntaxException ex) {
	    	            System.err.println("URISyntaxException exception: " + ex.getMessage());
	    	        }
	    }
	}
