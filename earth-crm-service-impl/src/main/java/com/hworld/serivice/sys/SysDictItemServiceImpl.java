package com.hworld.serivice.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hworld.entity.sys.SysDictItemDO;
import com.hworld.mapper.sys.SysDictItemMapper;
import com.hworld.service.api.sys.SysDictItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统字典选项业务实现
 *
 * @author caoyang
 * @date 2022-02-08 16:00:52
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItemDO> implements SysDictItemService {

}
