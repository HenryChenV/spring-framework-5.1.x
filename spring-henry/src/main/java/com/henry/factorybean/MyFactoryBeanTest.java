package com.henry.factorybean;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/21 11:33 上午
 */
public class MyFactoryBeanTest {

	@Test
	public void main() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
//		ctx.scan(getClass().getPackage().getName());
//		ctx.refresh();

		System.out.println("applicationContext 初始化完成");

		System.out.println("getBean(myFactoryBean): " + ctx.getBean("myFactoryBean"));
		System.out.println("getBean(&myFactoryBean): " + ctx.getBean("&myFactoryBean"));

		try {
			System.out.println("getBean(testBeanForFB): " + ctx.getBean("testBeanForFB"));
		} catch (BeansException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("getBean(TestBean.class): " + ctx.getBean(TestBeanForFB.class));
		} catch (BeansException e) {
			e.printStackTrace();
		}

//		ctx.getBean(TestBeanForFB.class);
	}

	@Test
	public void test2() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);

		System.out.println("applicationContext 初始化完成");

		System.out.println("getBean(myFactoryBean): " + ctx.getBean("myFactoryBean"));
		System.out.println("getBean(myFactoryBean): " + ctx.getBean("myFactoryBean"));
		System.out.println("getBean(myFactoryBean): " + ctx.getBean("myFactoryBean"));
		System.out.println("getBean(myFactoryBean): " + ctx.getBean("myFactoryBean"));
	}

	@Test
	public void test() {
		System.out.println(BeanFactoryUtils.transformedBeanName("&&&&bean"));
	}
}

@Configuration
@ComponentScan("com.henry.factorybean")
class Config {

	public Config() {
		System.out.println("创建" + this);
	}

	@Bean
	public TestBeanForConfig testBeanForConfig() {
		System.out.println("Config testBeanForConfig");
		return new TestBeanForConfig();
	}

}

class TestBeanForConfig {

	public TestBeanForConfig () {
		System.out.println("创建" + this);
	}

}

@Component
class MyFactoryBean implements FactoryBean {

	public MyFactoryBean() {
		System.out.println("创建" + this);
	}

	@Override
	public Object getObject() throws Exception {
		System.out.println("一段复杂的创建逻辑");
		return new TestBeanForFB();
	}

	@Override
	public Class<?> getObjectType() {
		return TestBeanForFB.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}

class TestBeanForFB {

	public TestBeanForFB() {
		System.out.println("创建TestBean");
	}
}

/**
 * 依赖factoryBean的结果
 */
@Component
class MyService {

	@Autowired
	private TestBeanForFB testBeanForFB;

}
