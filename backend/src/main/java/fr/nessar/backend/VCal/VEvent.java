package fr.nessar.backend.VCal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import fr.nessar.backend.u.UEvent;

public class VEvent {

	private StringBuilder stringBuilder = new StringBuilder();

	public VEvent(UEvent event) {
		this.stringBuilder.append("BEGIN:VEVENT\r\n");
		this.stringBuilder.append("UID:" + UUID.randomUUID().toString() + "\r\n");
		String modules = "";
		if (event.modules != null)
			for (String mod : event.modules) {
				modules += mod + ", ";
			}
		this.stringBuilder.append(
				"SUMMARY:"
						+ parseComma("[" + event.eventCategory + "] " + modules
								+ (event.modules != null ? event.modules[0] : "???"))
						+ "\r\n");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		this.stringBuilder.append("DTSTAMP;TZID=Europe/Paris:" + sdf.format(now) + "\r\n");
		this.stringBuilder.append("DTSTART;TZID=Europe/Paris:" + event.getParsedStart() + "\r\n");
		if (!event.allDay)
			this.stringBuilder.append("DTEND;TZID=Europe/Paris:" + event.getParsedEnd() + "\r\n");
		String room = event.getRoom();
		String desc;
		if (room == null)
			desc = parseDesc(event.description);
		else {
			desc = room + "\\nGroups: " + event.getGroups() + "\\nProf: " + event.getProf()
					+ "\\nNotes: " + event.getNotes();
		}
		this.stringBuilder.append("DESCRIPTION:" + parseComma(desc) + "\r\n");
		String loc = event.getLocation();
		if (loc != null)
			this.stringBuilder.append("LOCATION:" + parseComma(loc) + "\r\n");
		this.stringBuilder.append("END:VEVENT\r\n");
	}

	private String parseComma(String input) {
		return input.replace(",", "\\,");
	}

	private String parseDesc(String input) {
		return input.replace("\r", "").replace("\n", "");
	}

	public StringBuilder getBuilder() {
		return this.stringBuilder;
	}

	public String getEvent() {
		return this.stringBuilder.toString();
	}

}
