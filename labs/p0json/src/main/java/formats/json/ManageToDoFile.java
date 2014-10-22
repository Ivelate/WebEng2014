package formats.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.Gson;

import static formats.json.PhoneType.*;

public class ManageToDoFile {

	public final static String DEFAULT_FILE_NAME = "ToDoList.json";
	
	private static final int MENU_ADD=1;
	private static final int MENU_REMOVE=2;
	private static final int MENU_PRINT=3;
	private static final int MENU_EXIT=4;
	
	private static ToDo createTodoFromInput()
	{
		return null;
	}
	public static void main(String[] args) throws Exception {
		String filename = DEFAULT_FILE_NAME;
		if (args.length > 0) {
			filename = args[0];
		}

		ToDoList todoList = new ToDoList();
		Gson gson = new Gson();
		Scanner input=new Scanner(System.in);

		// Read the existing ToDo list
		try {
			todoList = gson.fromJson(new FileReader(filename),
					ToDoList.class);
		} catch (FileNotFoundException e) {
			System.out.println(filename
					+ ": File not found.  Creating a new file.");
		}
		
		System.out.println();
		System.out.println("Actually managing ToDo list "+filename);
		
		boolean finish=false;
		while(!finish)
		{
			System.out.println();
			System.out.println("Select an option: "+MENU_ADD+" - Add a new ToDo to the list");
			System.out.println("                  "+MENU_REMOVE+" - Delete a ToDo from the list");
			System.out.println("                  "+MENU_PRINT+" - Print complete ToDo list");
			System.out.println("                  "+MENU_EXIT+" - Exit");
			
			int selectedOption=-1;
			try
			{
				selectedOption=Integer.parseInt(input.nextLine());
				switch(selectedOption)
				{
				case MENU_ADD:
					todoList.addToDo(createTodoFromInput());
					break;
				case MENU_REMOVE:
					todoList.removeToDo(createTodoFromInput());
					break;
				case MENU_PRINT:
					todoList.printList(System.out);
					break;
				case MENU_EXIT:
					finish=true;
					break;
	
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid input, try again");
			}
		}

		// Write the new address book back to disk.
		FileWriter output = new FileWriter(filename);
		output.write(gson.toJson(todoList));
		input.close();
		output.close();
	}
}
