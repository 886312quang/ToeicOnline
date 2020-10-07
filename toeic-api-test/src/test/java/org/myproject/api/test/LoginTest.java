package org.myproject.api.test;

import org.apache.log4j.Logger;
import org.myproject.core.dao.UserDAO;
import org.myproject.core.daoimpl.UserDaoImpl;
import org.myproject.core.persistence.entity.UserEntity;
import org.testng.annotations.Test;

public class LoginTest {
    private final Logger log = Logger.getLogger(this.getClass());
    @Test
    public void checkIsUserExist() {
        UserDAO userDAO = new UserDaoImpl();

        String name = "admin";
        String password = "123456";

        UserEntity entity = userDAO.isUserExist(name, password);

        if (entity != null) {
            log.error("Login success");
        } else {
            log.error("Login fail!");
        }
    }
    @Test
    public void findRoleByUser() {
        UserDAO userDAO = new UserDaoImpl();

        String name = "admin";
        String password = "123456";

        UserEntity entity = userDAO.findRoleByUser(name, password);
        log.error(entity.getRoleEntity().getName() + "-" + entity.getRoleEntity().getRoleId());
    }

}
