package fr.nessar.backend.u;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import fr.nessar.backend.VCal.VCalendar;

public class UCalendar {

	private final String group;
	private final Date start;
	private final Date end;
	private final String calName;
	private final ArrayList<UEvent> events;
	private final HttpPost post = new HttpPost(UWebAPI.DOMAIN + UWebAPI.CALENDARDATA);
	public static final String FORMAT = "dd-MM-yyyy";
	public static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat(FORMAT);

	public UCalendar(String group, Date start, Date end, List<String> moduleUnwanted) {
		this.group = group;
		this.start = start;
		this.end = end;
		this.calName = group + "_" + DATEFORMAT.format(start) + "_" + DATEFORMAT.format(end);
		this.events = this.getCalendar();
		this.sortList(moduleUnwanted);
	}

	public ArrayList<UEvent> getCalendar() {
		final List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("start", DATEFORMAT.format(this.start)));
		params.add(new BasicNameValuePair("end", DATEFORMAT.format(this.end)));
		params.add(new BasicNameValuePair("resType", "103"));
		params.add(new BasicNameValuePair("calView", "agendaDay"));
		params.add(new BasicNameValuePair("federationIds[]", this.group));
		this.post.addHeader("Connection", "keep-alive");
		this.post.addHeader("Pragma", "no-cache");
		this.post.addHeader("Cache-Control", "no-cache");
		this.post.addHeader("Accept", "application/json");
		this.post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		try {
			this.post.setEntity(new UrlEncodedFormEntity(params));
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = (CloseableHttpResponse) client.execute(this.post);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.OK.value())
				throw new RuntimeException("Excepted 200 but got :" + response.getStatusLine().getStatusCode());
			String calString = EntityUtils.toString(response.getEntity());
			Gson gson = new Gson();
			ArrayList<UEvent> events = gson.fromJson(calString, new TypeToken<ArrayList<UEvent>>() {
			}.getType());
			client.close();
			return events;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private void sortList(List<String> moduleUnwanted) {
		if (moduleUnwanted != null)
			for (int i = 0; i < this.events.size(); i++) {
				String[] mods = this.events.get(i).modules;
				if (mods != null) {
					String mod = mods[0];
					for (String UWmod : moduleUnwanted) {
						if (mod.contains(UWmod))
							this.events.remove(i);
					}
				}
			}

	}

	public static void main(String[] args) {
		Set<String> loggers = new HashSet<>(Arrays.asList("org.apache.http", "groovyx.net.http"));

		for (String log : loggers) {
			Logger logger = (Logger) LoggerFactory.getLogger(log);
			logger.setLevel(Level.INFO);
			logger.setAdditive(false);
		}
		try {
			UCalendar cal = new UCalendar("INF601A12", DATEFORMAT.parse("01-01-2023"), DATEFORMAT.parse("31-07-2023"),
					null);
			VCalendar vcal = new VCalendar(cal);
			FileWriter f = new FileWriter("test.ics");
			f.write("\ufeff");
			f.write(vcal.toString());
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UEvent> getEvents() {
		return this.events;
	}

	public String getGroup() {
		return group;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public String getCalName() {
		return calName;
	}

	public HttpPost getPost() {
		return post;
	}

	public static String getFormat() {
		return FORMAT;
	}

	public static SimpleDateFormat getDateformat() {
		return DATEFORMAT;
	}
}
