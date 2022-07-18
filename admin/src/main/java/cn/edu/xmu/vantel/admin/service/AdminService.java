package cn.edu.xmu.vantel.admin.service;

import cn.edu.xmu.vantel.admin.model.Admin;
import cn.edu.xmu.vantel.admin.model.vo.LoginRetVo;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {
    ReturnObject<LoginRetVo> login(Admin admin);

    ReturnObject<Object> loginUp(Admin admin);

    ReturnObject<Object> changeAdminInfo(Admin admin);
}
