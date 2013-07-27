import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//This is how to send a get request with params
		//? after url
		//param name = param value
		
		sendFirstGet();
		sendSecondGet();
		sendFinalPOST();
	}
	
	public static void sendFinalPOST() throws UnknownHostException, IOException{
		Socket s = new Socket("localhost",8080);
		OutputStream os = s.getOutputStream();
		
		PrintWriter pw = new PrintWriter(os);
		
		System.out.println("What did you want again?");
		Scanner input = new Scanner(System.in);
		pw.write("POST /REST/ItemObtainer?a="+input.nextLine() +" HTTP/1.0\r\n\n");
		pw.flush();
		InputStream in = s.getInputStream();
		
		Scanner scan = new Scanner(in);
		while(scan.hasNext()){
			System.out.println(scan.nextLine());
		}
	}
	
	public static void sendSecondGet() throws UnknownHostException, IOException{
		Socket s = new Socket("localhost",8080);
		OutputStream os = s.getOutputStream();
		
		PrintWriter pw = new PrintWriter(os);
		
		System.out.println("Type the resturant name");
		Scanner input = new Scanner(System.in);
		pw.write("GET /REST/ItemObtainer?name="+input.nextLine()+" HTTP/1.0\r\n\n");
		pw.flush();
		InputStream in = s.getInputStream();
		
		Scanner scan = new Scanner(in);
		while(scan.hasNext()){
			System.out.println(scan.nextLine());
		}
	}
	
	public static void sendFirstGet() throws UnknownHostException, IOException{
		Socket s = new Socket("localhost",8080);
		OutputStream os = s.getOutputStream();
		
		PrintWriter pw = new PrintWriter(os);
		pw.write("GET /REST/ResturantObtainer HTTP/1.0\r\n\n");
		pw.flush();
		
		InputStream in = s.getInputStream();
		
		Scanner scan = new Scanner(in);
		while(scan.hasNext()){
			System.out.println(scan.nextLine());
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}