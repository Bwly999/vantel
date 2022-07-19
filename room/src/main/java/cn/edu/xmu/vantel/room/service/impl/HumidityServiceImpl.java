package cn.edu.xmu.vantel.room.service.impl;

import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.mapper.HumidityMapper;
import cn.edu.xmu.vantel.room.model.Humidity;
import cn.edu.xmu.vantel.room.model.Temperature;
import cn.edu.xmu.vantel.room.service.HumidityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("humidityService")
public class HumidityServiceImpl extends ServiceImpl<HumidityMapper, Humidity> implements HumidityService {
    private LambdaQueryWrapper<Humidity> getQueryWrapperByExample(Humidity example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<Humidity> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(example.getRoomId()).ifPresent(x -> queryWrapper.eq(Humidity::getRoomId, x));
        Optional.ofNullable(beginDate).ifPresent(x -> queryWrapper.ge(Humidity::getGmtCreate, x));
        Optional.ofNullable(endDate).ifPresent(x -> queryWrapper.le(Humidity::getGmtCreate, x));
        return queryWrapper;
    }

    private List<Humidity> listHumidityByExample(Humidity example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<Humidity> queryWrapper = getQueryWrapperByExample(example, beginDate, endDate);
        return list(queryWrapper);
    }
    @Override
    public ReturnObject<List<Humidity>> getRoomHumidity(Long roomId, LocalDateTime beginDate, LocalDateTime endDate) {
        Humidity example = Humidity.builder()
                .roomId(roomId)
                .build();

        return new ReturnObject<>(listHumidityByExample(example, beginDate, endDate));
    }
}
