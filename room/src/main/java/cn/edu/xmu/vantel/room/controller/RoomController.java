package cn.edu.xmu.vantel.room.controller;

import cn.edu.xmu.vantel.core.util.ReturnNo;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.model.Humidity;
import cn.edu.xmu.vantel.room.model.Temperature;
import cn.edu.xmu.vantel.room.service.HumidityService;
import cn.edu.xmu.vantel.room.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/room", produces = "application/json;charset=UTF-8")
public class RoomController {
    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private HumidityService humidityService;

    @PostMapping("/temperature")
    public ReturnObject<Object> uploadTemperature(@RequestBody Temperature temperature) {
        boolean isSuccess = temperatureService.save(temperature);
        if (isSuccess) {
            return new ReturnObject<>();
        }
        return new ReturnObject<>(ReturnNo.INTERNAL_SERVER_ERR);
    }

    @PostMapping("/humidity")
    public ReturnObject<Object> uploadHumidity(@RequestBody Humidity humidity) {
        boolean isSuccess = humidityService.save(humidity);
        if (isSuccess) {
            return new ReturnObject<>();
        }
        return new ReturnObject<>(ReturnNo.INTERNAL_SERVER_ERR);
    }
}
