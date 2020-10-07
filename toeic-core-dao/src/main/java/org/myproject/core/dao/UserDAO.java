package org.myproject.core.dao;

import org.myproject.core.data.dao.GenericDao;
import org.myproject.core.persistence.entity.UserEntity;

public interface UserDAO extends GenericDao<Integer, UserEntity> {
    UserEntity isUserExist(String name, String password);
    UserEntity findRoleByUser(String name, String password);
}
