package cn.edu.xmu.vantel.room.service.impl;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.mapper.TemperatureMapper;
import cn.edu.xmu.vantel.room.model.Temperature;
import cn.edu.xmu.vantel.room.service.TemperatureService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");

    @Override
    public ReturnObject<Map<String, Object>> getRoomTemperatureInHour(Long roomId, Integer totalHour) {
        LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime beginDateTime = endDateTime.minusHours(totalHour);

        Temperature example = Temperature.builder()
                .roomId(roomId)
                .build();
        List<Temperature> temperatureList = listRoomByExample(example, beginDateTime, endDateTime);
        temperatureList.forEach(x -> x.setGmtCreate(x.getGmtCreate().truncatedTo(ChronoUnit.HOURS)));
        Map<LocalDateTime, List<Temperature>> hourTemperatureMap = temperatureList.stream().collect(Collectors.groupingBy(BaseEntity::getGmtCreate, LinkedHashMap::new, Collectors.toList()));


        List<String> dateTime = hourTemperatureMap.keySet().stream().map(x -> x.format(dateTimeFormatter)).collect(Collectors.toList());
        List<DoubleSummaryStatistics> collect = hourTemperatureMap.values().stream()
                .map(list -> list.stream().mapToDouble(Temperature::getValue).summaryStatistics())
                .collect(Collectors.toList());
        List<Double> maxTemperature = collect.stream().map(DoubleSummaryStatistics::getMax).collect(Collectors.toList());
        List<Double> minTemperature = collect.stream().map(DoubleSummaryStatistics::getMin).collect(Collectors.toList());
        List<Double> avgTemperature = collect.stream().map(DoubleSummaryStatistics::getAverage).collect(Collectors.toList());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dateTime", dateTime);
        resultMap.put("maxTemperature", maxTemperature);
        resultMap.put("minTemperature", minTemperature);
        resultMap.put("avgTemperature", avgTemperature);
        return new ReturnObject<>(resultMap);
    }
}
