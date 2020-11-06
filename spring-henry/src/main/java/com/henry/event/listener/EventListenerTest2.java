package com.henry.event.listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author henrychen
 * @date created at 2020/10/23 6:18 下午
 */
public class EventListenerTest2 {

	public static void main(String[] args) {
		ApplicationEventPublisher ac = new AnnotationConfigApplicationContext(Config.class, Listener.class);

		ac.publishEvent(new Event("hello"));
	}

}

@Configuration
@EnableAsync  // 异步开关
class Config {

}

class Event {

	String name;

	public Event(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + name + ")";
	}
}

class Listener {

	@EventListener
	@Order(2) // 优先级, 数字越小越先执行
	public void listen1(Event event) {
		System.out.println(getClass().getSimpleName() + " listen1 " + event);
	}

	@EventListener
	@Order(1)
	public void listen2(Event event) {
		System.out.println(getClass().getSimpleName() + " listen2 " + event);
	}

}
