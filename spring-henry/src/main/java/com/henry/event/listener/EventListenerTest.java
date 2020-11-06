package com.henry.event.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author henrychen
 * @date created at 2020/10/23 5:59 下午
 */
public class EventListenerTest {

	static class MyEvent extends ApplicationEvent {

		public MyEvent(Object source) {
			super(source);
		}
	}

	static class EventListener implements ApplicationListener<MyEvent> {

		@Override
		public void onApplicationEvent(MyEvent event) {
			System.out.println(getClass().getSimpleName() + " onApplicationEvent " + event);
		}
	}

	public static void main(String[] args) {
		ApplicationEventPublisher ac = new AnnotationConfigApplicationContext(EventListener.class);

		ac.publishEvent(new MyEvent("hello"));
	}

}
