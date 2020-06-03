package cn.luyijun.fitness.api.service.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private Integer id;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private String isDelete;

}
