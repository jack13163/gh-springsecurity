package jack.gh.security.service.impl;

import jack.gh.security.dao.SysUserMapper;
import jack.gh.security.entity.SysUser;
import jack.gh.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findByName(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        return sysUserMapper.selectOne(sysUser);
    }
}
