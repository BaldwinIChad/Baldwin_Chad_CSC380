package UserFacingPackage;

public class TestClass {
	int value;
	String name;
	
	public TestClass(String n, int v)  {
		name = n;
		value = v;
	}
	
	public TestClass doStuff()
	{
		return new TestClass(name + "Test", value * 2);
	}
	
	public String toString()
	{
		return name;
	}
}
