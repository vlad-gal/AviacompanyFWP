//package by.halatsevich.company.trash;
//
//import by.halatsevich.company.model.dao.ColumnName;
//import by.halatsevich.company.model.entity.Entity;
//
//import java.util.Map;
//
//public class EntityFactory<T extends Entity> {
//    private static final EntityFactory instance = new EntityFactory();
//    private final EntityProvider provider = new EntityProvider();
//
//    private EntityFactory() {
//    }
//
//    public static EntityFactory getInstance() {
//        return instance;
//    }
//
//    public T createEntity(Map<String, Object> entityData) {
//        String entityName = (String) entityData.get(ColumnName.ENTITY_NAME);
//        EntityCreator<T> creator = provider.defineCreator(entityName);
//        return creator.create(entityData);
//    }
//}
