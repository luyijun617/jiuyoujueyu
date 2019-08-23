package cn.luyijun.fitness.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>查询结果</p>
 *
 * @author lili
 * @version 1.0: Result.java, v0.1 2019-01-10 16:32 PM lili Exp $
 */
@Data
@Builder
public class Result implements Serializable {

    private String cardType;
    private String date;
    private String state;
}
