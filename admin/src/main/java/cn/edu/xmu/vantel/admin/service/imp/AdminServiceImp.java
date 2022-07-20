package cn.edu.xmu.vantel.admin.service.imp;

import cn.edu.xmu.vantel.admin.mapper.AdminMapper;
import cn.edu.xmu.vantel.admin.model.Admin;
import cn.edu.xmu.vantel.admin.model.vo.LoginRetVo;
import cn.edu.xmu.vantel.admin.service.AdminService;
import cn.edu.xmu.vantel.core.util.JwtHelper;
import cn.edu.xmu.vantel.core.util.ReturnNo;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImp extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public ReturnObject<LoginRetVo> login(Admin admin) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Admin::getUsername, admin.getUsername());

        Admin daoAdmin = getOne(queryWrapper);
        if (daoAdmin == null || !admin.getPassword().equals(daoAdmin.getPassword())) {
            return new ReturnObject<>(ReturnNo.CUSTOMER_INVALID_ACCOUNT);
        }

        String token = JwtHelper.createToken(daoAdmin.getId(), daoAdmin.getUsername());
        return new ReturnObject<>(new LoginRetVo(token));
    }

    @Override
    public ReturnObject<Object> loginUp(Admin admin) {
        Admin insertAdmin = Admin.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .build();

        boolean isSuccess = save(insertAdmin);
        if (isSuccess) {
            return new ReturnObject<>();
        }
        return new ReturnObject<>(ReturnNo.INTERNAL_SERVER_ERR);
    }

    @Override
    public ReturnObject<Object> changeAdminInfo(Admin admin) {
        updateById(admin);
        return new ReturnObject<>();
    }
}
