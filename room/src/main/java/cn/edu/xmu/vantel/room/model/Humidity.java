package cn.edu.xmu.vantel.room.model;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;

@TableName("vantel_humidity")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Humidity extends BaseEntity {
    @NotNull
    private Long roomId;
    @NotNull
    private Double value;
}
