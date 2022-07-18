package cn.edu.xmu.vantel.room.service.imp;

import cn.edu.xmu.vantel.room.mapper.HumidityMapper;
import cn.edu.xmu.vantel.room.model.Humidity;
import cn.edu.xmu.vantel.room.service.HumidityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("humidityService")
public class HumidityServiceImp extends ServiceImpl<HumidityMapper, Humidity> implements HumidityService {
}
