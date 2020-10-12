package com.henry.circular.dependency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author henrychen
 * @date created at 2020/10/8 11:54 下午
 */
public class CircularDependencyApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		ac.register(CircularDependencyConfig.class);


		// 是否允许循环依赖
//		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ac.getBeanFactory();
//		beanFactory.setAllowCircularReferences(false);

		ac.refresh();

		System.out.println(ac.getBean(Yin.class));
	}

}
