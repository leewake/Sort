package com.pangpang.property;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/** 
* @author  : lijingwei
*/
public class GetSystemProperty {
	
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		Set<String> originProperties = properties.stringPropertyNames();
		Set<String> expectedProperties = new TreeSet<>();
		for (String property : originProperties) {
			expectedProperties.add(property);
		}
		for (String property : expectedProperties) {
			System.out.println(property + " = " + properties.getProperty(property));
		}
		System.out.println("---system----");
		for (Entry<String, String> env : System.getenv().entrySet()) {
			System.out.println(env.getKey() + " = " + env.getValue());
		}
		System.err.println("CPU nnumber of the current computer is " + System.getenv("NUMBER_OF_PROCESSORS"));
	}

}
