package org.myproject.core.test;

import org.myproject.core.dao.RoleDao;
import org.myproject.core.daoimpl.RoleDaoImpl;
import org.myproject.core.persistence.entity.RoleEntity;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleTest {
    @Test
    public void checkFindAll() {
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list = roleDao.findAll();
    }

    @Test
    public void checkUpdateRole() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(2);
        roleEntity.setName("USER");

        roleDao.update(roleEntity);
    }

    @Test
    public void checkSaveRole() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(1);
        roleEntity.setName("ADMIN");

        roleDao.save(roleEntity);
    }

    @Test
    public void findById() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity;
        roleEntity = roleDao.findById(1);
    }

    @Test
    public void checkDelete() {
        List<Integer> listId = new ArrayList<Integer>();
        listId.add(1);
        listId.add(2);
        RoleDao roleDao = new RoleDaoImpl();
        Integer count = roleDao.delete(listId);
    }
}
