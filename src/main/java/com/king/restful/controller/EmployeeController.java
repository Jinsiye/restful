package com.king.restful.controller;

import com.king.restful.dao.DepartmentDao;
import com.king.restful.dao.EmployeeDao;
import com.king.restful.entities.Department;
import com.king.restful.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/emp")
    public String toAddPage(ModelMap modelMap) {
        Collection<Department> departments = departmentDao.getDepartments();
        modelMap.addAttribute("depts", departments);
        return "add";
    }

    @PostMapping("/emp")
    public String addEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/board";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, ModelMap modelMap) {
        //需要将员工信息进行回显
        Employee employee = employeeDao.getEmpById(id);
        //将员工放入请求域
        modelMap.put("emp", employee);
        //部门也需要回显，所以需要将部门信息放入请求域中
        Collection<Department> departments = departmentDao.getDepartments();
        modelMap.addAttribute("depts", departments);
        //跳转到add页面
        return "add";
    }


    @PutMapping("/emp")
    public String editEmployee(Employee employee) {
        //保存修改的employee
        employeeDao.save(employee);
        //重定向到dashBoard页面
        return "redirect:/board";

    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) throws Exception {
        if (id == null)
            throw new Exception();
        //删除id对应的用户
        employeeDao.deleteEmpById(id);
        //重定向到dashborad页面
        return "redirect:/board";

    }
}
