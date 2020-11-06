package com.henry.bean.factory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/23 10:29 下午
 */
public class AutowireCapableBeanFactoryTest {

	@Test
	public void test1() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan(getClass().getPackage().getName());
		ac.refresh();

		AutowireCapableBeanFactory beanFactory = ac.getBeanFactory();

		// 通过容器创建bean
		ExternalService externalService = beanFactory.createBean(ExternalService.class);

		// dependency被注入到了externalService中
		externalService.showDependency();

		// 但是ExternalService不在容器中
		try {
			ac.getBean(ExternalService.class);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}

class ExternalService {

	@Autowired
	private InternalService dependency;

	public void showDependency() {
		System.out.println(dependency);
	}

}

@Component
class InternalService {
}
