package com.henry.aop;

import com.henry.aop.logme.LogMeConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author henrychen
 * @date created at 2020/10/12 4:54 下午
 */
@Configuration
@ComponentScan("com.henry.aop")
@EnableAspectJAutoProxy
@Import(LogMeConfig.class)
public class AopConfig {
}
