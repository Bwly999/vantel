package cn.edu.xmu.vantel.admin.controller;

import cn.edu.xmu.vantel.admin.model.Admin;
import cn.edu.xmu.vantel.admin.model.vo.LoginRetVo;
import cn.edu.xmu.vantel.admin.service.AdminService;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin", produces = "application/json;charset=UTF-8")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public ReturnObject<LoginRetVo> login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }

    /**
     * 注册
     * @param admin
     * @return
     */
    @PostMapping("")
    public ReturnObject<Object> loginUp(@RequestBody Admin admin) {
        return adminService.loginUp(admin);
    }

    @PutMapping("")
    public ReturnObject<Object> changeAdminInfo(@RequestBody Admin admin) {
        return adminService.changeAdminInfo(admin);
    }
}
