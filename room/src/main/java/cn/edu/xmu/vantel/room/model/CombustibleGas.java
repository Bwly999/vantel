package cn.edu.xmu.vantel.room.model;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;

@TableName("vantel_combustible_gas")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombustibleGas extends BaseEntity {
    @NotNull
    private Long roomId;
    @NotNull
    private Double density;
}
