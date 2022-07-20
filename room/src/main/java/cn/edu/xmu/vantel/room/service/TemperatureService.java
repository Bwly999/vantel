package cn.edu.xmu.vantel.room.service;

import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.model.Temperature;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TemperatureService extends IService<Temperature> {
    ReturnObject<List<Temperature>> getRoomTemperature(Long roomId, LocalDateTime beginDate, LocalDateTime endDate);

    ReturnObject<Map<String, Object>> getRoomTemperatureInHour(Long roomId, Integer totalHour);
}
