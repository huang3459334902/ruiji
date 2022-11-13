package com.huang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@ServletComponentScan
@SpringBootApplication
public class SpringbootRuijiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRuijiApplication.class, args);
        log.info("项目启动成功...");
    }

}
