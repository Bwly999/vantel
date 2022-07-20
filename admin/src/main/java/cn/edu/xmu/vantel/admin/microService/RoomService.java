package cn.edu.xmu.vantel.admin.microService;

import cn.edu.xmu.vantel.admin.model.dto.CombustibleGas;
import cn.edu.xmu.vantel.admin.model.dto.Humidity;
import cn.edu.xmu.vantel.admin.model.dto.Room;
import cn.edu.xmu.vantel.admin.model.dto.Temperature;
import cn.edu.xmu.vantel.core.util.ReturnObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@FeignClient(name = "room-service")
public interface RoomService {
    /**
     * admin create room
     * @param room
     * @return
     */
    @PostMapping("/room/admin/room")
    ReturnObject<Object> createRoom(@RequestBody Room room);


    /**
     * 分页查询房间信息
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/room/admin/room/page")
    ReturnObject<Page<Room>> listRoomPage(@RequestParam Integer page, @RequestParam Integer pageSize);

    /**
     * 管理员获取房间温度信息
     * @param roomId
     * @return
     */
    @GetMapping("/room/admin/room/{roomId}/temperature")
    ReturnObject<List<Temperature>> getRoomTemperature(@PathVariable("roomId") Long roomId, @RequestParam LocalDateTime beginDate, @RequestParam LocalDateTime endDate);

    /**
     * 管理员获取房间过去totalHour个小时的温度统计信息
     * @param roomId
     * @param totalHour
     * @return
     */
    @GetMapping("/admin/room/{roomId}/temperature/hour")
    ReturnObject<Map<String, Object>> getRoomTemperatureInHour(@PathVariable("roomId") Long roomId, @RequestParam(defaultValue = "24") Integer totalHour);

    /**
     * 管理员获取房间湿度信息
     * @param roomId
     * @return
     */
    @GetMapping("/room/admin/room/{roomId}/humidity")
    ReturnObject<List<Humidity>> getRoomHumidity(@PathVariable("roomId") Long roomId, @RequestParam LocalDateTime beginDate, @RequestParam LocalDateTime endDate);

    /**
     * 管理员获取房间可燃气体信息
     * @param roomId
     * @return
     */
    @GetMapping("/room/admin/room/{roomId}/combustibleGas")
    ReturnObject<List<CombustibleGas>> getRoomCombustibleGas(@PathVariable("roomId") Long roomId, @RequestParam LocalDateTime beginDate, @RequestParam LocalDateTime endDate);
}
