package com.henry.onepiece;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/8 1:30 下午
 */
@Component
@Configuration
public class ThousandSunny {

	@Bean
	public OrangeTree orangeTree() {
		System.out.println("init orangeTree");
		return new OrangeTree();
	}

	@Bean
	public Coke coke() {
		System.out.println("init coke");
		// 如果ThousandSunny加了@Configuration，则会进代理对象，代理对象保证每个bean是单例，所以不会实际调用orangeTree
		// 这就是加了@Configuration的作用
		orangeTree();
		return new Coke();
	}

}
