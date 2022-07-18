package cn.edu.xmu.vantel.room.service.imp;

import cn.edu.xmu.vantel.room.mapper.TemperatureMapper;
import cn.edu.xmu.vantel.room.model.Temperature;
import cn.edu.xmu.vantel.room.service.TemperatureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("temperatureService")
public class TemperatureServiceImp extends ServiceImpl<TemperatureMapper, Temperature> implements TemperatureService {
}
