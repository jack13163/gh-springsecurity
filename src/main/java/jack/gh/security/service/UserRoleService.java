package jack.gh.security.service;

import jack.gh.security.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getUserRolesByUserId(Integer uid);
}
