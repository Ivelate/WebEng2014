package ivelate.p3.websocket;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import ivelate.p3.todo.ToDoList;

public class ToDoFileManager 
{
	public final static String DEFAULT_FILE_NAME = "ToDoList.json";
	
	private static ToDoList todoList=null;
	
	public static void readFile()
	{
		Gson gson = new Gson();

		// Read the existing ToDo list
		try {
			todoList = gson.fromJson(new FileReader(DEFAULT_FILE_NAME),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println(DEFAULT_FILE_NAME
					+ ": File not found.  ToDos can't be loaded. (WARNING)");
			todoList=new ToDoList();
		}
		
	}
	public static void readFileIfEmpty()
	{
		if(todoList==null) readFile();
	}
	
	public static void saveFile()
	{
		// Write the new ToDo file back to disk.
		   Gson gson = new Gson();
		   try
		   {
			   FileWriter output = new FileWriter(DEFAULT_FILE_NAME);
			   output.write(gson.toJson(getToDoList()));
			   output.close();
		   } catch (IOException e) {System.out.println("Error: ToDo file can't be saved");}
	}
	
	public static ToDoList getToDoList()
	{
		return todoList;
	}
	
}
