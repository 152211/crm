package com.hworld.bo.es;

import com.hworld.base.BaseDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class EsUpdateBO extends EsBaseBO {

    /**
     * es数据
     */
    private BaseDO baseDO;
}
