package org.myproject.core.utils;

import org.myproject.core.dto.UserDTO;
import org.myproject.core.persistence.entity.UserEntity;

public class UserBeanUtil {

    // Get data entity to DTO
    public static UserDTO entity2Dto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setFullName(entity.getFullName());
        dto.setPassword(entity.getPassword());
        dto.setCreateDate(entity.getCreateDate());
        dto.setRoleDTO(RoleBeanUtil.entity2Dto(entity.getRoleEntity()));
        return dto;
    }

    // else
    public static UserEntity dto2Entity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setFullName(dto.getFullName());
        entity.setPassword(dto.getPassword());
        entity.setCreateDate(dto.getCreateDate());
        entity.setRoleEntity(RoleBeanUtil.dto2Entity(dto.getRoleDTO()));
        return entity;
    }
}

