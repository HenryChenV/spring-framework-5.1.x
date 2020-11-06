package com.henry.aop;

import com.henry.aop.logme.LogMe;
import com.henry.aop.logme.LogMeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/12 4:55 下午
 */
@Component
public class TargetObject extends LogMeConfig implements ImportAware {

	@Autowired
	private Dog dog;

	@LogMe
	public void sayHello() {
		System.out.println("say hello");
	}

	@LogMe
	public void callSayHello() {
		System.out.println("call say hello ++++++++++++++++++.");
		sayHello();
		System.out.println("call say hello ------------------.");
	}

	/**
	 * Set the annotation metadata of the importing @{@code Configuration} class.
	 *
	 * @param importMetadata
	 */
	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		System.out.println(importMetadata);
	}
}
