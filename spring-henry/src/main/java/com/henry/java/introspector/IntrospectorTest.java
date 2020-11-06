package com.henry.java.introspector;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author henrychen
 * @date created at 2020/10/18 12:51 上午
 */
public class IntrospectorTest {

	@Test
	public void testMyPropertyUtils() throws Exception {
		User user = User.newHenry();

		System.out.println(MyPropertyUtils.getProperty(user, "name"));

		MyPropertyUtils.setProperty(user, "name", "hanli");
		System.out.println(user.getName());
	}

	@Test
	public void testPropertyUtils() throws Exception {
		User user = User.newHenry();

		System.out.println(PropertyUtils.getProperty(user, "name"));

		PropertyUtils.setProperty(user, "name", "hanli");
		System.out.println(user.getName());
	}

	@Test
	public void testMyBeanUtils() throws Exception {
		User user = User.newHenry();

		System.out.println(MyBeanUtils.getProperty(user, "name"));

		MyBeanUtils.setProperty(user, "name", "hanli");
		System.out.println(user.getName());
	}

	@Test
	public void testBeanUtils() throws Exception {
		// User得是public的, 否则不会成功
		User user = User.newHenry();

		System.out.println(BeanUtils.getProperty(user, "id"));
		// output: java.lang.NoSuchMethodException: Property 'id' has no getter method in class 'class com.henry.java.introspector.User'

		BeanUtils.setProperty(user, "name", "hanli");
		// output: java.lang.reflect.InvocationTargetException: Cannot set name

		System.out.println(user.getName());

	}

}

class MyPropertyUtils {

	public static void setProperty(Object bean, String propertyName, Object propertyValue)
			throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, bean.getClass());
		Method writeMethod = pd.getWriteMethod();
		writeMethod.invoke(bean, propertyValue);
	}

	public static Object getProperty(Object bean, String propertyName)
			throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, bean.getClass());
		Method readMethod = pd.getReadMethod();
		return readMethod.invoke(bean);
	}

}

class MyBeanUtils {

	public static void setProperty(Object bean, String propertyName, Object propertyValue)
			throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		if (null != pds) {
			for (PropertyDescriptor pd : pds) {
				if (pd.getName().equals(propertyName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(bean, propertyValue);
					return;
				}
			}
		}
	}

	public static Object getProperty(Object bean, String propertyName)
			throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		if (null != pds) {
			for (PropertyDescriptor pd : pds) {
				if (pd.getName().equals(propertyName)) {
					Method readMethod = pd.getReadMethod();
					return readMethod.invoke(bean);
				}
			}
		}

		return null;
	}

}
