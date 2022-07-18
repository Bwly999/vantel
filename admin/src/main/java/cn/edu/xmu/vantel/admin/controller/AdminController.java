package cn.edu.xmu.vantel.admin.controller;

import cn.edu.xmu.vantel.admin.model.Admin;
import cn.edu.xmu.vantel.admin.service.AdminService;
import cn.edu.xmu.vantel.core.util.Common;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ReturnObject<String> login(@RequestBody Admin admin) {
        return new ReturnObject<>("11");
    }
}
