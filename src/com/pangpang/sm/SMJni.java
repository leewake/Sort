package com.pangpang.sm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: sm algorithm java interface
 * @author: leewake
 * @create: 2019-11-21 16:00
 **/
public class SMJni {

    private static final int SM4_ENCRYPT = 0;
    private static final int SM4_DECRYPT = 1;

    private static final String pathPrefix = "/Users/lijingwei/sm/ubuntu/sm3-demo";

    public native static byte[] sm3(String data);

    public native static byte[] sm3File(String filePath);

    public native static byte[] sm4Encry(byte[] data, byte[] key, byte[] iv, int mode);

    public native static byte[] sm4Decry(byte[] data, byte[] key, byte[] iv, int mode);

    //加载c/c++编译好的库
    static{
        System.loadLibrary("smjni");
    }

    public static void main(String[] args) throws IOException {
        String message = "abcd";
        System.out.println("Message:");
        System.out.println(message);
        byte[] bytes = new SMJni().sm3(message);
        System.out.println("Hash:");
        printResult(bytes, bytes.length);

        message = "abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
        System.out.println("Message:");
        System.out.println(message);
        bytes = new SMJni().sm3(message);
        System.out.println("Hash:");
        printResult(bytes, bytes.length);

        String filePath  = "sm3.c";
        System.out.println("Message:");
        System.out.println("Hash a file:" + filePath);
        bytes = new SMJni().sm3File(pathPrefix + filePath);
        System.out.println("Hash:");
        printResult(bytes, bytes.length);


        System.out.println("<---开始测试sm4加密算法--->");

        int mode = 0;
        String plainText = "test, I am encry some data using sm4 algorithm";
        System.out.println("输入的明文为:" + plainText);

        if (mode == 0) {
            System.out.println("using ecb mode");
        } else if (mode == 1) {
            System.out.println("using cbc mode");
        } if (mode == 2) {
            System.out.println("using ctr mode");
        }

        byte[] key = {(byte) 0x61, (byte) 0x62, (byte) 0x63, (byte) 0x64, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x10, (byte) 0x84, (byte) 0x29, (byte) 0x64, (byte) 0x50, (byte) 0xf8, (byte) 0x07, (byte) 0x00, (byte) 0x70};
        byte[] iv = {(byte)0x1f,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,
                (byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0x5e};

        String cipherText = sm4EncStr(plainText, key, iv, mode);
        System.out.println("加密后密文为:" + cipherText);
        printByteToStr(cipherText.getBytes(), cipherText.getBytes().length);
        String plainTextAfterEnc = sm4DecStr(cipherText, key, iv, mode);
        System.out.println("解密后明文为:" + plainTextAfterEnc);
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

    /**
     * <B>Description:</B> sm4加密接口 <br>
     * <B>Create on:</B> 2019/11/23 下午8:51 <br>
     * key与iv都是长度为16的byte数组,其中ecb模式下,用不到iv,只需要传一个空byte数组
     * mode代表加密模式,0:ecb; 1:cbc; 2:ctr
     * @author leewake
     */
    public static String sm4EncStr(String plainText, byte[] key, byte[] iv, int mode) throws IOException {
        byte[] cipherous = sm4Enc(plainText, key, iv, mode);

        //encode,不然输出字符串是乱码
        String cipherStr = new String(Base64.getEncoder().encodeToString(cipherous));
        if (cipherStr != null && cipherStr.trim().length() > 0) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(cipherStr);
            cipherStr = m.replaceAll("");
        }
        return cipherStr;
    }

    public static byte[] sm4Enc(String plainText, byte[] key, byte[] iv, int mode) throws IOException {
        byte[] bytes = padding(plainText.getBytes(), SM4_ENCRYPT);
        int length = bytes.length;

        ByteArrayInputStream bins = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        for (; length > 0; length -= 16) {
            byte[] in = new byte[16];
            bins.read(in);
            byte[] ciphertext = new SMJni().sm4Encry(in, key, iv, mode);
            bous.write(ciphertext);
        }

        byte[] output = bous.toByteArray();
        bins.close();
        bous.close();

        return output;
    }

    /**
     * <B>Description:</B> sm4解密接口 <br>
     * <B>Create on:</B> 2019/11/23 下午9:06 <br>
     *
     * @author leewake
     */
    public static String sm4DecStr(String cipherText, byte[] key, byte[] iv, int mode) throws IOException {
        byte[] plainTextOus = sm4Dec(cipherText, key, iv, mode);
        String cipherStr = new String(plainTextOus);
        return cipherStr;
    }

    public static byte[] sm4Dec(String cipherText, byte[] key, byte[] iv, int mode) throws IOException {
        //使用Base64先decode
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        int length = bytes.length;

        ByteArrayInputStream bins = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        for (; length > 0; length -= 16) {
            byte[] in = new byte[16];
            bins.read(in);
            byte[] plainText = new SMJni().sm4Decry(in, key, iv, mode);
            bous.write(plainText);
        }

        byte[] output = bous.toByteArray();
        byte[] outputAfterPadding = padding(output, SM4_DECRYPT);
        bins.close();
        bous.close();

        return outputAfterPadding;
    }

    private static void printByteToStr(byte[] bytes, int length) {
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1){
                hex = '0' + hex;
            }
            System.out.print("0x" + hex + ",");
        }
        System.out.println();
    }

    /**
     * <B>Description:</B>加解密时不足128bit,填充 <br>
     *     mode : 1:加密, 0: 解密
     * <B>Create on:</B> 2019/11/23 下午8:58 <br>
     *
     * @author leewake
     */
    private static byte[] padding(byte[] input, int mode) {
        if (input == null) {
            return null;
        }

        byte[] ret = (byte[]) null;
        if (mode == SM4_ENCRYPT) {
            int p = 16 - input.length % 16;
            ret = new byte[input.length + p];
            System.arraycopy(input, 0, ret, 0, input.length);
            for (int i = 0; i < p; i++) {
                ret[input.length + i] = (byte) p;
            }
        } else {
            int p = input[input.length - 1];
            ret = new byte[input.length - p];
            System.arraycopy(input, 0, ret, 0, input.length - p);
        }
        return ret;
    }

}
