package com.hworld.vo.req.hdata.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysCodeInfoLanguageVO implements Serializable {

    private String languageCode;

    private String type;

    private String typeName;

    private String code;

    private String codeName;

    private Boolean isDelete;

}