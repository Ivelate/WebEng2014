package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pistachotodo.ListToDoTasks;
import pistachotodo.ToDoList;

/**
 * Servlet implementation class loadServlet
 */
@WebServlet(urlPatterns = { "/loadServlet" })
public class loadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//ToDoList taskList = ListToDoTasks.getTaskList();
		ToDoList taskList = ListToDoTasks.getTaskList();
		System.out.println("ESTOY VIVO");
		if(taskList!=null){
			System.out.println(taskList.getToDoList().get(0).getTask());
			request.setAttribute("taskList", taskList);
			System.out.println("LLEGO HASTA EL DISPATCHER");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			//System.out.println("SER� DESPACHADO ALG�N D�A");
		}
	}

}
