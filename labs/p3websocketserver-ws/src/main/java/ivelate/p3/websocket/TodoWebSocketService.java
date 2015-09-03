package ivelate.p3.websocket;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import ivelate.p3.todo.ToDo;

@ServerEndpoint(value = "/todoWebSocketService")
public class TodoWebSocketService 
{
		private static List<Session> registeredTodoListeners=new LinkedList<Session>();
		
		@OnOpen
		public void onOpen(Session session) throws IOException 
		{
			Gson gson=new Gson();
			
			ToDoFileManager.readFileIfEmpty();
			session.getAsyncRemote().sendText(gson.toJson(ToDoFileManager.getToDoList().getRawList()));
			
			addTodoListener(session);
		}

		@OnMessage
		public void onMessage(String message, Session session) throws IOException 
		{
			int separatorLocation=message.indexOf(";");
			
			String beginning=message.substring(0, separatorLocation);
	
			switch(beginning)
			{
			case "addTodo":
				Gson gson=new Gson();
				ToDo toAdd=gson.fromJson(message.substring(separatorLocation+1, message.length()), ToDo.class);
				ToDoFileManager.getToDoList().addToDo(toAdd);
				broadcastCurrentList();
				ToDoFileManager.saveFile();
				break;
			case "removeTodo":
				String task=message.substring(separatorLocation+1, message.length());
				if(ToDoFileManager.getToDoList().removeToDoWithTask(task))
				{
					broadcastCurrentList();
					ToDoFileManager.saveFile();
				}
				break;
			case "getList":
				Gson gson2=new Gson();
				session.getAsyncRemote().sendText(gson2.toJson(ToDoFileManager.getToDoList().getRawList()));
				break;
			}
		}

		/**
	     * The user closes the connection.
	     */
		@OnClose
		public void onClose(Session session, CloseReason closeReason) {
			this.removeTodoListener(session);
		}
		
		private void addTodoListener(Session s)
		{
			synchronized(this.registeredTodoListeners)
			{
				this.registeredTodoListeners.add(s);
			}
		}
		private void removeTodoListener(Session s)
		{
			synchronized(this.registeredTodoListeners)
			{
				this.registeredTodoListeners.remove(s);
			}
		}
		private void broadcastCurrentList()
		{
			Gson gson=new Gson();
			
			synchronized(this.registeredTodoListeners)
			{
				for(Session s:this.registeredTodoListeners)
				{
					s.getAsyncRemote().sendText(gson.toJson(ToDoFileManager.getToDoList().getRawList()));
				}
			}
		}
		
}
