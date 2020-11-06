package com.henry.circular.dependency.case1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * {@link org.springframework.beans.factory.annotation.Autowired}注解的情况
 * @author henrychen
 * @date created at 2020/10/8 11:54 下午
 */
public class DependencyWithAnnotationAutowired {

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

@Configuration
@ComponentScan("com.henry.circular.dependency.case1")
class CircularDependencyConfig {
}

@Component
class Yang {

	@Autowired
	private Yin yin;

}

@Component
class Yin {

	@Autowired
	private Yang yang;

}
