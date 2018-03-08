package com.pangpang.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
* @author  : lijingwei
* @version ：2018年2月28日 下午7:22:14 
*/
public class ReaderDemo {

	public static void main(String[] args) throws IOException {
		readNobuf();
//		readBybuf();
	}

	private static void readNobuf() throws FileNotFoundException, IOException {
		InputStreamReader isReader = new InputStreamReader(new FileInputStream("D:\\demo.txt"));
		char[] data = new char[50];
		int len = 0;
		while ((len = isReader.read(data, 0, 50)) != -1) {
			System.out.println(data);
		}
//		System.out.println(new String(data, 0, len));
		isReader.close();
	}
	
	private static void readBybuf() throws FileNotFoundException, IOException {
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\demo1.txt")));
		String line = null;
		while((line = bufReader.readLine()) != null){
			System.out.println(line);
		}
		bufReader.close();
	}

}
