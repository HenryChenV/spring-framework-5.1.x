package com.henry.constructor.autowire;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/13 7:28 下午
 */

@Component
class DependencyInAC {

	private AutowireConstructor autowireConstructor;

	public DependencyInAC(AutowireConstructor ac) {
		System.out.println("init with DependencyInAC(AutowireConstructor) ac=" + ac);
		autowireConstructor = ac;
	}

	public AutowireConstructor getAutowireConstructor() {
		return autowireConstructor;
	}
}

@Component
class AutowireConstructor {

	private DependencyInAC dependency;

	@Autowired(required = false)
	public AutowireConstructor() {
		System.out.println("init with AC()");
	}

	@Autowired(required = false)
	public AutowireConstructor(DependencyInAC dependency) {
		System.out.println("init with AC(Dependency) dependency=" + dependency);
		this.dependency = dependency;
	}

	public DependencyInAC getDependency() {
		return dependency;
	}
}

@Component
class AutowireConstructor2 {

	private DependencyInAC dependency;

	@Autowired(required = false)
	public AutowireConstructor2() {
		System.out.println("init with AC2()");
	}

	@Autowired(required = false)
	public AutowireConstructor2(DependencyInAC dependency) {
		System.out.println("init with AC2(Dependency)");
		this.dependency = dependency;
	}

//	@Autowired(required = false)
//	public AutowireConstructor2(Class clazz) {
//		System.out.println(clazz);
//	}

	public DependencyInAC getDependency() {
		return dependency;
	}
}

//@Component
class AutowireConstructorBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("xxxxxxxxxxxxxxx");
		GenericBeanDefinition bd = (GenericBeanDefinition) beanFactory.getBeanDefinition("autowireConstructor");

		// 如果提供了值, 会直接注入提供的值
		DependencyInAC dependencyInAC = new DependencyInAC(null);
		System.out.println("created in postProcessBeanFactory: " + dependencyInAC);
		bd.getConstructorArgumentValues().addGenericArgumentValue(dependencyInAC);

		// 如果没有这个构造器, 就会报错
		// 就算传了字符串, 也会传成功
        //	@Autowired(required = false)
        //	public AutowireConstructor2(Class clazz) {
        //		System.out.println(clazz);
        //	}
//		GenericBeanDefinition bd2 = (GenericBeanDefinition) beanFactory.getBeanDefinition("autowireConstructor2");
//		bd2.getConstructorArgumentValues().addGenericArgumentValue(DependencyInAC.class.getName());
	}
}

public class AutowireConstructorApp {

	@Test
	public void test() {
		System.out.println(getClass().getPackage().getName());

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan(getClass().getPackage().getName());
		ac.refresh();

		System.out.println("get from container");
		System.out.println("ac: " + ac.getBean(AutowireConstructor.class));
		System.out.println("ac.dependency: " + ac.getBean(AutowireConstructor.class).getDependency());
		System.out.println("ac2.dependency: " + ac.getBean(AutowireConstructor2.class).getDependency());

		System.out.println("dependency: " + ac.getBean(DependencyInAC.class));
		System.out.println("dependency.ac: " + ac.getBean(DependencyInAC.class).getAutowireConstructor());
	}

}
