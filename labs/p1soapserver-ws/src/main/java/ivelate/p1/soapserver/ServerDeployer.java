package ivelate.p1.soapserver;

import javax.xml.ws.Endpoint;

public class ServerDeployer 
{
	public static void main(String[] args)
	{
		Endpoint.publish("http://localhost:8081/todoSoap", new TodoWebService());
	}
}
