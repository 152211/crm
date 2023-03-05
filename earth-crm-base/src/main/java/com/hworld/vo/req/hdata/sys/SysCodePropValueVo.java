package com.hworld.vo.req.hdata.sys;

import lombok.Data;

import java.util.Date;

@Data
public class SysCodePropValueVo {


    private String languageCode;

    private String type;

    private String code;

    private String propCode;

    private String propValue;

    private Boolean isDelete;


}