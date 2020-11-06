package com.henry.profile;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/23 2:34 下午
 */
public class ProfileTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		ac.getEnvironment().setActiveProfiles("prod");

		ac.register(Config.class);

		ac.refresh();

		System.out.println(ac.getEnvironment());

		System.out.println(ac.getBean("dataSource"));
	}

}

@Configuration
class Config {

	@Bean("dataSource")
	@Profile("dev")
	public DataSource devDataSource() {
		return new DataSource("dev");
	}

	@Bean("dataSource")
	@Profile("prod")
	public DataSource prodDataSource() {
		return new DataSource("prod");
	}

}

@Component
class DataSource {

	private String name;

	public DataSource(String name) {
		System.out.println("init with " + name);
		this.name = name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + name + ")";
	}
}

