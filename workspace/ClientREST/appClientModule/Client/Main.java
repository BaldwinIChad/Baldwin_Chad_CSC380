package Client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Resturants.FoodService;
import Resturants.MenuItem;
import Resturants.Resturant;


public class Main {
	static FoodService service;
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) throws UnknownHostException, IOException, JAXBException {
		
		//This is how to send a get request with params
		//? after url
		//param name = param value
		
//		URL url = new URL("http://localhost:8080/REST/ItemObtainer");
//		URLConnection con = url.openConnection();
//		con.setDoOutput(true);
//		PrintWriter writer = new PrintWriter(con.getOutputStream());
//		writer.write("hello");
//		writer.close();
//
//		URL url2 = new URL("http://localhost:8080/REST/ItemObtainer");
//		HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
//		connection.setDoOutput(true);
//		connection.setRequestMethod("POST");
//		connection.setRequestProperty("Accept", "application/xml");
//
//		
//		MenuItem mi = new MenuItem();
//		mi.setName("Test");
//		mi.setPrice(1);
//		Marshaller m = JAXBContext.newInstance(MenuItem.class).createMarshaller();
//		m.marshal(mi, connection.getOutputStream());
//
//		
//		
//		JAXBContext jc = JAXBContext.newInstance(FoodService.class);
//		InputStream xml = connection.getInputStream();
//		connection.disconnect();
//		
		
		sendFirstGet();
		sendSecondGet();
		
		
//		con = (HttpURLConnection) url.openConnection();
//		con.connect();
//		System.out.println(con.getConnectTimeout());
	}
	
	
	
//	public static void sendFinalPOST(Resturant r) throws IOException, JAXBException{
//		
//		InputStream is = new FileInputStream(getServletConfig().getServletContext().getRealPath("/foodService.xml"));
//		Unmarshaller um = JAXBContext.newInstance(FoodService.class).createUnmarshaller();
//		FoodService fs = (FoodService)(um.unmarshal(is));
//		Marshaller m = JAXBContext.newInstance(FoodService.class).createMarshaller();
//		m.marshal(fs, response.getOutputStream());
//		
////		JAXBContext jc = JAXBContext.newInstance(MenuItem.class);
////		Marshaller m = jc.createMarshaller();
////		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
////		MenuItem mi = new MenuItem();
////		mi.setName("Working");
////		m.marshal(mi, connection.getOutputStream());
////		connection.getOutputStream().flush();
//		System.out.println("DONE");
//	}
	
	public static void sendFinalPOST(Resturant r) throws UnknownHostException, IOException, JAXBException {
		for(MenuItem i : r.getItems())
			System.out.println(i.getName() + " Price: $" + i.getPrice());
		
		System.out.println("Type the food item you want...");
		MenuItem item = new MenuItem();
		boolean notFound = true;
		String selection;
		
		while(notFound){
			selection = input.nextLine();
			
			for(MenuItem i : r.getItems())
				if(selection.equalsIgnoreCase(i.getName())) {
					item = i;
					notFound = false;
				}
			if(notFound)
				System.out.println("Item not found, try again...");
		}
		
//		URL url = new URL("http://localhost:8080/REST/ItemObtainer");
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setDoOutput(true);
//		connection.connect();
		
		URL url2 = new URL("http://localhost:8080/REST/ItemObtainer");
		HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
		connection2.setDoOutput(true);
		connection2.setRequestMethod("POST");
		connection2.setRequestProperty("Accept", "application/xml");
		
		Marshaller m = JAXBContext.newInstance(MenuItem.class).createMarshaller();
		m.marshal(item, connection2.getOutputStream());
		
		Scanner s = new Scanner(connection2.getInputStream());
		while(s.hasNext())
			System.out.println(s.nextLine());
		connection2.disconnect();
		
		s.close();
		
		
//		JAXBContext jc = JAXBContext.newInstance(MenuItem.class);
//		Marshaller m = jc.createMarshaller();
//		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		m.marshal(item, connection.getOutputStream());
//		connection.getOutputStream().flush();

		System.out.println("DONE");
//		InputStream is = new FileInputStream(new File("testItem.xml"));
//		Unmarshaller um = JAXBContext.newInstance(MenuItem.class).createUnmarshaller();
//		MenuItem mi = (MenuItem)(um.unmarshal(is));
//		Marshaller mars = JAXBContext.newInstance(FoodService.class).createMarshaller();
//		m.marshal(mi, System.out);
	}
	
	public static void sendSecondGet() throws UnknownHostException, IOException, JAXBException {
		boolean notFound = true;
		Resturant choice = null;
		while(notFound) {
			System.out.println("Please type the name of the restaurant you wish to order from");
			String selection = input.nextLine();
			
			for(Resturant x : service.getResturants())
				if(selection.equalsIgnoreCase(x.getName())) {
					choice = x;
					notFound = false;
				}
			if(notFound)
				System.out.println("Restaurant not found, try again...");
		}
		sendFinalPOST(choice);
	}
	
	public static void sendFirstGet() throws UnknownHostException, IOException, JAXBException {
		URL url = new URL("http://localhost:8080/REST/ResturantObtainer");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/xml");
		
		JAXBContext jc = JAXBContext.newInstance(FoodService.class);
		InputStream xml = connection.getInputStream();
		FoodService ser = (FoodService) jc.createUnmarshaller().unmarshal(xml);
		service = ser;
		
		for(Resturant x : service.getResturants())
			System.out.println(x.getName());
		
		connection.disconnect();
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}