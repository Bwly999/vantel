package cn.edu.xmu.vantel.admin.service.imp;

import cn.edu.xmu.vantel.admin.mapper.AdminMapper;
import cn.edu.xmu.vantel.admin.model.Admin;
import cn.edu.xmu.vantel.admin.model.vo.LoginRetVo;
import cn.edu.xmu.vantel.admin.service.AdminService;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImp extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public ReturnObject<LoginRetVo> login(Admin admin) {
        return null;
    }
}
