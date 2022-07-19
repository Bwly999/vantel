package cn.edu.xmu.vantel.room.service.impl;

import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.mapper.TemperatureMapper;
import cn.edu.xmu.vantel.room.model.Temperature;
import cn.edu.xmu.vantel.room.service.TemperatureService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("temperatureService")
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements TemperatureService {
    private LambdaQueryWrapper<Temperature> getQueryWrapperByExample(Temperature example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<Temperature> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(example.getRoomId()).ifPresent(x -> queryWrapper.eq(Temperature::getRoomId, x));
        Optional.ofNullable(beginDate).ifPresent(x -> queryWrapper.ge(Temperature::getGmtCreate, x));
        Optional.ofNullable(endDate).ifPresent(x -> queryWrapper.le(Temperature::getGmtCreate, x));
        return queryWrapper;
    }

    private List<Temperature> listRoomByExample(Temperature example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<Temperature> queryWrapper = getQueryWrapperByExample(example, beginDate, endDate);
        return list(queryWrapper);
    }

    @Override
    public ReturnObject<List<Temperature>> getRoomTemperature(Long roomId, LocalDateTime beginDate, LocalDateTime endDate) {
        Temperature example = Temperature.builder()
                .roomId(roomId)
                .build();

        return new ReturnObject<>(listRoomByExample(example, beginDate, endDate));
    }
}
