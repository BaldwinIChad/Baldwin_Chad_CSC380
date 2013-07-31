package Servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Resturants.FoodService;
import Resturants.Resturant;

/**
 * Servlet implementation class ResturantObtainer
 */
@WebServlet("/ResturantObtainer")
public class ResturantObtainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResturantObtainer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			InputStream is = new FileInputStream(getServletConfig().getServletContext().getRealPath("/foodService.xml"));
			Unmarshaller um = JAXBContext.newInstance(FoodService.class).createUnmarshaller();
			FoodService fs = (FoodService)(um.unmarshal(is));
			Marshaller m = JAXBContext.newInstance(FoodService.class).createMarshaller();
			m.marshal(fs, response.getOutputStream());
		} catch(Exception e){
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
