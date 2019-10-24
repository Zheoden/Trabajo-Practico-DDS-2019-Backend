package modelo.interfaces;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.Arrays;
import java.util.List;

public interface Repository<T> extends WithGlobalEntityManager, TransactionalOps {
	default void persist(T entity) {
		withTransaction(() -> entityManager().persist(entity));
	}

	@SuppressWarnings("unchecked")
	default void persistAll(T... entities) {
		withTransaction(() -> {
			Arrays.stream(entities).forEach(entityManager()::persist);
		});
	}

	default void delete(T entity) {
		withTransaction(() -> entityManager().remove(entity));
	}

	default void update(T entity) {
		beginTransaction();
		entityManager().flush();
		commitTransaction();
	}

	List<T> all();

}
