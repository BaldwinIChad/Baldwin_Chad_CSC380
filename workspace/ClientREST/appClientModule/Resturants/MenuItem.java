package Resturants;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name","price"})
@XmlRootElement
public class MenuItem {

	private String name;
	private double price;
	
	
	public String getName(){
		return name;
	}
	
	@XmlAttribute(name="name",required=true)
	public void setName(String name){
		this.name = name;
	}
	
	public double getPrice(){
		return price;
	}
	
	@XmlAttribute(name="price",required=true)
	public void setPrice(double price){
		this.price = price;
	}
}
