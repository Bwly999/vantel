package cn.edu.xmu.vantel.room.model;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@TableName("vantel_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room extends BaseEntity {
    @NotBlank
    private String number;
    private Byte state;
    @TableLogic(value = "0", delval = "1")
    private Boolean beDeleted;

}
