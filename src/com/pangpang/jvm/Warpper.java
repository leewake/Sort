package com.pangpang.jvm;

import java.util.Map;
import java.util.Random;

/**
 * 测试程序假死,最终jvm程序栈都装不了异常信息
 * reference: http://bijian1013.iteye.com/blog/2271600
 * @author Administrator
 *
 */

public class Warpper {
	public static void main(String[] args) {
		Map<Object, Object> map = System.getProperties();
		Random random = new Random();
		while (true) {
			map.put(random.nextInt(), "value");
		}
	}

}
