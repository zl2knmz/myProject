package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * 
 *
 * @author zl
 * @date 2023-12-06
 */
@Data
@TableName("`pass_vip_service`")
@ApiModel(value="PassVipService对象", description=" ")
public class PassVipService implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("`org_id`")
    private Long orgId;

    @TableId(value = "`service_id`", type = IdType.INPUT)
    private Long serviceId;

    @ApiModelProperty(value = "记录的条数")
    @TableField("`service_count`")
    private Integer serviceCount;

    @ApiModelProperty(value = "记录人")
    @TableField("`service_recorder`")
    private String serviceRecorder;

    @ApiModelProperty(value = "内容")
    @TableField("`service_content`")
    private String serviceContent;

    @ApiModelProperty(value = "时间")
    @TableField("`service_date`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime serviceDate;

    @TableField("`service_type`")
    private Integer serviceType;

    @ApiModelProperty(value = "账号类型")
    @TableField("`account_type`")
    private Integer accountType;

}
