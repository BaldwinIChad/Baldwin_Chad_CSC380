package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args)
	{
		try {
			Socket sock = new Socket("localhost", 2756);
			boolean isRunning = true;
			while(isRunning)
			{
				BufferedReader scan = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				
				System.out.println(scan.readLine());

				Scanner userInput = new Scanner(System.in);
				PrintWriter printer = new PrintWriter(sock.getOutputStream());
				printer.println(userInput.nextLine());
				printer.flush();

				Thread.sleep(500);
				System.out.println(scan.readLine());
				
				scan.close();
				printer.close();
				userInput.close();
				
				isRunning = false;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
