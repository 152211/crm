package com.hworld.bo.es;

import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * EsBo
 *
 * @author Hbm-generator
 * @version 1.0
 * @date 2022-08-02 10:22:04
 **/
@Getter
@Setter
@ToString
public class EsBaseBO implements Serializable {
    /**
     * es ID
     */
    private String esId;

    /**
     * 索引
     */
    private String index;

    /**
     * 索引类型
     */
    private String type;
}
