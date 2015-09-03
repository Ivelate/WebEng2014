package socketmsg;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class SendMsg 
{
	public static void main(String[] args) throws IOException
	{
		/*final AtomicInteger ai=new AtomicInteger(0);
		for(int i=0;i<200;i++)
		{
			final int arg=i;
			Thread t=new Thread(){
				@Override
				public void run()
				{
					int id=arg;
					try{
					while(true){
						Socket s=new Socket("localhost",8081);
						PrintStream ps=new PrintStream(s.getOutputStream());
						ps.println("GET todoRestService HTTP/1.0");
						ps.println("Accept: application/json");
						ps.println("Host: "+s.getInetAddress());
						ps.println();
						
						Scanner sc=new Scanner(s.getInputStream());
						String buf="";
						while(sc.hasNextLine()){
							buf=buf+sc.nextLine();
						}
						//System.out.println(id+" success "+ai.incrementAndGet());
						int i=ai.incrementAndGet();
						if(i%100==0) System.out.println(i);
					}
					}catch(Exception e){System.out.println("MEMORI "+id);}
				}
			};
			t.start();
		}*/
		
		
		Socket s=new Socket("localhost",8081);
		PrintStream ps=new PrintStream(s.getOutputStream());
		ps.println("DELETE todoRestService/pikachuMala HTTP/1.0");
		ps.println("Accept: application/json");
		ps.println("Host: "+s.getInetAddress());
		ps.println();
						
		Scanner sc=new Scanner(s.getInputStream());
		String buf="";
		while(sc.hasNextLine()){
			buf=buf+sc.nextLine()+"\n";
		}
		System.out.println(buf);
	
	}
}
