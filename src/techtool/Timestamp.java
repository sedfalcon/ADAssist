package techtool;

import java.time.LocalDateTime;

public class Timestamp {


	public Timestamp() {
		
	}
	
	public String getTimestamp() {
		StringBuilder sb = new StringBuilder();
		String timestamp = LocalDateTime.now().toString();
		int month = LocalDateTime.now().getMonthValue();
		int day = LocalDateTime.now().getDayOfMonth();
		int year = LocalDateTime.now().getYear();
		
		int hour = Main.timeto12(LocalDateTime.now().toLocalTime());
		int minute = LocalDateTime.now().getMinute();
		String minutestr = null;
		int second = LocalDateTime.now().getSecond();
		String secondstr = null;
		
		String ampm = Main.getAMPM(LocalDateTime.now().toLocalTime());
		
		if(minute >= 0 && minute < 10) {
			minutestr = "0" + minute;
		}else{minutestr = Integer.toString(minute);}
		
		if(second >= 0 && second < 10) {
			secondstr = "0" + second;
		}else{secondstr = Integer.toString(second);}
		
		sb.append(month + "/");
		sb.append(day + "/");
		sb.append(year + " ");
		sb.append(hour + ":");
		sb.append(minutestr + ":");
		sb.append( secondstr + " ");
		sb.append(ampm);
		timestamp = sb.toString();
		
		return timestamp;
	}
}
