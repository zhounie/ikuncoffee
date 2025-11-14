package com.ikuncoffee.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("goods")
public class Goods {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long goodsCategoryId;

    private String name;

    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;

}
