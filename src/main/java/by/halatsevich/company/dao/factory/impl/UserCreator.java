package by.halatsevich.company.dao.factory.impl;

import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.factory.EntityCreator;
import by.halatsevich.company.entity.User;

import java.util.Map;

public class UserCreator implements EntityCreator<User> {
    @Override
    public User create(Map<String, Object> userData) {
        int id = (int) userData.get(SqlColumnName.USER_ID);

//        entityData.get()
        return new User();
    }
}
