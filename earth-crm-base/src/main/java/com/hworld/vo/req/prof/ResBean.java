package com.hworld.vo.req.prof;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ResBean<T> implements Serializable {
    private static final long serialVersionUID = 473565850962581446L;
    public static final int RES_SUCESS = 200;
    public static final int RES_FAIL = 400;
    private int code;
    private String errCode;
    private String errMsg;
    private Object obj;
    private Integer page;
    private Integer rows;
    private Integer totalRows;
    private Integer totalPage;
    private BigDecimal totalAmount;
    private List<T> list;
}
