package com.pangpang.sm;

/**
 * <B>Description:</B> sm3 hash algorithm java interface <br>
 * <B>Create on:</B> 2019/11/25 上午10:14 <br>
 *
 * @author leewake
 */

public class SM3Jni {

    private static final String pathPrefix = "/Users/lijingwei/sm/ubuntu/sm3-demo";

    //native声明，用于生成c/c++代码
    public native byte[] sm3(String data);

    public native byte[] sm3File(String filePath);

    //加载c/c++编译好的库
    static{
        System.loadLibrary("sm3jni");
    }

    public static void main(String[] args) {
        String message = "abcd";
        System.out.println("Message:");
        System.out.println(message);
        byte[] bytes = new SM3Jni().sm3(message);
        System.out.println("Hash:");
        printResult(bytes, bytes.length);

        message = "abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
        System.out.println("Message:");
        System.out.println(message);
        bytes = new SM3Jni().sm3(message);
        System.out.println("Hash:");
        printResult(bytes, bytes.length);

        String filePath  = "sm3.c";
        System.out.println("Message:");
        System.out.println("Hash a file:" + filePath);
        bytes = new SM3Jni().sm3File(pathPrefix + filePath);
        System.out.println("Hash:");
        printResult(bytes, bytes.length);
    }

    private static void printResult(byte[] bytes, int length) {
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1){
                hex = '0' + hex;
            }
            if (i != 0 && i % 4 == 0) {
                System.out.print(" ");
            }
            System.out.print(hex);
        }
        System.out.println();
    }

}
