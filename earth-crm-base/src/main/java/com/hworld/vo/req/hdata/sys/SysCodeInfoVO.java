package com.hworld.vo.req.hdata.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysCodeInfoVO implements Serializable {

    private String type;

    private String typeName;

    private String code;

    private String name;

    private String parentCode;

    private Byte status;

    private String parentName;

    private Boolean isDelete;

    private List<SysCodeInfoLanguageVO> sysCodeInfoLanguageVOList;

    private List<SysCodePropValueVo> sysCodePropValueVoList;

}
