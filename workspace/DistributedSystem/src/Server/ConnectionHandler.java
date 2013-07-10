package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionHandler implements Runnable {
	
	Socket client;
	
	public ConnectionHandler(Socket clientConnection)
	{
		client = clientConnection;
	}
	
	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			Scanner in = new Scanner(client.getInputStream());
			String input = "";
			out.println("Please type a addition problem or a subtraction problem (i.e. 3+4)");
			out.flush();
			System.out.println(TimeStamper.getTimeStamp() + "- Math problem request sent");
			
			input+=in.nextLine();
			
			System.out.println(TimeStamper.getTimeStamp() + "- Math problem recieved");
			String[] temp;
			if(input.contains("+"))
			{
				temp = input.split("\\+");
				try
				{
					int result = MathLogic.add(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));	
					out.println(input + "=" + result);
					out.flush();
					System.out.println(TimeStamper.getTimeStamp() + "- Calculation done, result sent");
				} catch(NumberFormatException e) {
					out.println("Your input did not contain whole numbers.");
					out.flush();
					System.out.println(TimeStamper.getTimeStamp() + "- Request rejected for improper format");
					in.close();
				}
			}
			else if(input.contains("-"))
			{
				temp = input.split("-");
				try
				{
					int result = MathLogic.subtract(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));	
					out.println(input + "=" + result);
					out.flush();
					System.out.println(TimeStamper.getTimeStamp() + "- Calculation done, result sent");
				} catch(NumberFormatException e) {
					out.println("Your input did not contain whole numbers.");
					out.flush();
					System.out.println(TimeStamper.getTimeStamp() + "- Request rejected for improper format");
					in.close();
				}
			}
			else
			{
				out.println("That operation is not yet handled");	
				out.flush();
				System.out.println(TimeStamper.getTimeStamp() + "- Request rejected for unsupported operation");
				in.close();
			}
		}
		catch (IOException e) {
			System.out.println("Lost Connection");
			System.out.println(TimeStamper.getTimeStamp() + "- Connection to client was lost");
		}
		
	}

}
