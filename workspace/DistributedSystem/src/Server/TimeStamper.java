package Server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamper {
	static DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static String getTimeStamp()
	{
		Date date = new Date();
		return format.format(date);
	}
}
