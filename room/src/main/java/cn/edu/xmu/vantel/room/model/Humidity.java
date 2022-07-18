package cn.edu.xmu.vantel.room.model;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("vantel_humidity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Humidity extends BaseEntity {
    private Long roomId;
    private Double value;
}
