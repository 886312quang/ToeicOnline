package org.myproject.api.test;

import org.myproject.core.dao.RoleDao;
import org.myproject.core.daoimpl.RoleDaoImpl;
import org.testng.annotations.Test;

public class ApiTest {
    @Test
    public void checkFindProperty() {
        RoleDao roleDao = new RoleDaoImpl();
        String property = null;
        String value = null;
        String sortExpression = null;
        String sortDirection = null;

        Object objects = roleDao.findByProperty(property, value, sortExpression, sortDirection);
    }
}
