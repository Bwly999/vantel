package cn.edu.xmu.vantel.room.model;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@TableName("vantel_temperature")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Temperature extends BaseEntity {
    @NotNull
    private Long roomId;
    @NotNull
    private Double value;
}
