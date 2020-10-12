package com.henry.synthetic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author henrychen
 * @date created at 2020/10/11 2:26 下午
 */
public class SyntheticClassAndMethod {

	class Inner {
		private int i = 77;

		private Inner() {

		}
	}

	private void print() {
		Inner inner = new Inner();
		System.out.println(inner.i);
	}

	public static void main(String[] args) {
		new SyntheticClassAndMethod().print();
		// output: 77

		// Q:
		// 	根据Java的原则, Inner的私有构造方法和私有方法是不能在外部被访问的, 但实验证明确实访问到了
		// 	SyntheticMethod可以访问到Inner的私有变量i, 也就是这个原则被打破了
		// 	怎么做到的

		// A:
		// 	看下Inner的构造方法和方法列表就知道了

		System.out.println("Inner.class.getDeclaredConstructors()");
		for (Constructor<?> constructor : Inner.class.getDeclaredConstructors()) {
			System.out.println(constructor);
		}
		System.out.println();
		// output:
		// 	Inner.class.getDeclaredConstructors()
		// 	private com.henry.synthetic.SyntheticClassAndMethod$Inner(com.henry.synthetic.SyntheticClassAndMethod)
		// 	com.henry.synthetic.SyntheticClassAndMethod$Inner(com.henry.synthetic.SyntheticClassAndMethod,com.henry.synthetic.SyntheticClassAndMethod$1)
		// 可以看到, 这里多了一个构造方法,
		// 再看下 ls out/production/classes/com/henry/synthetic
		// output:
		// 	SyntheticClassAndMethod$1.class     SyntheticClassAndMethod$Inner.class SyntheticClassAndMethod.class
		// 多了一个类, 这个类就是合成类

		System.out.println("Inner.class.getDeclaredMethods()");
		for (Method declaredMethod : Inner.class.getDeclaredMethods()) {
			System.out.println(declaredMethod);
		}
		System.out.println();
		// output:
		// 	Inner.class.getDeclaredMethods()
		// 	static int com.henry.synthetic.SyntheticClassAndMethod$Inner.access$100(com.henry.synthetic.SyntheticClassAndMethod$Inner)
		// 可以看到, 这里多了一个方法, 这个方法是一个合成方法,
		// 就是通过这个方法SyntheticMethod访问到了Inner中的私有变量
		// 且没有打破私有变量不能从外部访问的原则
	}

}
