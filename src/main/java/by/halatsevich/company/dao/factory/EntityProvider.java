package by.halatsevich.company.dao.factory;

public class EntityProvider {
    public EntityCreator defineCreator(String name) {
        return CreatorType.valueOf(name.toUpperCase()).getCreator();
    }
}
