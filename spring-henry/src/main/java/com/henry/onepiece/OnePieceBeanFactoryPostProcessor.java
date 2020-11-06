package com.henry.onepiece;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/8 12:16 上午
 */
@Component
public class OnePieceBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
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
		System.out.println(getClass().getSimpleName() + " postProcessBeanFactory");
		GenericBeanDefinition onePiece = (GenericBeanDefinition) beanFactory.getBeanDefinition("onePiece");
		onePiece.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

		Luffy luffy = new Luffy();
		System.out.println("luffy in postProcessBeanFactory: " + luffy);
		onePiece.getPropertyValues().add("luffy", luffy);
	}
}
