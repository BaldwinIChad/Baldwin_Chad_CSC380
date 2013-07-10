package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	static ServerSocket server;
	public static void main(String[] args)
	{
		try {
			server = new ServerSocket(2756);
			server.setSoTimeout(300000);
			
			while(true)
			{
				Runnable handler = new ConnectionHandler(server.accept());
				System.out.println(TimeStamper.getTimeStamp() + "- Connection accpeted");
				new Thread(handler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
