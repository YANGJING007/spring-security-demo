package com.example.springsecuritydemo2.controller;
import com.example.springsecuritydemo2.dao.UserMapper;
import org.springframework.security.core.userdetails.User;
import com.example.springsecuritydemo2.entity.RetResult;
import com.example.springsecuritydemo2.entity.SysUser;
import com.example.springsecuritydemo2.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class DemoController {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN') or hasAnyAuthority('ROLE_HOME')")
    public String role() {
        return "role";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @ResponseBody
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public RetResult register(@ModelAttribute SysUser user) {
        RetResult result = securityService.register(user);
        return result;
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    @ResponseBody
    public RetResult addbook(@RequestParam String bookName) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
       User user = (User) auth.getPrincipal();
       String userName = user.getUsername();
       Integer userId = userMapper.findUserIdByName(userName);
       RetResult result =securityService.addbook(userId, bookName);
        return result;
    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.GET)
    @ResponseBody
    public RetResult deletebook(@RequestParam String bookName) {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        User user = (User) auth.getPrincipal();
        String userName = user.getUsername();
        Integer userId = userMapper.findUserIdByName(userName);
        RetResult result = securityService.deletebook(userId, bookName);
        return result;
    }
    @RequestMapping(value = "/showpage",method = RequestMethod.GET)
    @ResponseBody
    public RetResult showpage(@RequestParam Integer page){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        User user = (User) auth.getPrincipal();
        String userName = user.getUsername();
        Integer userId = userMapper.findUserIdByName(userName);
        RetResult result = securityService.showpage(page,userId);
        return result;
    }
}
