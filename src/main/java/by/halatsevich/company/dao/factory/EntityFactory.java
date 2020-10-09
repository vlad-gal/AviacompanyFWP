package by.halatsevich.company.dao.factory;

import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.entity.Entity;

import java.util.Map;

public class EntityFactory<T extends Entity> {
    private static final EntityFactory instance = new EntityFactory();
    private final EntityProvider provider = new EntityProvider();

    private EntityFactory() {
    }

    public static EntityFactory getInstance() {
        return instance;
    }

    public T createEntity(Map<String, Object> entityData) {
        String entityName = (String) entityData.get(SqlColumnName.ENTITY_NAME);
        EntityCreator<T> creator = provider.defineCreator(entityName);
        return creator.create(entityData);
    }
}
