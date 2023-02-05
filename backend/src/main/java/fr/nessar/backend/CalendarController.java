package fr.nessar.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

	public CalendarController(ImageDao imageDao) {
	}

	@GetMapping(value = "/getcal/{group}", produces = "text/calendar; charset=UTF-8")
	public ResponseEntity<?> getImage(@PathVariable("group") String group) {
		return ResponseEntity
				.ok()
				.contentType(MediaType.TEXT_PLAIN)
				.body("BEGIN:VCALENDAR");
	}
}
