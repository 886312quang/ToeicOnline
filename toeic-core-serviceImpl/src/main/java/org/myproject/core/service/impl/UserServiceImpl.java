package org.myproject.core.service.impl;

import org.myproject.core.dao.UserDAO;
import org.myproject.core.daoimpl.UserDaoImpl;
import org.myproject.core.dto.UserDTO;
import org.myproject.core.persistence.entity.UserEntity;
import org.myproject.core.service.UserService;
import org.myproject.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {
    public UserDTO isUserExist(UserDTO dto) {
        UserDAO userDAO = new UserDaoImpl(); // Moi thang co 1 session rieng

        UserEntity entity = userDAO.isUserExist(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public UserDTO findRoleByUser(UserDTO dto) {
        UserDAO userDAO = new UserDaoImpl();

        UserEntity entity = userDAO.isUserExist(dto.getName(), dto.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }
}
