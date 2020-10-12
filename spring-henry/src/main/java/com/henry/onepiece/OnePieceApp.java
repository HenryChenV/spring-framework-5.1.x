package com.henry.onepiece;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author henrychen
 * @date created at 2020/10/8 12:20 上午
 */
public class OnePieceApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		ac.register(OnePieceAppConfig.class);

		ac.refresh();

		System.out.println(ac.getBean(OrangeTree.class));
	}

}
