package jack.gh.security.service.impl;

import jack.gh.security.dao.ResourceMapper;
import jack.gh.security.dao.SysUserMapper;
import jack.gh.security.entity.Resource;
import jack.gh.security.entity.SysUser;
import jack.gh.security.service.ResourceService;
import jack.gh.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public Resource getResourceById(Integer id) {
        return resourceMapper.selectByPrimaryKey(id);
    }
}
