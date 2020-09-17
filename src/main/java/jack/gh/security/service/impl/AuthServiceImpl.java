package jack.gh.security.service.impl;

import jack.gh.security.entity.*;
import jack.gh.security.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleResourceService roleResourceService;

    @Autowired
    ResourceService resourceService;

    /**
     * 从数据库中加载用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser sysUser = userService.findByName(s);
        if(sysUser != null) {
            // 查询用户的角色
            Integer id = sysUser.getId();
            List<UserRole> userRoles = userRoleService.getUserRolesByUserId(id);
            List<String> roleNames = new ArrayList<>();
            List<String> authorities = new ArrayList<>();
            for (int i = 0; i < userRoles.size(); i++) {
                // 添加用户角色
                Role role = roleService.getRoleById(userRoles.get(i).getRoleId());
                String roleName = "ROLE_" + role.getName().toUpperCase();// 角色名
                roleNames.add(roleName);
                // 添加角色对应的权限
                List<RoleResource> roleResources = roleResourceService.getRoleResourcesByRoleId(role.getId());
                for (int j = 0; j < roleResources.size(); j++) {
                    Resource resource = resourceService.getResourceById(roleResources.get(j).getResourceId());
                    if(resource != null) {
                        String permissions = resource.getPermissions();
                        if (!StringUtils.isEmpty(permissions)) {
                            authorities.add(permissions);
                        }
                    }
                }
            }
            List<String> rolesAndPermissions = new ArrayList<>();
            rolesAndPermissions.addAll(roleNames);
            rolesAndPermissions.addAll(authorities);
            List<GrantedAuthority> roles = AuthorityUtils.commaSeparatedStringToAuthorityList(rolesAndPermissions.stream().collect(Collectors.joining(",")));
            // 返回spring security所需要的用户信息
            UserDetails userDetails = new User(sysUser.getUsername(), sysUser.getPassword(), roles);
            return userDetails;
        }
        return null;
    }
}
