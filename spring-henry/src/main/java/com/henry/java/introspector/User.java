package com.henry.java.introspector;

import java.util.Date;

/**
 * @author henrychen
 * @date created at 2020/10/18 1:42 上午
 */
public class User {

	/**
	 * 基本类型
	 */
	private long id;

	/**
	 * 字符串类型
	 */
	private String name;

	/**
	 * 包装类
	 */
	private Integer age;

	/**
	 * 有点复杂的类
	 */
	private Date birthday;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public static User newHenry() {
		User user = new User();
		user.setId(1L);
		user.setName("henry");
		user.setAge(99);
		user.setBirthday(new Date());

		return user;
	}

}
