package cn.edu.xmu.vantel.room.service;

import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.model.Humidity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

public interface HumidityService extends IService<Humidity> {
    ReturnObject<List<Humidity>> getRoomHumidity(Long roomId, LocalDateTime beginDate, LocalDateTime endDate);
}
