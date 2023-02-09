package fr.nessar.backend.VCal;

import java.util.List;

import fr.nessar.backend.u.UCalendar;
import fr.nessar.backend.u.UEvent;

public class VCalendar {

	private final StringBuilder stringBuilder = new StringBuilder();

	public VCalendar(UCalendar calendar) {
		stringBuilder.append("BEGIN:VCALENDAR\r\nVERSION:2.0\r\nPRODID:" + calendar.getCalName() + "\r\n");
		stringBuilder.append(
				"BEGIN:VTIMEZONE\r\nTZID:Europe/Paris\r\nX-LIC-LOCATION:Europe/Paris\r\nBEGIN:DAYLIGHT\r\nTZOFFSETFROM:+0100\r\nTZOFFSETTO:+0200\r\nTZNAME:CEST\r\nDTSTART:19700329T020000\r\nRRULE:FREQ=YEARLY;INTERVAL=1;BYDAY=-1SU;BYMONTH=3\r\nEND:DAYLIGHT\r\nBEGIN:STANDARD\r\nTZOFFSETFROM:+0200\r\nTZOFFSETTO:+0100\r\nTZNAME:CET\r\nDTSTART:19701025T030000\r\nRRULE:FREQ=YEARLY;INTERVAL=1;BYDAY=-1SU;BYMONTH=10\r\nEND:STANDARD\r\nEND:VTIMEZONE\r\n");
		this.addEvent(calendar.getEvents());
	}

	public void addEvent(UEvent event) {
		VEvent v = new VEvent(event);
		this.addEvent(v);
	}

	public void addEvent(VEvent v) {
		this.stringBuilder.append(v.getBuilder());
	}

	public void addEvent(List<UEvent> u) {
		for (UEvent event : u) {
			this.addEvent(event);
		}
	}

	@Override
	public String toString() {
		return this.stringBuilder.toString() + "END:VCALENDAR";
	}
}
