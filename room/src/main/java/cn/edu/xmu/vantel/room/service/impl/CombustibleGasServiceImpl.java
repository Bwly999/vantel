package cn.edu.xmu.vantel.room.service.impl;

import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.mapper.CombustibleGasMapper;
import cn.edu.xmu.vantel.room.model.CombustibleGas;
import cn.edu.xmu.vantel.room.model.Humidity;
import cn.edu.xmu.vantel.room.service.CombustibleGasService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("combustibleGasService")
public class CombustibleGasServiceImpl extends ServiceImpl<CombustibleGasMapper, CombustibleGas> implements CombustibleGasService {
    private LambdaQueryWrapper<CombustibleGas> getQueryWrapperByExample(CombustibleGas example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<CombustibleGas> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(example.getRoomId()).ifPresent(x -> queryWrapper.eq(CombustibleGas::getRoomId, x));
        Optional.ofNullable(beginDate).ifPresent(x -> queryWrapper.ge(CombustibleGas::getGmtCreate, x));
        Optional.ofNullable(endDate).ifPresent(x -> queryWrapper.le(CombustibleGas::getGmtCreate, x));
        return queryWrapper;
    }

    private List<CombustibleGas> listHumidityByExample(CombustibleGas example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<CombustibleGas> queryWrapper = getQueryWrapperByExample(example, beginDate, endDate);
        return list(queryWrapper);
    }
    @Override
    public ReturnObject<List<CombustibleGas>> getRoomCombustibleGas(Long roomId, LocalDateTime beginDate, LocalDateTime endDate) {
        CombustibleGas example = CombustibleGas.builder()
                .roomId(roomId)
                .build();

        return new ReturnObject<>(listHumidityByExample(example, beginDate, endDate));
    }
}
