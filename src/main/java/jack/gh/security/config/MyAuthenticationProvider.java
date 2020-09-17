package jack.gh.security.config;

import jack.gh.security.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AuthServiceImpl authServiceImpl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 获取表单中的用户名、密码、用户角色和权限
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = authServiceImpl.loadUserByUsername(name);

        // 认证流程，判断用户是否存在
        if(userDetails != null){
            // 判断密码是否正确
            if(passwordEncoder.matches(password, userDetails.getPassword())){
                // 获取用户权限
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
            }
        }

        // 认证失败
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 支持的验证方式为用户名加密码，另有Dao验证
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
