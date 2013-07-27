package Servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import Resturants.FoodService;
import Resturants.MenuItem;
import Resturants.Resturant;

/**
 * Servlet implementation class ItemObtainer
 */
@WebServlet("/ItemObtainer")
public class ItemObtainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemObtainer() {
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
			
			String req = request.getParameter("name");
			
			
			for(Resturant r : fs.getResturants()){
				if(r.getName().equals(req)){
					for(MenuItem mi : r.getItems()){
						response.getWriter().write(mi.getName()+" "+mi.getPrice()+"\n");
					}
					break;
				}
			}
		} catch(Exception e){
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Processing order");
		Enumeration<String> items = request.getParameterNames();
		
		while(items.hasMoreElements()){
			response.getWriter().write("\nOrder confirmed for : "+request.getParameter(items.nextElement())+"\n");
		}
		
		response.getWriter().write("Thanks for orderings and shopping at lazymans ordering school thing servlet calls");
	}

}
