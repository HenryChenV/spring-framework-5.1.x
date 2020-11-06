package com.henry.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author henrychen
 * @date created at 2020/10/14 10:57 下午
 */

class Hello1 {

}

class World1 {

}

class Config1 {

	@Bean
	public Hello1 hello1() {
		System.out.println("hello1");
		return new Hello1();
	}

	@Bean
	public World1 world1() {
		System.out.println("world1");
		hello1();
		return new World1();
	}

}

class Hello2 {

}

class World2 {

}

@Configuration
class Config2 {

	@Bean
	public Hello2 hello2() {
		System.out.println("hello2");
		return new Hello2();
	}

	@Bean
	public World2 world2() {
		System.out.println("world2");
		hello2();
		return new World2();
	}

}

public class ConfigurationApp {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(Config1.class);
		ac.register(Config2.class);
		ac.refresh();
	}
}
