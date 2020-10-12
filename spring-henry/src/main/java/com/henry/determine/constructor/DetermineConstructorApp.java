package com.henry.determine.constructor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class MultiConstructor {

	public MultiConstructor() {
		System.out.println("init with constructor () in " + getClass().getSimpleName());
	}

	public MultiConstructor(Integer id) {
		System.out.println("init with constructor (Integer)" + getClass().getSimpleName());
	}

	public MultiConstructor(String name) {
		System.out.println("init with constructor (String)" + getClass().getSimpleName());
	}

	public MultiConstructor(Integer id, String name) {
		System.out.println("init with constructor (Integer,String)" + getClass().getSimpleName());
	}


}
@Component
class MultiConstructorAutowiredFalse {

	public MultiConstructorAutowiredFalse() {
		System.out.println("init with constructor () in " + getClass().getSimpleName());
	}

	@Autowired(required = false)
	public MultiConstructorAutowiredFalse(Integer id) {
		System.out.println("init with constructor (Integer) in " + getClass().getSimpleName());
	}

	@Autowired(required = false)
	public MultiConstructorAutowiredFalse(String name) {
		System.out.println("init with constructor (String) in " + getClass().getSimpleName());
	}

	@Autowired(required = false)
	public MultiConstructorAutowiredFalse(Integer id, String name) {
		System.out.println("init with constructor (Integer,String) in " + getClass().getSimpleName());
	}

}


@Component
class HackedAutoWiredModeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	/**
	 * Modify the application context's internal bean factory after its standard
	 * initialization. All bean definitions will have been loaded, but no beans
	 * will have been instantiated yet. This allows for overriding or adding
	 * properties even to eager-initializing beans.
	 *
	 * @param beanFactory the bean factory used by the application context
	 * @throws BeansException in case of errors
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("^-^ hacked beanFacotry in " + getClass().getSimpleName());

		GenericBeanDefinition mcafobd =
				(GenericBeanDefinition) beanFactory.getBeanDefinition("multiConstructorAutowiredFalse");
		mcafobd.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR);

		GenericBeanDefinition mcbf =
				(GenericBeanDefinition) beanFactory.getBeanDefinition("multiConstructor");
		mcbf.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR);
	}
}

/**
 * @author henrychen
 * @date created at 2020/10/11 9:47 下午
 */
public class DetermineConstructorApp {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.scan("com.henry.determine.constructor");

		applicationContext.refresh();
	}
}
