package com.henry.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author henrychen
 * @date created at 2020/10/7 12:29 上午
 */
public class Hello {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(HelloWorldAppConfig.class);

		System.out.println("Go Go Go");

		System.out.println(applicationContext.getBean(World.class));
		System.out.println(applicationContext.getBean(HelloWorldAppConfig.class));


		System.out.println(applicationContext.getBeanDefinition("helloWorldAppConfig"));
		System.out.println(applicationContext.getBeanDefinition("helloWorldAppConfig").getBeanClassName());

		System.out.println("Success ^-^");
	}

}
