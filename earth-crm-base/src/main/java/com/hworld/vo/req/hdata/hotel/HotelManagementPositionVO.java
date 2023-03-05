package com.hworld.vo.req.hdata.hotel;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 管店关系
 */
@Data
@Accessors(chain = true)
public class HotelManagementPositionVO {

    private String hotelNo;

    private String hotelCode;

    private String jobCode;

    private Integer isRealDz;

    private String empNo;

    private String empName;

    private String adAccount;

    private String encrypAdAccount;

    private Date beginTime;

    private Date endTime;

    private String managerTypeCode;

    private Boolean isDelete;

    private String createUser;

    private Date createTime;

    private String modifyUser;

    private Date modifyTime;

    private Date timestamp;

}