package com.henry.onepiece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author henrychen
 * @date created at 2020/10/8 12:14 上午
 */
@Component
public class OnePiece {

	@Autowired
	private Luffy luffy;

	@Autowired
	private Zoro zoro;

}
