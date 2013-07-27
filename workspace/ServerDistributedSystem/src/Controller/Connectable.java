package Controller;

public interface Connectable {

	public void connect();
	
	public void request(String request);
	
	public void response(String response);
	
	public void disconnect();
}
