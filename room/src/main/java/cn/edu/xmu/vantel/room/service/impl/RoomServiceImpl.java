package cn.edu.xmu.vantel.room.service.impl;

import cn.edu.xmu.vantel.core.util.ReturnObject;
import cn.edu.xmu.vantel.room.mapper.RoomMapper;
import cn.edu.xmu.vantel.room.model.Room;
import cn.edu.xmu.vantel.room.service.RoomService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomService")
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Override
    public ReturnObject<Page<Room>> listRoomPage(Integer page, Integer pageSize) {
        Page<Room> roomPageRequest = new Page<>(page, pageSize);
        Page<Room> roomPage = page(roomPageRequest);
        return new ReturnObject<>(roomPage);
    }
}
