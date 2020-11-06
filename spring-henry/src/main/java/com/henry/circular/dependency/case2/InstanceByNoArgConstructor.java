package com.henry.circular.dependency.case2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

/**
 * 通过无参构造器实例化, 但是也实例化了有参构造方法的参数
 * @author henrychen
 * @date created at 2020/10/16 3:34 下午
 */
public class InstanceByNoArgConstructor {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.scan(InstanceByNoArgConstructor.class.getPackage().getName());
		ac.refresh();

		System.out.println(ac.getBean(StartPoint.class).next);
		System.out.println(ac.getBean(EndPoint.class).next);

		/**
		 * output:
		 *     init StartPoint with constructor ([])
		 * 	   init EndPoint with constructor ([class com.henry.circular.dependency.case2.StartPoint])
		 * 	   null
		 * 	   com.henry.circular.dependency.case2.StartPoint@3d012ddd
		 *
		 *
		 * 使用了无参构造方法的没有注入依赖
		 * 使用了有参构造方法的注入了依赖
		 */
	}
}


@Component
class StartPoint {

	public EndPoint next;

	public StartPoint() {
		System.out.println(String.format("init %s with constructor (%s)",
				getClass().getSimpleName(), Collections.emptyList()));
	}

	@Autowired(required = false)
	public StartPoint(EndPoint endPoint) {
		System.out.println(String.format("init %s with constructor (%s)",
				getClass().getSimpleName(), Arrays.asList(EndPoint.class)));
		this.next = endPoint;

	}

}

@Component
class EndPoint {

	public StartPoint next;

	public EndPoint() {
		System.out.println(String.format("init %s with constructor (%s)",
				getClass().getSimpleName(), Collections.emptyList()));
	}

	@Autowired(required = false)
	public EndPoint(StartPoint startPoint) {
		System.out.println(String.format("init %s with constructor (%s)",
				getClass().getSimpleName(), Arrays.asList(StartPoint.class)));

		this.next = startPoint;
	}
}
