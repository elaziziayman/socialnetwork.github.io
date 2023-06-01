package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.SingletonConnection;
import metier.entities.User;

public class TetsdtePublication {

	public static void main(String[] args) {
		Connection connection = SingletonConnection.getConnection();
		long date= System.currentTimeMillis() / 1000;;
		long date_pub =0; 
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT DATE_PUBLICATION FROM publication ");
			ResultSet rs = ps.executeQuery();


			while (rs.next()) {
				
				 date_pub = rs.getLong("DATE_PUBLICATION");

			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long secondsDifference = date-date_pub;

        if (secondsDifference < 60) {
            System.out.println(secondsDifference + " seconds ago");
        } else if (secondsDifference < 3600) {
            long minutes = secondsDifference / 60;
            System.out.println(minutes + " minutes ago");
        } else if (secondsDifference < 86400) {
            long hours = secondsDifference / 3600;
            System.out.println(hours + " hours ago");
        } else {
            long days = secondsDifference / 86400;
            System.out.println(days + " days ago");
        }

		

	}
	public String timeInfo(long date, long date_pub) {
		long secondsDifference = date-date_pub;
		String time_sentence=null;
        if (secondsDifference < 60) {
        	time_sentence=secondsDifference + " seconds ago";
            System.out.println(secondsDifference + " seconds ago");
        } else if (secondsDifference < 3600) {
            long minutes = secondsDifference / 60;
            time_sentence=minutes + " minutes ago";
            System.out.println(minutes + " minutes ago");
        } else if (secondsDifference < 86400) {
            long hours = secondsDifference / 3600;
            time_sentence=hours + " hours ago";
            System.out.println(hours + " hours ago");
        }
        else if (secondsDifference <  2592000) {
            long days = secondsDifference / 86400;
            time_sentence=days + " days ago";
            System.out.println(days + " days ago");
        }
        else if (secondsDifference <  31104000) {
            long months = secondsDifference / 2592000;
            time_sentence=months + " months ago";
            System.out.println(months + " months ago");
        }
        else {
            long years = secondsDifference / 31104000 ;
            time_sentence= years + " years ago";
            System.out.println(years + " years ago");
        }
        


		return time_sentence;
	}

}
