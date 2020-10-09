package by.halatsevich.company.dao.factory;

import by.halatsevich.company.dao.factory.impl.UserCreator;

public enum CreatorType {
    USER(new UserCreator());

    private EntityCreator creator;

    CreatorType(EntityCreator creator) {
        this.creator = creator;
    }

    public EntityCreator getCreator() {
        return creator;
    }
}
