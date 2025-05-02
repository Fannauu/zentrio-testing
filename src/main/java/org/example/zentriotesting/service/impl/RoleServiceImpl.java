package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.Role;
import org.example.zentriotesting.model.entity.RoleName;
import org.example.zentriotesting.repository.RoleRepository;
import org.example.zentriotesting.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleNameByRoleId(UUID roleId) {
        System.out.println("Role Id : " +roleId);
        System.out.println("Role Name : "+roleRepository.getRoleNameByRoleId(roleId));
        return roleRepository.getRoleNameByRoleId(roleId);
    }

    @Override
    public List<Role> getAllRoles() {

        System.out.println("All Roles : " + roleRepository.getAllRoles());
        return roleRepository.getAllRoles();
    }

    @Override
    public Role getRoleIdByRoleName(RoleName roleName) {

        String role = roleName.toString();
        System.out.println("Role to String : " +role);
        System.out.println("Role id by role name : " + roleRepository.getRoleIdByRoleName(role));
        return roleRepository.getRoleIdByRoleName(role);
    }

    @Override
    public UUID getRoleId(RoleName roleName) {
        String role = roleName.toString();

        return roleRepository.getRoleId(role);
    }
}
