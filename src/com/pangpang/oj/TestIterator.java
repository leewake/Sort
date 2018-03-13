package com.pangpang.oj;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 
* @author  : lijingwei
* @version ：2018年3月12日 下午6:19:01 
*/
public class TestIterator {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();  
		list.add(0);  
		list.add(1);  
		list.add(2);  
		list.add(3);  
		list.add(4);  
		list.add(5);  
		list.add(6);  
		list.add(7);  

		int count = 1;
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer next = iterator.next();
			try {
				Field cursor = iterator.getClass().getDeclaredField("cursor");
				cursor.setAccessible(true);
				System.err.println("第" + count + "次循环, cursor<--->" + cursor.get(iterator));
				
				Field lastRet = iterator.getClass().getDeclaredField("lastRet");
				lastRet.setAccessible(true);
				System.err.println("第" + count + "次循环, lastRet<--->" + lastRet.get(iterator));
				count++;
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			}
			if (next == 3) {
				iterator.remove();
			}
		}
	}
}
