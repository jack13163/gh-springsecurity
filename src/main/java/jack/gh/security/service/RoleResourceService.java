package jack.gh.security.service;

import jack.gh.security.entity.RoleResource;

import java.util.List;

public interface RoleResourceService {
    List<RoleResource> getRoleResourcesByRoleId(Integer id);
}
