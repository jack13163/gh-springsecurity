package jack.gh.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity// 开启spring security
@EnableGlobalMethodSecurity(prePostEnabled=true)// 开启权限认证注解
public class MyWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 成功和失败页面不需要验证
        http.formLogin()
                .loginPage("/index/login")// 设置自定义登录页
                .loginProcessingUrl("/doLogin")// 用户提交的表单地址
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .permitAll();// 这些页面不需要身份认证

        // 记住我
        http.rememberMe()
                .rememberMeParameter("remember_me")// 和登录表单中的name保持一致
                .userDetailsService(userDetailsService())
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60);

        // 对请求授权
        http.authorizeRequests()
                //.antMatchers("/index/login", "/index/doLogin").permitAll()// 这些页面不需要身份认证,其他请求需要认证
                .anyRequest() // 任何请求
                .authenticated(); // 都需要身份认证

        // 暂时停用csrf，否则会影响验证
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内存中认证
//        String pass = passwordEncoder().encode("123");
//        System.out.println(pass);
//        auth.inMemoryAuthentication().withUser("jack").password(pass).roles("Admin");
        // 在数据库中认证，Dao认证方式
//        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
        // 在数据库中认证，用户名加密码方式
        auth.authenticationProvider(myAuthenticationProvider);
    }
}
