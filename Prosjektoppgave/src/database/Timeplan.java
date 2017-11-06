package database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Timeplan{
	public static String getTidspunktStreng(Date date, Integer timeslot, Integer duration) {
		int timestart = 8+timeslot;
		return new SimpleDateFormat("EEEE").format(date) +" "+ new SimpleDateFormat("dd/MM").format(date) + " "+(timeslot+8)+":15 - "+(timeslot+duration+8)+":00";
	}
	
	
	public static boolean erAktiv(Forelesning forelesning) {
		return getLongSince(forelesning)==0;
	}
	
	public static long getLongSince(Forelesning forelesning){
		Date date = forelesning.getDato();
		Integer timeslot = forelesning.getTimeslot();
		Integer duration = forelesning.getVarighet();
		
		Calendar start = GregorianCalendar.getInstance();
		start.setTime(date);
		start.add(GregorianCalendar.HOUR, timeslot+8);
		start.add(GregorianCalendar.MINUTE, 15);
		Date dStart = start.getTime();
		
		Calendar stop = GregorianCalendar.getInstance();
		stop.setTime(date);
		stop.add(GregorianCalendar.HOUR, timeslot+8+duration);
		Date dStop = stop.getTime();
		
		Date now = new Date();
		
		if(dStart.compareTo(now)>0)
			return now.getTime()-dStart.getTime();
		else if(dStop.compareTo(now)>0)
			return 0;
		else
			return now.getTime()-dStop.getTime();
		
	}
}