package com.henry.onepiece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/8 12:14 上午
 */
@Component
public class Luffy {

	public Luffy() {
		System.out.println("init with constructor Luffy()");
	}

	public Luffy(String name) {
		System.out.println("init with constructor Luffy(String)");
	}

	public Luffy(Integer id) {
		System.out.println("init with constructor Luffy(Integer)");
	}

	public Luffy(Integer id, String name) {
		System.out.println("init with constructor Luffy(Integer,String)");
	}

	@Autowired
	private Meet meet;


}
