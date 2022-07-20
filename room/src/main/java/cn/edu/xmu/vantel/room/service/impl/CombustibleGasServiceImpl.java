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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service("combustibleGasService")
public class CombustibleGasServiceImpl extends ServiceImpl<CombustibleGasMapper, CombustibleGas> implements CombustibleGasService {
    private LambdaQueryWrapper<CombustibleGas> getQueryWrapperByExample(CombustibleGas example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<CombustibleGas> queryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(example.getRoomId()).ifPresent(x -> queryWrapper.eq(CombustibleGas::getRoomId, x));
        Optional.ofNullable(beginDate).ifPresent(x -> queryWrapper.ge(CombustibleGas::getGmtCreate, x));
        Optional.ofNullable(endDate).ifPresent(x -> queryWrapper.le(CombustibleGas::getGmtCreate, x));
        return queryWrapper;
    }

    private List<CombustibleGas> listCombustibleGasByExample(CombustibleGas example, LocalDateTime beginDate, LocalDateTime endDate) {
        LambdaQueryWrapper<CombustibleGas> queryWrapper = getQueryWrapperByExample(example, beginDate, endDate);
        return list(queryWrapper);
    }
    @Override
    public ReturnObject<List<CombustibleGas>> getRoomCombustibleGas(Long roomId, LocalDateTime beginDate, LocalDateTime endDate) {
        CombustibleGas example = CombustibleGas.builder()
                .roomId(roomId)
                .build();

        return new ReturnObject<>(listCombustibleGasByExample(example, beginDate, endDate));
    }
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");

    @Override
    public ReturnObject<Map<String, Object>> getRoomCombustibleGasInHour(Long roomId, Integer totalHour) {
        LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime beginDateTime = endDateTime.minusHours(totalHour);

        CombustibleGas example = CombustibleGas.builder()
                .roomId(roomId)
                .build();
        List<CombustibleGas> combustibleGasList = listCombustibleGasByExample(example, beginDateTime, endDateTime);
        combustibleGasList.forEach(x -> x.setGmtCreate(x.getGmtCreate().truncatedTo(ChronoUnit.HOURS)));
        Map<LocalDateTime, List<CombustibleGas>> combustibleGasMap = combustibleGasList.stream().collect(Collectors.groupingBy(CombustibleGas::getGmtCreate, LinkedHashMap::new, Collectors.toList()));


        List<String> dateTime = combustibleGasMap.keySet().stream().map(x -> x.format(dateTimeFormatter)).collect(Collectors.toList());
        List<DoubleSummaryStatistics> collect = combustibleGasMap.values().stream()
                .map(list -> list.stream().mapToDouble(CombustibleGas::getDensity).summaryStatistics())
                .collect(Collectors.toList());
        List<Double> avgDensity = collect.stream().map(DoubleSummaryStatistics::getAverage).collect(Collectors.toList());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dateTime", dateTime);
        resultMap.put("density", avgDensity);
        return new ReturnObject<>(resultMap);
    }
}
