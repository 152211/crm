package com.hworld.vo.req.hdata.sys;

import lombok.Data;

import java.util.List;

/**
 * @author Geewill
 * @version 1.0.0
 * @ClassName SysCodeVO.java
 * @PackageName com.hzgroup.xxlJobClient.common.vo
 * @Description TODO
 * @createTime 2021年08月30日
 */
@Data
public class SysCodeVO {
    private Long id;

    private String code;

    private String parentCode;

    private Byte status;

    private Boolean isDelete;

    private List<SysCodeInfoLanguageVO> sysCodeInfoLanguageVOList;

    private List<SysCodePropValueVo> sysCodePropValueVoList;
}
