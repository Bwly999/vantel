package cn.edu.xmu.vantel.room.service.imp;

import cn.edu.xmu.vantel.room.mapper.RoomMapper;
import cn.edu.xmu.vantel.room.model.Room;
import cn.edu.xmu.vantel.room.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("roomService")
public class RoomServiceImp extends ServiceImpl<RoomMapper, Room> implements RoomService {
}
