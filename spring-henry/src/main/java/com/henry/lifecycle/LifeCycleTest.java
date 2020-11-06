package com.henry.lifecycle;

import org.junit.Test;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.LifecycleProcessor;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 容器的生命周期
 *
 * @author henrychen
 * @date created at 2020/10/21 9:39 下午
 */
public class LifeCycleTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan(getClass().getPackage().getName());

		// 注册JVM shutdown hook, JVM shutdown时会调用
		// 也能触发stop和销毁回调
//		ac.registerShutdownHook();

		ac.refresh();

//		ac.start();

		// 停止容器,

		// 只会触发stop方法, 但是不能触发销毁回调
//		ac.stop();

		// stop方法和销毁回调都会触发
		ac.close();
	}

}


/**
 * 为了debug
 */
interface Ignore {}


/**
 * Bean的初始化回调
 * 1. @PostConstructor注解
 * 2. 实现InitializingBean接口
 *
 * Bean的销毁回调
 * 1. @PreDestroy
 * 2. 实现DisposableBean接口
 */
@Component
class LifeCycleDemo implements
		InitializingBean,
		DisposableBean,
		LifecycleProcessor,
		Lifecycle,
		Ignore
{

	public void init() {
		System.out.println(getClass().getSimpleName() + " @PostConstructor");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println(getClass().getSimpleName() + " @PreDestroy");
	}

	@Override
	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		System.out.println(getClass().getSimpleName() + " InitializingBean afterPropertiesSet");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println(getClass().getSimpleName() + " DisposableBean destroy");
	}

	volatile boolean isRunning;

	@Override
	public void start() {
		// 如果这里不置为true, destroy, @PreDestroy, stop都不会执行
		// 如果没实现LifeCycle接口, 没有isRunning方法, 并且这个方法没有没有返回true, 也会出现同样的情况
		isRunning = true;
		System.out.println(getClass().getSimpleName() + " LifeCycle start");
	}

	@Override
	public void stop() {
		isRunning = false;
		System.out.println(getClass().getSimpleName() + " LifeCycle stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void onRefresh() {
		System.out.println(getClass().getSimpleName() + " LifeCycle onRefresh");
	}

	@Override
	public void onClose() {
		System.out.println(getClass().getSimpleName() + " LifeCycle onClose");
	}
}


/**
 * 指定多个bean的回调方法的执行顺序-@Dependon
 */
@Component
//@DependsOn("b")
class A implements Lifecycle {

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		System.out.println(applicationContext.getBean("b"));
		System.out.println(getClass().getSimpleName() + " @PostConstructor");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println(getClass().getSimpleName() + " @PreDestroy");
	}

	volatile boolean isRunning;

	@Override
	public void start() {
		isRunning = true;
		System.out.println(getClass().getSimpleName() + " LifeCycle start");
	}

	@Override
	public void stop() {
		isRunning = false;
		System.out.println(getClass().getSimpleName() + " LifeCycle stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

}

@Component
class B implements Lifecycle {

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
//		System.out.println(applicationContext.getBean("a"));
		System.out.println(getClass().getSimpleName() + " @PostConstructor");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println(getClass().getSimpleName() + " @PreDestroy");
	}

	volatile boolean isRunning;

	@Override
	public void start() {
		isRunning = true;
		System.out.println(getClass().getSimpleName() + " LifeCycle start");
	}

	@Override
	public void stop() {
		isRunning = false;
		System.out.println(getClass().getSimpleName() + " LifeCycle stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

}


/**
 * 通过SmartLifecycle规定顺序
 */
@Component
class SmartLifecycleImpl0  implements SmartLifecycle {

	@PostConstruct
	public void init() {
		System.out.println(getClass().getSimpleName() + " @PostConstructor");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println(getClass().getSimpleName() + " @PreDestroy");
	}

	volatile boolean isRunning;

	@Override
	public void start() {
		isRunning = true;
		System.out.println(getClass().getSimpleName() + " LifeCycle start");
	}

	@Override
	public void stop() {
		isRunning = false;
		System.out.println(getClass().getSimpleName() + " LifeCycle stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public int getPhase() {
		return 0;
	}
}

@Component
class SmartLifecycleImpl1  implements SmartLifecycle {

	@PostConstruct
	public void init() {
		System.out.println(getClass().getSimpleName() + " @PostConstructor");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println(getClass().getSimpleName() + " @PreDestroy");
	}

	volatile boolean isRunning;

	@Override
	public void start() {
		isRunning = true;
		System.out.println(getClass().getSimpleName() + " LifeCycle start");
	}

	@Override
	public void stop() {
		isRunning = false;
		System.out.println(getClass().getSimpleName() + " LifeCycle stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public int getPhase() {
		return 1;
	}
}

@Component
class SmartLifecycleImpl2  implements SmartLifecycle {

	@PostConstruct
	public void init() {
		System.out.println(getClass().getSimpleName() + " @PostConstructor");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println(getClass().getSimpleName() + " @PreDestroy");
	}

	volatile boolean isRunning;

	@Override
	public void start() {
		isRunning = true;
		System.out.println(getClass().getSimpleName() + " LifeCycle start");
	}

	@Override
	public void stop() {
		isRunning = false;
		System.out.println(getClass().getSimpleName() + " LifeCycle stop");
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public int getPhase() {
		return 2;
	}
}
