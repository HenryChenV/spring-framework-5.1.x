package com.henry.bean.definition;

import org.junit.Test;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class RegistryMe {

	private Object dependencyObj;
	private Class dependencyClass;

	public RegistryMe() {
		System.out.println("init with no-arg constructor in " + getClass().getSimpleName());
	}

	public RegistryMe(DependencyOfRegistryMe dependencyObj) {
		this.dependencyObj = dependencyObj;
		System.out.println("init with dependency object " + dependencyObj + " in " + getClass().getSimpleName());
	}

	public RegistryMe(Class dependencyClass) {
		this.dependencyClass = dependencyClass;
		System.out.println("init with dependency class " + dependencyClass + " in " + getClass().getSimpleName());
	}

	public RegistryMe(Integer id, String name) {
		System.out.println("init with constructor (Integer,String) in " + getClass().getSimpleName());
	}

	public Object getDependencyObj() {
		return dependencyObj;
	}

	public Class getDependencyClass() {
		return dependencyClass;
	}
}

class DependencyOfRegistryMe {
}

/**
 * 手动注册bd
 * @author henrychen
 * @date created at 2020/10/12 12:20 上午
 */
public class RegistryBeanDefinitionApp {

	@Test
	public void testNoArg() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		GenericBeanDefinition noArgBD = new GenericBeanDefinition();
		noArgBD.setBeanClass(RegistryMe.class);

		ac.registerBeanDefinition("noArgRegistryMe", noArgBD);

		ac.refresh();

		System.out.println(((RegistryMe) ac.getBean("noArgRegistryMe")).getDependencyObj());
	}

	@Test
	public void testOneObjectArg() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		GenericBeanDefinition oneObjArgBD = new GenericBeanDefinition();
		oneObjArgBD.setBeanClass(RegistryMe.class);
		oneObjArgBD.getConstructorArgumentValues().addGenericArgumentValue(new DependencyOfRegistryMe());
		ac.registerBeanDefinition("oneObjArgRegistryMe", oneObjArgBD);

		ac.refresh();

		System.out.println(((RegistryMe) ac.getBean("oneObjArgRegistryMe")).getDependencyObj());
	}

	@Test
	public void testOneClassArgWithString() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		GenericBeanDefinition oneClassArgBD = new GenericBeanDefinition();
		oneClassArgBD.setBeanClass(RegistryMe.class);

		// 这里传入类和类的全限定名都可以, 如果是全限定名, Spring会做转换
		oneClassArgBD.getConstructorArgumentValues().addGenericArgumentValue(DependencyOfRegistryMe.class.getName());

		ac.registerBeanDefinition("oneClassArgRegistryMe", oneClassArgBD);

		ac.refresh();

		System.out.println(((RegistryMe) ac.getBean("oneClassArgRegistryMe")).getDependencyClass());
	}

	@Test
	public void testOneClassArgWithClass() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		GenericBeanDefinition oneClassArgBD = new GenericBeanDefinition();
		oneClassArgBD.setBeanClass(RegistryMe.class);

		oneClassArgBD.getConstructorArgumentValues().addGenericArgumentValue(DependencyOfRegistryMe.class);

		ac.registerBeanDefinition("oneClassArgRegistryMe", oneClassArgBD);

		ac.refresh();

		System.out.println(((RegistryMe) ac.getBean("oneClassArgRegistryMe")).getDependencyClass());

	}
}
