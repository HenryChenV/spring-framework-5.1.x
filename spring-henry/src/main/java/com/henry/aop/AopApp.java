package com.henry.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author henrychen
 * @date created at 2020/10/12 4:54 下午
 */
public class AopApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AopConfig.class);


		ac.getBean(TargetObject.class).sayHello();
	}
}
