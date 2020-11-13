package by.halatsevich.company.model.factory;

import by.halatsevich.company.model.entity.Entity;

import java.util.Map;

/**
 * The interface represents entity creator.
 *
 * @param <T> the type parameter
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface EntityCreator<T extends Entity> {

    /**
     * Create entity.
     *
     * @param entityParameter the entity parameter
     * @return the entity
     */
    T create(Map<String, Object> entityParameter);
}
