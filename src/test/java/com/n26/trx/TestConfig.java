package com.n26.trx;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by carlos on 4/22/17.
 */
@ComponentScan(basePackages = {"com.n26.trx"},
        excludeFilters = @ComponentScan.Filter(value = {AppConfig.class}, type = FilterType.ASSIGNABLE_TYPE))
@EnableAsync
@EnableScheduling
public class TestConfig {
}
