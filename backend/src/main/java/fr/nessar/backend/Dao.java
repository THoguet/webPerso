package fr.nessar.backend;

import java.util.Optional;
import java.util.List;

public interface Dao<T> {

	void create(final T t);

	Optional<T> retrieve(final long id);

	Optional<T> retrieve(final String name);

	List<T> retrieveAll();

	void update(final T t, final String[] params);

	void delete(final T t);
}
