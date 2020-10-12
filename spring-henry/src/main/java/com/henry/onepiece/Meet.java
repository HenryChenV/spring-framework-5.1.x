package com.henry.onepiece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/8 11:50 下午
 */
@Component
public class Meet {

	public Meet() {
		System.out.println("init meet");
	}

	@Autowired
	private Luffy luffy;

}
