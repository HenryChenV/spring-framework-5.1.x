package com.henry.aop;

import com.henry.aop.logme.LogMe;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/12 4:55 下午
 */
@Component
public class TargetObject {

	@LogMe
	public void sayHello() {
		System.out.println("say hello");
	}

}
