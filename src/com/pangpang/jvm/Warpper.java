package com.pangpang.jvm;

import java.util.Map;
import java.util.Random;

public class Warpper {
	public static void main(String[] args) {
		Map<Object, Object> map = System.getProperties();
		Random random = new Random();
		while (true) {
			map.put(random.nextInt(), "value");
		}
	}

}
