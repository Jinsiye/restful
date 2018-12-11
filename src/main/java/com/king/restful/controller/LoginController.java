package com.king.restful.controller;

import com.king.restful.dao.EmployeeDao;
import com.king.restful.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    EmployeeDao employeeDao;

    @PostMapping("/user/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(email) && "123456".equals(password)) {
            //登陆成功,重定向到main.html
            session.setAttribute("email", email);
            return "redirect:/board";
        }

        //登陆失败，重新返回到登陆页面，并且现实登陆失败消息
        map.put("msg", "账号或密码错误！");
        return "login";
    }

    @RequestMapping("/board")
    public String toDashboard(ModelMap modelMap) {
        Collection<Employee> employees = employeeDao.getAll();
        modelMap.addAttribute("emps", employees);
        return "Dashboard";
    }
}
