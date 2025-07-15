package com.entity;

import java.math.BigDecimal;
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
 * @date 2025-07-07
 */
@Data
@TableName("`pass_activity_coupon`")
@ApiModel(value="PassActivityCoupon对象", description="")
public class PassActivityCoupon implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "`id`", type = IdType.INPUT)
    private Long id;

    @TableField("`activity_id`")
    private Long activityId;

    @ApiModelProperty(value = "优惠券类型，0：用户定义，1：营销优惠")
    @TableField("`type`")
    private Integer type;

    @TableField("`coupon`")
    private String coupon;

    @TableField("`ticket_sn`")
    private String ticketSn;

    @TableField("`name`")
    private String name;

    @TableField("`discount`")
    private BigDecimal discount;

    @ApiModelProperty(value = "减免方式，0：折扣减免，1：定额减免")
    @TableField("`dis_type`")
    private Integer disType;

    @TableField("`usage_count`")
    private Integer usageCount;

    @TableField("`max_count`")
    private Integer maxCount;

    @TableField("`stop_selling`")
    private Boolean stopSelling;

    @TableField("`start_date`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startDate;

    @TableField("`end_date`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endDate;

    @ApiModelProperty(value = "早鸟优惠，0：未设置，1：启用早鸟优惠")
    @TableField("`early_buy`")
    private Integer earlyBuy;

    @ApiModelProperty(value = "团购优惠，0：未设置，>0：团购优惠数量阀值")
    @TableField("`multi_buy`")
    private Integer multiBuy;

    @ApiModelProperty(value = "会员专享优惠，0：未设置，1：已设置专享价")
    @TableField("`vip_buy`")
    private Integer vipBuy;

    @ApiModelProperty(value = "砍价优惠，0：未设置，>0：砍价数量阀值")
    @TableField("`bargain_buy`")
    private Integer bargainBuy;

    @ApiModelProperty(value = "主办方会员卡优惠，0：未设置，1：已设置专享价")
    @TableField("`orgvip_buy`")
    private Integer orgvipBuy;

    @ApiModelProperty(value = "团购优惠折扣")
    @TableField("`multi_discount`")
    private BigDecimal multiDiscount;

    @TableField(value = "`create_date`", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDate;

    @TableField(value = "`update_date`", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateDate;

    @TableField(value = "`update_by`", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(value = "`create_by`", fill = FieldFill.INSERT)
    private String createBy;

    @TableField("`disposable`")
    private Boolean disposable;

}
