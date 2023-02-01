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
public class ImageController {

	@Autowired
	private ObjectMapper mapper;

	private final ImageDao imageDao;

	public ImageController(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	@GetMapping(value = "/images/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> getImage(@PathVariable("name") String name) {
		var img = imageDao.retrieve(name);
		if (img.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		byte[] bytes = img.get().getData();
		return ResponseEntity
				.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(bytes);
	}

	@GetMapping(value = "/images", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ArrayNode getImageList() {
		ArrayNode nodes = mapper.createArrayNode();
		for (Image image : imageDao.retrieveAll()) {
			var imageNode = mapper.createObjectNode();
			imageNode.put("title", image.getName());
			imageNode.put("id", image.getId());
			nodes.add(imageNode);
		}
		return nodes;
	}

}
