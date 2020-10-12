package com.henry.helloworld;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @author henrychen
 * @date created at 2020/10/11 3:59 下午
 */
public class Test {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(Arrays.asList(1, 2, 3).toArray(new Integer[0])));
	}
}
