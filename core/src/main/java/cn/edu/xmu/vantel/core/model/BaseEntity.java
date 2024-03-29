package cn.edu.xmu.vantel.core.model;

import cn.edu.xmu.vantel.core.constant.DateTimeConstants;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 5315085444565595538L;

    @TableId(type = IdType.AUTO)
    protected Long id;

    @TableField(fill = FieldFill.INSERT)
    protected Long creatorId;

    @TableField(fill = FieldFill.INSERT)
    protected String creatorName;

    @TableField(fill = FieldFill.UPDATE)
    protected Long modifierId;

    @TableField(fill = FieldFill.UPDATE)
    protected String modifierName;

    @JsonFormat(pattern = DateTimeConstants.OUTPUT_DATE_TIME_FORMAT)
    protected LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = DateTimeConstants.OUTPUT_DATE_TIME_FORMAT)
    protected LocalDateTime gmtModified;
}
