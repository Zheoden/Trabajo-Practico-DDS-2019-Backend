package repository;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.Arrays;
import java.util.List;

public interface Repository<E> extends WithGlobalEntityManager, TransactionalOps {
    default void persist(E entity) {
        withTransaction(() -> entityManager().persist(entity));
    }

    default void persistAll(E... entities) {
        withTransaction(() -> {
            Arrays.stream(entities).forEach(entityManager()::persist);
        });
    }

    default void delete(E entity) {
        withTransaction(() -> entityManager().remove(entity));
    }

    List<E> all();
}
