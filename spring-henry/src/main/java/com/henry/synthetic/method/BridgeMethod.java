package com.henry.synthetic.method;

import org.junit.Test;

/**
 * 桥接方法
 *
 * @author henrychen
 * @date created at 2020/10/15 4:18 下午
 */
public class BridgeMethod {

	@Test
	public void test1() {
		MyNode mn = new MyNode();
		Node n = mn;

		n.setData("Hello");
		// output: java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer
	}

}

class Node<T> {

	public T data;

	public void setData(T data) {
		System.out.println("Node#setData(" + data + ")");
		this.data = data;
	}

}

class MyNode extends Node<Integer> {

	/**
	 * 实际上JVM会生成两个方法:
	 * 	setData(Ljava/lang/Integer;)V  Access flags: public
	 * 	setData(Ljava/lang/Object;)V  Access flags: public synthetic bridge
	 * 其中参数类型为Object的就是桥接方法, 为了满足多态, 实际的实现是{@link TypeErasureMyNode#setData(Object)}
	 * 搭建了从setData(Integer)到setData(Object)的桥梁
	 * @param data
	 */
	@Override
	public void setData(Integer data) {
		super.setData(data);
	}
}

class TypeErasureNode {

	public Object data;

	public TypeErasureNode(Object data) {
		this.data = data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

class TypeErasureMyNode extends TypeErasureNode {

	public Integer data;

	public TypeErasureMyNode(Object data) {
		super(data);
	}

	/**
	 * 桥接方法功能上等同于这个方法, 搭建了类型消除后
	 * @param data
	 */
	public void setData(Object data) {
		setData((Integer) data);
	}

	public void setData(Integer data) {
		this.data = data;
	}

}


