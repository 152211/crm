package com.hworld.serivice.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.entity.sys.SysConfigDO;
import com.hworld.mapper.sys.SysConfigMapper;
import com.hworld.service.api.sys.SysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统配置业务实现
 *
 * @author caoyang
 * @date 2022-02-08 16:00:49
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfigDO> implements SysConfigService {

}
