package StartUp;

import java.io.IOException;

import Server.ServerApplication;

public class ServerDriver {

	final static int PORT = 5000;
	
	public static void main(String[]args) throws IOException, NoSuchMethodException, SecurityException, ClassNotFoundException{

		ServerApplication server = new ServerApplication(PORT);
		server.start();
		
		
	}
	
	
}
