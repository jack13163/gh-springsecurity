package jack.gh.security.service.impl;

import jack.gh.security.dao.RoleMapper;
import jack.gh.security.entity.Role;
import jack.gh.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role getRoleById(Integer id) {
        Role role = new Role();
        role.setId(id);
        return roleMapper.selectOne(role);
    }
}
