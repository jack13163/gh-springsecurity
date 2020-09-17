package jack.gh.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/index")
@Controller
public class IndexController {
    @PreAuthorize("hasAuthority('role:add')")// 测试权限
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello spring security";
    }

    @PreAuthorize("hasRole('ADMIN')")// 测试角色
    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test spring security";
    }

    @RequestMapping("/loginError")
    @ResponseBody
    public String error() {
        return "error";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/whoimi")
    @ResponseBody
    public String whoimi() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
