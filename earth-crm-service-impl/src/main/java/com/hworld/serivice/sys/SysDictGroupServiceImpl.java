package com.hworld.serivice.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.entity.sys.SysDictGroupDO;
import com.hworld.mapper.sys.SysDictGroupMapper;
import com.hworld.service.api.sys.SysDictGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统字典分组业务实现
 *
 * @author caoyang
 * @date 2022-02-08 16:00:50
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictGroupServiceImpl extends ServiceImpl<SysDictGroupMapper, SysDictGroupDO> implements SysDictGroupService {

}
