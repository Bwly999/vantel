package cn.edu.xmu.vantel.room.controller;

import cn.edu.xmu.vantel.core.constant.DateTimeConstants;
import cn.edu.xmu.vantel.core.util.ReturnNo;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.model.CombustibleGas;
import cn.edu.xmu.vantel.room.model.Humidity;
import cn.edu.xmu.vantel.room.model.Room;
import cn.edu.xmu.vantel.room.model.Temperature;
import cn.edu.xmu.vantel.room.service.CombustibleGasService;
import cn.edu.xmu.vantel.room.service.HumidityService;
import cn.edu.xmu.vantel.room.service.RoomService;
import cn.edu.xmu.vantel.room.service.TemperatureService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/room", produces = "application/json;charset=UTF-8")
public class RoomController {
    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private HumidityService humidityService;

    @Autowired
    private CombustibleGasService combustibleGasService;

    @Autowired
    private RoomService roomService;

    /**
     *
     * 上传房间温度
     * @param temperature
     * @return
     */
    @PostMapping("/temperature")
    public ReturnObject<Object> uploadTemperature(@RequestBody @Validated Temperature temperature) {
        boolean isSuccess = temperatureService.save(temperature);
        if (isSuccess) {
            return new ReturnObject<>();
        }
        return new ReturnObject<>(ReturnNo.INTERNAL_SERVER_ERR);
    }

    /**
     *
     * 上传房间湿度
     * @param humidity
     * @return
     */
    @PostMapping("/humidity")
    public ReturnObject<Object> uploadHumidity(@RequestBody @Validated Humidity humidity) {
        boolean isSuccess = humidityService.save(humidity);
        if (isSuccess) {
            return new ReturnObject<>();
        }
        return new ReturnObject<>(ReturnNo.INTERNAL_SERVER_ERR);
    }

    /**
     *
     * 上传房间可燃气体浓度
     * @param combustibleGas
     * @return
     */
    @PostMapping("/combustibleGas")
    public ReturnObject<Object> uploadHumidity(@RequestBody @Validated CombustibleGas combustibleGas) {
        boolean isSuccess = combustibleGasService.save(combustibleGas);
        if (isSuccess) {
            return new ReturnObject<>();
        }
        return new ReturnObject<>(ReturnNo.INTERNAL_SERVER_ERR);
    }

    // admin api

    /**
     * create a room
     * @param room
     * @return
     */
    @PostMapping("/admin/room")
    public ReturnObject<Object> createRoom(@RequestBody @Validated Room room) {
        boolean isSuccess = roomService.save(room);
        if (isSuccess) {
            return new ReturnObject<>();
        }
        return new ReturnObject<>(ReturnNo.INTERNAL_SERVER_ERR);
    }


    /**
     * 分页查询房间信息
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/admin/room/page")
    public ReturnObject<Page<Room>> listRoomPage(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        return roomService.listRoomPage(page, pageSize);
    }


    /**
     * 管理员获取房间温度信息
     * @param roomId
     * @return
     */
    @GetMapping("/admin/room/{roomId}/temperature")
    public ReturnObject<List<Temperature>> getRoomTemperature(@PathVariable("roomId") Long roomId,
                                                   @RequestParam(required = false)
                                                   @DateTimeFormat(pattern = DateTimeConstants.INPUT_DATE_TIME_FORMAT)
                                                   LocalDateTime beginDate,
                                                   @RequestParam(required = false)
                                                   @DateTimeFormat(pattern = DateTimeConstants.INPUT_DATE_TIME_FORMAT)
                                                   LocalDateTime endDate) {
        return temperatureService.getRoomTemperature(roomId, beginDate, endDate);
    }

    /**
     * 管理员获取房间过去totalHour个小时的温度统计信息
     * @param roomId
     * @param totalHour
     * @return
     */
    @GetMapping("/admin/room/{roomId}/temperature/hour")
    public ReturnObject<Map<String, Object>> getRoomTemperatureInHour(@PathVariable("roomId") Long roomId, @RequestParam(defaultValue = "24") Integer totalHour) {
        return temperatureService.getRoomTemperatureInHour(roomId, totalHour);
    }


    /**
     * 管理员获取房间湿度信息
     * @param roomId
     * @return
     */
    @GetMapping("/admin/room/{roomId}/humidity")
    public ReturnObject<List<Humidity>> getRoomHumidity(@PathVariable("roomId") Long roomId,
                                                        @RequestParam(required = false)
                                                   @DateTimeFormat(pattern = DateTimeConstants.INPUT_DATE_TIME_FORMAT)
                                                           LocalDateTime beginDate,
                                                        @RequestParam(required = false)
                                                   @DateTimeFormat(pattern = DateTimeConstants.INPUT_DATE_TIME_FORMAT)
                                                           LocalDateTime endDate) {
        return humidityService.getRoomHumidity(roomId, beginDate, endDate);
    }

    /**
     * 管理员获取房间过去totalHour个小时的湿度统计信息
     * @param roomId
     * @param totalHour
     * @return
     */
    @GetMapping("/admin/room/{roomId}/humidity/hour")
    public ReturnObject<Map<String, Object>> getRoomHumidityInHour(@PathVariable("roomId") Long roomId, @RequestParam(defaultValue = "24") Integer totalHour) {
        return humidityService.getRoomHumidityInHour(roomId, totalHour);
    }

    /**
     * 管理员获取房间可燃气体信息
     * @param roomId
     * @return
     */
    @GetMapping("/admin/room/{roomId}/combustibleGas")
    public ReturnObject<List<CombustibleGas>> getRoomCombustibleGas(@PathVariable("roomId") Long roomId,
                                                        @RequestParam(required = false)
                                                        @DateTimeFormat(pattern = DateTimeConstants.INPUT_DATE_TIME_FORMAT)
                                                                LocalDateTime beginDate,
                                                        @RequestParam(required = false)
                                                        @DateTimeFormat(pattern = DateTimeConstants.INPUT_DATE_TIME_FORMAT)
                                                                LocalDateTime endDate) {
        return combustibleGasService.getRoomCombustibleGas(roomId, beginDate, endDate);
    }

    /**
     * 管理员获取房间过去totalHour个小时的可燃气体浓度统计信息
     * @param roomId
     * @param totalHour
     * @return
     */
    @GetMapping("/admin/room/{roomId}/combustibleGas/hour")
    public ReturnObject<Map<String, Object>> getRoomCombustibleGasInHour(@PathVariable("roomId") Long roomId, @RequestParam(defaultValue = "24") Integer totalHour) {
        return combustibleGasService.getRoomCombustibleGasInHour(roomId, totalHour);
    }
}
