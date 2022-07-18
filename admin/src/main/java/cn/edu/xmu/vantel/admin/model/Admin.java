package cn.edu.xmu.vantel.admin.model;

import cn.edu.xmu.vantel.core.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@TableName("vantel_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin extends BaseEntity {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private State state;
    @TableLogic(value = "0", delval = "1")
    private Boolean beDeleted;

    @AllArgsConstructor
    public enum State {
        /**
         * 正常
         */
        NORMAL(0, "正常"),
        /**
         * 封禁
         */
        BANNED(1, "封禁");

        @EnumValue
        int value;
        String desc;
    }
}
