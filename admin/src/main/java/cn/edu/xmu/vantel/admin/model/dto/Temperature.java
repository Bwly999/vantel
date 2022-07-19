package cn.edu.xmu.vantel.admin.model.dto;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;


@TableName("vantel_temperature")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Temperature extends BaseEntity {
    @NotNull
    private Long roomId;
    @NotNull
    private Double value;
}
