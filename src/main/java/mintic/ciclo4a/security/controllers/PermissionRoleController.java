package mintic.ciclo4a.security.controllers;

import lombok.AllArgsConstructor;
import mintic.ciclo4a.security.models.Permission;
import mintic.ciclo4a.security.models.PermissionRole;
import mintic.ciclo4a.security.models.Role;
import mintic.ciclo4a.security.repositories.PermissionRepository;
import mintic.ciclo4a.security.repositories.PermissionRoleRepository;
import mintic.ciclo4a.security.repositories.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/permissions-roles")
@AllArgsConstructor
public class PermissionRoleController {
    private final PermissionRoleRepository permissionRoleRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @GetMapping("")
    List<PermissionRole> getAll() {
        return this.permissionRoleRepository.findAll();
    }

    @GetMapping("{id}")
    PermissionRole getById(@PathVariable String id) {
        return this.permissionRoleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping("role/{idRole}")
    List<PermissionRole> getAllByRole(@PathVariable String idRole) {
        Role role = this.roleRepository.findById(idRole).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return this.permissionRoleRepository.findByRole(role);
    }

    @GetMapping("permission/{idPermission}")
    List<PermissionRole> getAllByPermission(@PathVariable String idPermission) {
        Permission permission = this.permissionRepository.findById(idPermission).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        return this.permissionRoleRepository.findByPermission(permission);
    }

    @PostMapping("role/{roleId}/permission/{permissionId}")
    PermissionRole create(@PathVariable String roleId, @PathVariable String permissionId) {
        Role role = this.roleRepository.findById(roleId).orElse(null);
        Permission permission = this.permissionRepository.findById(permissionId).orElse(null);
        if (role == null || permission == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        boolean exist = this.checkIfAlreadyExists(role, permission);
        if (exist) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            PermissionRole permissionRole = new PermissionRole(role, permission);
            return this.permissionRoleRepository.save(permissionRole);
        }
    }

    boolean checkIfAlreadyExists(Role role, Permission permission) {
        /*
        * Verificar si existe relación entre el rol y el permiso dado
        * */
        List<PermissionRole> items = this.permissionRoleRepository.findByRole(role);
        for (int i = 0; i < items.size(); i++) {
            boolean exist = items.get(i).getPermission().getId().equals(permission.getId());
            if (exist) {
                return true;
            }
        }
        return false;
    }

}
