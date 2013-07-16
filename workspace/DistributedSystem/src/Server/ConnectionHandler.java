package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ConnectionHandler implements Runnable {
	
	Socket client;
	MathLogic logic = new MathLogic();
	HashMap<String, Class<?>[]> methodParams = new HashMap<String, Class<?>[]>();
	HashMap<Class<?>, Class<?>> boxingClasses = new HashMap<Class<?>, Class<?>>();
	HashMap<Class<?>, Class<?>> unboxingClasses = new HashMap<Class<?>, Class<?>>();
	
	public ConnectionHandler(Socket clientConnection)
	{
		client = clientConnection;
		boxingClasses.put(int.class, Integer.class);
		boxingClasses.put(double.class, Double.class);
		boxingClasses.put(short.class, Short.class);
		boxingClasses.put(byte.class, Byte.class);
		boxingClasses.put(char.class, Character.class);
		boxingClasses.put(long.class, Long.class);
		boxingClasses.put(float.class, Float.class);
		boxingClasses.put(boolean.class, Boolean.class);
		boxingClasses.put(String.class, String.class);
		
		unboxingClasses.put(Integer.class, int.class);
		unboxingClasses.put(Double.class, double.class);
		unboxingClasses.put(Short.class, short.class);
		unboxingClasses.put(Byte.class, byte.class);
		unboxingClasses.put(Character.class, char.class);
		unboxingClasses.put(Long.class, long.class);
		unboxingClasses.put(Float.class, float.class);
		unboxingClasses.put(Boolean.class, boolean.class);
		unboxingClasses.put(String.class, String.class);
	}
	
	@Override
	public void run() {
		try {
			Class<?> c = Class.forName("Server.MathLogic");
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			Scanner in = new Scanner(client.getInputStream());
			String methodName = "";
			String initalResponse = "Type the math function to preform. Functions are: ";

			for(Method x : c.getDeclaredMethods())
			{
				initalResponse += x.getName() + ", ";
				methodParams.put(x.getName(), x.getParameterTypes());
			}
			
			out.println(initalResponse);
			out.flush();
			
			System.out.println(TimeStamper.getTimeStamp() + "- Math problem request sent");
			
			methodName=in.nextLine();
			System.out.println(TimeStamper.getTimeStamp() + "- Math problem recieved");
			
			Method method = c.getMethod(methodName, methodParams.get(methodName));
			Class<?>[] paramsForMethod = methodParams.get(methodName);
			out.println("Method requires: ");
			out.flush();
				
			String[] inputs = new String[paramsForMethod.length];
			out.println(paramsForMethod.length);
			out.flush();
			
			for(int a = 0; a < paramsForMethod.length; a++)
			{
				out.println(paramsForMethod[a].getName());
				out.flush();
				inputs[a] = in.nextLine();
			}
			
			
			Object[] userParaList = new Object[paramsForMethod.length];
			
			for(int p = 0; p<paramsForMethod.length;p++)
			{
				Class<?> wrap = boxingClasses.get(paramsForMethod[p]);
				Class<?> unwrap = unboxingClasses.get(wrap);
				
				if(wrap.equals(Character.class))
				{
					userParaList[p] = (char) Character.valueOf(inputs[p].charAt(0));
				}
				else if(wrap.equals(String.class))
				{
					userParaList[p] = inputs[p];
				}
				else
				{
					Method u = wrap.getMethod(unwrap.getSimpleName()+"Value");
					userParaList[p] = u.invoke(wrap.getMethod("valueOf", String.class).invoke(wrap, inputs[p]));
				}
			}
			
			Object result = method.invoke(c.newInstance(), userParaList);
			
			out.println("Result: " + result.toString());
			out.flush();
			System.out.println(TimeStamper.getTimeStamp() + "- Calculation done, result sent");
			in.close();
			out.close();
		}
		catch (IOException e) {
			System.out.println("Lost Connection");
			System.out.println(TimeStamper.getTimeStamp() + "- Connection to client was lost");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
