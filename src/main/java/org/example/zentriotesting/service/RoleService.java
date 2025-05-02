package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Role;
import org.example.zentriotesting.model.entity.RoleName;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role getRoleNameByRoleId(UUID roleId);

    List<Role> getAllRoles();

    Role getRoleIdByRoleName(RoleName roleName);

    UUID getRoleId(RoleName roleName);
}
