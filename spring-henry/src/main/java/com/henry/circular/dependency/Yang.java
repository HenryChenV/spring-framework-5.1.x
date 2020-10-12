package com.henry.circular.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/8 11:55 下午
 */
@Component
public class Yang {

	@Autowired
	private Yin yin;

}
