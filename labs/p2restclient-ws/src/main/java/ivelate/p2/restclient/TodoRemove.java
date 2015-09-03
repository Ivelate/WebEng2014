package ivelate.p2.restclient;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/todoRemove" })
public class TodoRemove extends HttpServlet 
{
	
	@Override
	public void init() throws ServletException 
	{
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String task = req.getParameter("task_text");
		HttpResponse<JsonNode> removed=null;
		try {
			removed = Unirest.delete("http://localhost:8081/todoRestService/"+task).asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		//Writes response
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.print("<html><head><title>Removing ToDo</title></head>"
				+ "<body><h1> ToDo with task "+task+" has "+((removed!=null&&removed.getStatus()==200)?"removed succesfully":"not been found in the ToDo database")+" </h1>");
		
		out.println("</body></html>");
	}
}
