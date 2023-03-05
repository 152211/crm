package com.hworld.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
public class BaseDO implements Serializable {

    /**
     * 是否删除
     */
    @TableField(value = "deleted_flag", fill = FieldFill.INSERT)
    private Boolean deletedFlag;

    /**
     * 创建人
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private Long createdBy;

    /**
     * 创建人姓名
     */
    @TableField(value = "created_name", fill = FieldFill.INSERT)
    private String createdName;

    /**
     * 创建时间
     */
    @TableField(value = "created_on", fill = FieldFill.INSERT)
    private LocalDateTime createdOn;

    /**
     * 创建人
     */
    @TableField(value = "modified_by", fill = FieldFill.INSERT_UPDATE)
    private Long modifiedBy;

    /**
     * 创建人姓名
     */
    @TableField(value = "modified_name", fill = FieldFill.INSERT_UPDATE)
    private String modifiedName;

    /**
     * 修改时间
     */
    @TableField(value = "modified_on", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifiedOn;
}
