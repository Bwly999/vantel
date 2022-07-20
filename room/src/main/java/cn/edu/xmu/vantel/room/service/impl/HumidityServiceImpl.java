package cn.edu.xmu.vantel.room.service.impl;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.mapper.HumidityMapper;
import cn.edu.xmu.vantel.room.model.Humidity;
import cn.edu.xmu.vantel.room.model.Temperature;
import cn.edu.xmu.vantel.room.service.HumidityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");

    @Override
    public ReturnObject<Map<String, Object>> getRoomHumidityInHour(Long roomId, Integer totalHour) {
        LocalDateTime endDateTime = LocalDateTime.now();
        LocalDateTime beginDateTime = endDateTime.minusHours(totalHour);

        Humidity example = Humidity.builder()
                .roomId(roomId)
                .build();
        List<Humidity> humidityList = listHumidityByExample(example, beginDateTime, endDateTime);
        humidityList.forEach(x -> x.setGmtCreate(x.getGmtCreate().truncatedTo(ChronoUnit.HOURS)));
        Map<LocalDateTime, List<Humidity>> hourHumidityMap = humidityList.stream().collect(Collectors.groupingBy(Humidity::getGmtCreate, LinkedHashMap::new, Collectors.toList()));


        List<String> dateTime = hourHumidityMap.keySet().stream().map(x -> x.format(dateTimeFormatter)).collect(Collectors.toList());
        List<DoubleSummaryStatistics> collect = hourHumidityMap.values().stream()
                .map(list -> list.stream().mapToDouble(Humidity::getValue).summaryStatistics())
                .collect(Collectors.toList());
        List<Double> avgHumidity = collect.stream().map(DoubleSummaryStatistics::getAverage).collect(Collectors.toList());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dateTime", dateTime);
        resultMap.put("humidity", avgHumidity);
        return new ReturnObject<>(resultMap);
    }
}
