package Resturants;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name","items"})
public class Resturant {

	private String name;
	
	public String getName(){
		return name;
	}
	
	@XmlAttribute(name="name")
	public void setName(String name){
		this.name = name;
	}
	
	private ArrayList<MenuItem> items;
	
	public ArrayList<MenuItem> getItems(){
		return items;
	}
	
	@XmlElement(name="item")
	public void setItems(ArrayList<MenuItem> items){
		this.items = items;
	}
	
}
