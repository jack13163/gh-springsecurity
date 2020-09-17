package jack.gh.security.service.impl;

import jack.gh.security.dao.ResourceMapper;
import jack.gh.security.dao.RoleResourceMapper;
import jack.gh.security.entity.Resource;
import jack.gh.security.entity.RoleResource;
import jack.gh.security.service.ResourceService;
import jack.gh.security.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    RoleResourceMapper roleResourceMapper;

    @Override
    public List<RoleResource> getRoleResourcesByRoleId(Integer id) {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleId(id);
        return roleResourceMapper.select(roleResource);
    }
}
