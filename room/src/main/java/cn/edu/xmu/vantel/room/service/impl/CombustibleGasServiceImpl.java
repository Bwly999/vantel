package cn.edu.xmu.vantel.room.service.impl;

import cn.edu.xmu.vantel.room.mapper.CombustibleGasMapper;
import cn.edu.xmu.vantel.room.model.CombustibleGas;
import cn.edu.xmu.vantel.room.service.CombustibleGasService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("combustibleGasService")
public class CombustibleGasServiceImpl extends ServiceImpl<CombustibleGasMapper, CombustibleGas> implements CombustibleGasService {
}
