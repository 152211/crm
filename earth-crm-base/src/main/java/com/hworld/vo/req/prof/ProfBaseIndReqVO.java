package com.hworld.vo.req.prof;

import com.earth.diana.bo.apol.BProfBaseInd;
import com.earth.diana.bo.apol.ext.ProfileSearchReq;
import com.hworld.annotation.Delelt;
import com.hworld.annotation.SelectOne;
import com.hworld.annotation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 客例查询VO
 *
 * @author caoyang
 * @date 2022-07-21 16:43:04
 */
@ApiModel("客例查询VO")
@Getter
@Setter
@ToString
public class ProfBaseIndReqVO extends BProfBaseInd implements Serializable {

    @ApiModelProperty("客例ID")
    @NotNull(message = "profId Can not be empty", groups = {Update.class, Delelt.class, SelectOne.class})
    private String profId;
}
