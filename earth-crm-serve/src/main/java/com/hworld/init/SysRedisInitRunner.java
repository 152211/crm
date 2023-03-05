package com.hworld.init;

import com.hworld.service.api.sys.SysGlobalRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SysRedisInitRunner implements CommandLineRunner {

    @Autowired
    private SysGlobalRegionService sysGlobalRegionService;

    @Override
    public void run(String... args) throws Exception {
//        sysGlobalRegionService.setGlobalRegionRedisVal();
    }
}
