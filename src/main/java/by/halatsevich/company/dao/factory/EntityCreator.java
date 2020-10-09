package by.halatsevich.company.dao.factory;

import by.halatsevich.company.entity.Entity;

import java.util.Map;

public interface EntityCreator<T extends Entity> {
    T create(Map<String, Object> entityData);
}
