package jack.gh.security.service.impl;

import jack.gh.security.dao.UserRoleMapper;
import jack.gh.security.entity.UserRole;
import jack.gh.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> getUserRolesByUserId(Integer uid) {
        UserRole userRole = new UserRole();
        userRole.setAdminId(uid);
        return userRoleMapper.select(userRole);
    }
}
