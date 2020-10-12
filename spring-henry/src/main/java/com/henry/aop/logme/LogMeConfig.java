package com.henry.aop.logme;

import org.springframework.context.annotation.Import;

/**
 * @author henrychen
 * @date created at 2020/10/12 6:03 下午
 */
@Import(LogMeAspect.class)
public class LogMeConfig {
}
