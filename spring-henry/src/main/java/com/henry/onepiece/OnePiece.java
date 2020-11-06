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

	private Zoro zoro;

	public void setLuffy(Luffy luffy) {
		System.out.println("setLuffy " + luffy);
		this.luffy = luffy;
	}

	public void setZoro(Zoro zoro) {
		System.out.println("setZoro");
		this.zoro = zoro;
	}

	public Luffy getLuffy() {
		return luffy;
	}

	public Zoro getZoro() {
		return zoro;
	}
}
