package cn.edu.xmu.vantel.room.service;

import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.model.CombustibleGas;
import cn.edu.xmu.vantel.room.model.Humidity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface CombustibleGasService extends IService<CombustibleGas> {
    ReturnObject<List<CombustibleGas>> getRoomCombustibleGas(Long roomId, LocalDateTime beginDate, LocalDateTime endDate);

    ReturnObject<Map<String, Object>> getRoomCombustibleGasInHour(Long roomId, Integer totalHour);
}
