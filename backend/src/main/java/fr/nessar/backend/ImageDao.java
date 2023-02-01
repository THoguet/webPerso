package fr.nessar.backend;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDao implements Dao<Image> {

	private final Map<Long, Image> images = new HashMap<>();

	public ImageDao() {
		final ClassPathResource imgFile = new ClassPathResource("space.jpg");
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(imgFile.getFile().toPath());
			Image img = new Image("space.jpg", fileContent);
			images.put(img.getId(), img);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<Image> retrieve(final long id) {
		return Optional.ofNullable(images.get(id));
	}

	@Override
	public List<Image> retrieveAll() {
		List<Image> retImages = new ArrayList<Image>();
		for (Image img : images.values()) {
			retImages.add(img);
		}
		return retImages;
	}

	@Override
	public void create(final Image img) {
		images.put(img.getId(), img);
	}

	@Override
	public void update(final Image img, final String[] params) {
		// Not used
	}

	@Override
	public void delete(final Image img) {
		images.remove(img.getId());
	}

	@Override
	public Optional<Image> retrieve(String name) {
		for (Image img : images.values()) {
			if (img.getName().equals(name)) {
				return Optional.of(img);
			}
		}
		return Optional.empty();
	}
}
