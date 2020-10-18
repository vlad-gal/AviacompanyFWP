package by.halatsevich.company.model.factory;

import by.halatsevich.company.model.entity.Entity;

import java.util.Map;

public interface EntityCreator<T extends Entity> {
    T create(Map<String, Object> entityParameter);
}
