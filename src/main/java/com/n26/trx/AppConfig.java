package com.n26.trx;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by carlos on 4/22/17.
 *
 * This class is only needed to enable spring scheduling support.
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AppConfig {
}
