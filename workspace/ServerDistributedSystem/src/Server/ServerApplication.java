package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import UserFacingPackage.MathLogic;

import Controller.ClientProcessor;
import Controller.Connectable;

public class ServerApplication implements Connectable{

	ServerSocket serverSocket;
	static MathLogic logic = new MathLogic();

	public ServerApplication(int port) throws IOException{
		serverSocket = new ServerSocket(port);
	}
	
	
	public void start() throws IOException, NoSuchMethodException, SecurityException, ClassNotFoundException{
		
		
		
		while(true){
			System.out.println("Listening for client connection...");
			Socket clientSocket = serverSocket.accept();
			
			ClientProcessor newProcessor = new ClientProcessor(logic,clientSocket);
			newProcessor.addListener(this);
			Thread nextClient = new Thread(newProcessor);
			
			nextClient.start();
		}
	}

	public void connect() {
		System.out.println("Client connected "+new Date());
	}

	public void disconnect() {
		System.out.println("Client disconnected "+new Date());
	}

	public void request(String request) {
		System.out.println("REQUEST: "+request);
	}

	public void response(String response) {
		System.out.println("RESPONSE: "+response);
	}
}
