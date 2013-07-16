import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;



public class Startup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ReflectionActivity act = new ReflectionActivity();
		try {
			@SuppressWarnings("rawtypes")
			Class x = Class.forName("ReflectionActivity");
			@SuppressWarnings("rawtypes")
			Constructor[] c = x.getConstructors();
			
			for(int i = 0; i<c.length;i++)
			{
				System.out.println(c[i]);
			}
			
			@SuppressWarnings("unchecked")
			ReflectionActivity a = (ReflectionActivity) x.getConstructor(String.class,
					double.class).newInstance("Whoo", 13.0);
			
			System.out.println(a.getName() + " " + a.getId());
			
			Field[] f = x.getDeclaredFields();
			
			for(int i = 0; i<f.length;i++)
				System.out.println(f[i].getName());
			
			@SuppressWarnings("unchecked")
			Method z = x.getMethod("addToNumber", double.class);
			System.out.println(z.invoke(a, 18.0));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
