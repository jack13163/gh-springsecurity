package jack.gh.security.service;

import jack.gh.security.entity.SysUser;

public interface UserService {
    SysUser findByName(String username);
}
