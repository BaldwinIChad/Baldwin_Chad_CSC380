package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Resturants.MenuItem;

/**
 * Servlet implementation class ItemObtainer
 */
@WebServlet("/ItemObtainer")
public class ItemObtainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest rq, HttpServletResponse re)
	{
		System.out.println("I'm awesome");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JAXBContext jc = JAXBContext.newInstance(MenuItem.class);
			Unmarshaller um = jc.createUnmarshaller();
			MenuItem mi = (MenuItem) um.unmarshal(request.getInputStream());
			System.out.println(mi.getName());
			response.getWriter().write("Order for " + mi.getName() + " with a cost of " + mi.getPrice() + " confirmed");
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
