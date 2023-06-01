package supp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;

public class Help {
	public static int calculateAge(String input)   
	{  
	LocalDate dob = LocalDate.parse(input); 
	//creating an instance of the LocalDate class and invoking the now() method      
	//now() method obtains the current date from the system clock in the default time zone      
	LocalDate curDate = LocalDate.now();  
	//calculates the amount of time between two dates and returns the years  
	if ((dob != null) && (curDate != null))   
	{  
	return Period.between(dob, curDate).getYears();  
	}  
	else  
	{  
	return 0;  
	}  
	}
	public static  String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
	public String timeInfo(long current_date, long date_pub) {
		long secondsDifference = current_date-date_pub;
		String time_sentence="Last updated ";
        if (secondsDifference < 60) {
        	if(secondsDifference>1) {
            	time_sentence=secondsDifference + " seconds ago";
        	}
        	else {
            	time_sentence=secondsDifference + " second ago";


        	}
        } else if (secondsDifference < 3600) {
            long minutes = secondsDifference / 60;
            if(minutes>1) {
                time_sentence=minutes + " minutes ago";

            }
            else {
                time_sentence=minutes + " minute ago";

            }
        } else if (secondsDifference < 86400) {
            long hours = secondsDifference / 3600;
            if(hours>1) {
                time_sentence=hours + " hours ago";

            }
            else {
                time_sentence=hours + " hour ago";

            }
        }
        else if (secondsDifference <  2592000) {
            long days = secondsDifference / 86400;
            if(days>1) {
            time_sentence=days + " days ago";}
            else {
                time_sentence=days + " day ago";}

            
        }
        else if (secondsDifference <  31104000) {
            long months = secondsDifference / 2592000;
            if(months>1) {
                time_sentence=months + " months ago";

            }
            else {
                time_sentence=months + " month ago";

            }
        }
        else {
            long years = secondsDifference / 31104000 ;
            if(years>1) {
                time_sentence= years + " years ago";

            }
            else {
                time_sentence= years + " year ago";

            }
        }
        


		return time_sentence;
	}
}
