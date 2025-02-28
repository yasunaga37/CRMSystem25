package test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test1 {

	public static void main(String[] args) {
		String strDate = "2023-07-10T15:20:00";  // この形式に注意してください: yyyy-MM-ddTHH:mm:ss

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);

        Timestamp timestamp = Timestamp.valueOf(dateTime);

        System.out.println("Timestamp: " + timestamp);

	}

}
