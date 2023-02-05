package fr.nessar.backend;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class UCalendar {

	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	public UCalendar(String group) {
		// prout
	}

	public static String getCalendar(Date start, Date end, String group) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final HttpPost post = new HttpPost(UWebAPI.DOMAIN + UWebAPI.CALENDARDATA);
		final List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("start", dateFormat.format(start)));
		params.add(new BasicNameValuePair("end", dateFormat.format(end)));
		params.add(new BasicNameValuePair("resType", "103"));
		params.add(new BasicNameValuePair("calView", "agendaDay"));
		params.add(new BasicNameValuePair("federationIds[]", group));
		post.addHeader("Connection", "keep-alive");
		post.addHeader("Pragma", "no-cache");
		post.addHeader("Cache-Control", "no-cache");
		post.addHeader("Accept", "application/json");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.setEntity(new UrlEncodedFormEntity(params));
		try (CloseableHttpClient client = HttpClients.createDefault();
				CloseableHttpResponse response = (CloseableHttpResponse) client.execute(post)) {
			final int statusCode = response.getStatusLine().getStatusCode();
			assert (statusCode != HttpStatus.OK.value());
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date start = formatter.parse("01-01-2023");
			Date end;
			end = formatter.parse("31-07-2023");
			String s = getCalendar(start, end, "INF601A12");
			Gson gson = new Gson();
			ArrayList<UEvent> events = gson.fromJson(s, new TypeToken<ArrayList<UEvent>>() {
			}.getType());
			for (UEvent event : events) {
				System.out.println(event.description);
				System.out.println("SITES : " + (event.sites == null ? "null" : event.sites[0]));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void close() throws IOException {
		httpClient.close();
	}
}
