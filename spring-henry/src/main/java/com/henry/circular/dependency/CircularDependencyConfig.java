package com.henry.circular.dependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author henrychen
 * @date created at 2020/10/8 11:54 下午
 */
@Configuration
@ComponentScan("com.henry.circular.dependency")
public class CircularDependencyConfig {
}
