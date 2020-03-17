package SSAFYDaily7;

import java.util.*;

public class HelloWorld{

	public static void main(String []args){
         
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
        
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		
		int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};
		if((year % 4 == 0) && (year % 100 != 0) || (year % 400) == 0) 
			days[1] = 29;
        
        System.out.println(days[month] + " days");
     }
}