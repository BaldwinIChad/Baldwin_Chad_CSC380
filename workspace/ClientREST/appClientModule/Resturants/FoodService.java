package Resturants;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement

@XmlType(propOrder={"resturants"})
public class FoodService {
	
	private ArrayList<Resturant> resturants;
	
	public ArrayList<Resturant> getResturants(){
		return this.resturants;
	}
	
	@XmlElement(name="resturant")
	public void setResturants(ArrayList<Resturant> resturants){
		this.resturants = resturants;
	}
}
