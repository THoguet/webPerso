package fr.nessar.backend.u;

public class UEvent {
	public String id;
	public String start;
	public String end;
	public boolean allDay;
	public String description;
	public String backgroundColor;
	public String textColor;
	public String department;
	public String faculty;
	public String eventCategory;
	public String[] sites;
	public String[] modules;
	public int registerStatus;
	public int studentMark;
	public String custom1;
	public String custom2;
	public String custom3;

	@Override
	public String toString() {
		return "id: " + this.id + ", start: " + this.start + ", Pstart: " + this.getParsedStart() + ", end: " + this.end
				+ ", Pend: " + this.getParsedEnd() + ", allDay: " + this.allDay + ", description: " + this.description
				+ ", backgroundColor: " + this.backgroundColor + ", textColor: " + this.textColor + ", department: "
				+ this.department + ", faculty: " + this.faculty + ", eventCategory: " + this.eventCategory
				+ ", sites: " + (this.sites != null ? this.sites[0] : "aucun") + ", modules: "
				+ (this.modules != null ? this.modules[0] : "aucun") + ", registerStatus: " + this.registerStatus
				+ ", studentMark: " + this.studentMark + ", custom1: " + this.custom1 + ", custom2: " + this.custom2
				+ ", custom3: " + this.custom3 + ", Prof: " + this.getProf() + ", Groups: " + this.getGroups()
				+ ", Notes:" + this.getNotes();
	}

	public String getParsedStart() {
		String str = this.start.replaceAll("-", "").replaceAll(":", "");
		int index = str.indexOf("T");
		if (this.allDay)
			return str.substring(0, index);
		return str;
	}

	public String getParsedEnd() {
		if (this.end == null)
			return "null";
		return this.end.replaceAll("-", "").replaceAll(":", "");

	}

	public String getGroups() {
		String[] strs = this.description.replaceAll("\\r", "").replaceAll("\\n", "").split("<br />");
		if (strs.length > 2)
			return strs[2];
		return "???";
	}

	public String getProf() {
		String[] strs = this.description.replaceAll("\\r", "").replaceAll("\\n", "").split("<br />");
		int indexRoom = getRoomIndex(strs);
		if (strs.length > indexRoom - 1 && indexRoom - 1 != 2)
			return strs[indexRoom - 1];
		return "???";
	}

	public String getNotes() {
		String[] strs = this.description.replaceAll("\\r", "").replaceAll("\\n", "").split("<br />");
		if (!Character.isDigit(strs[strs.length - 1].charAt(0)))
			return strs[strs.length - 1];
		return "";
	}

	public String getLocation() {
		if (this.sites == null)
			return null;
		String loc = this.sites[0].replaceAll("Bâtiment ", "");
		return loc + ", 33400 Talence, France";
	}

	private int getRoomIndex(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			if (strs[i] != null)
				if (strs[i].contains("Amphith&#233;&#226;tre ") || strs[i].contains("Salle "))
					return i;
		}
		return -1;
	}

	public String getRoom() {
		String[] strs = this.description.replaceAll("\\r", "").replaceAll("\\n", "").split("<br />");
		int indexRoom = getRoomIndex(strs);
		if (indexRoom == -1)
			return null;
		String locAndRoom = strs[indexRoom];
		String[] amphis = locAndRoom.split("Amphith&#233;&#226;tre ");
		String[] salles = locAndRoom.split("Salle ");
		String ret = null;
		if (salles.length == 2)
			ret = "Salle: ";
		else if (salles.length > 2)
			ret = "Salles: ";
		else if (amphis.length == 2)
			ret = "Amphithéâtre: ";
		else if (amphis.length > 2)
			ret = "Amphithéâtres: ";
		else
			return null;
		for (int i = 1; i < salles.length; i++) {
			if (i % 2 == 1)
				ret += salles[i] + (i + 1 == salles.length ? ", " : "");
		}
		for (int i = 1; i < amphis.length; i++) {
			if (i % 2 == 1)
				ret += amphis[i] + (i + 1 == amphis.length ? ", " : "");
		}
		return ret;
	}

}