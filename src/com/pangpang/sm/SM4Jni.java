package com.pangpang.sm;

import java.io.*;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: call sm4 c++ code
 * @author: leewake
 * @create: 2019-11-21 16:00
 **/

public class SM4Jni {

    private static final int SM4_ENCRYPT = 0;
    private static final int SM4_DECRYPT = 1;

    private static final String pathPrefix = "/Users/lijingwei/sm/ubuntu/SM4New/";

    //native声明，用于生成c/c++代码
    public native byte[] sm4Encry(byte[] data, byte[] key, byte[] iv, int mode);

    public native byte[] sm4Decry(byte[] data, byte[] key, byte[] iv, int mode);

    //加载c/c++编译好的库
    static{
        System.loadLibrary("sm4Jni");
    }

    public static void main(String[] args) throws Exception {
        int mode = 0;
        String plainText = "test, I am encry some data using sm4 algorithm";
        System.out.println("输入的明文为:" + plainText);

        byte[] key = {(byte) 0x61, (byte) 0x62, (byte) 0x63, (byte) 0x64, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x10, (byte) 0x84, (byte) 0x29, (byte) 0x64, (byte) 0x50, (byte) 0xf8, (byte) 0x07, (byte) 0x00, (byte) 0x70};
        byte[] iv = {(byte)0x1f,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,
                (byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0x5e};

        System.out.println("-----ECB-----");
        String cipherText = sm4EncStr(plainText, key, iv, mode);
        System.out.println("加密后密文为:" + cipherText);
        String plainTextAfterEnc = sm4DecStr(cipherText, key, iv, mode);
        System.out.println("解密后明文为:" + plainTextAfterEnc);

        System.out.println("-----CBC-----");
        mode = 1;
        cipherText = sm4EncStr(plainText, key, iv, mode);
        System.out.println("加密后密文为:" + cipherText);
        plainTextAfterEnc = sm4DecStr(cipherText, key, iv, mode);
        System.out.println("解密后明文为:" + plainTextAfterEnc);

        System.out.println("-----CTR-----");
        mode = 2;
        cipherText = sm4EncStr(plainText, key, iv, mode);
        System.out.println("加密后密文为:" + cipherText);
        plainTextAfterEnc = sm4DecStr(cipherText, key, iv, mode);
        System.out.println("解密后明文为:" + plainTextAfterEnc);

        System.out.println("开始加密文件数据");
        encDataFromFile(pathPrefix + "news.txt", key, iv, mode);
        System.out.println("开始解密数据");
        decDataFromFile(pathPrefix + "cipher.txt", key, iv, mode);

    }

    public static void encDataFromFile(String filePath, byte[] key, byte[] iv, int mode) throws Exception {
        if (mode == 0) {
            System.out.println("using ecb mode enc");
        } else if (mode == 1) {
            System.out.println("using cbc mode enc");
        } if (mode == 2) {
            System.out.println("using ctr mode enc");
        }

        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        String plainText = "";

        File writerName = new File(pathPrefix + "/cipher.txt");
        writerName.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writerName));

        while ((plainText =bReader.readLine()) != null) {
            if (!"".equals(plainText)) {
                String cipherText = sm4EncStr(plainText, key, iv, mode);
                out.write(cipherText + "\n"); // \r\n即为换行
                out.flush();
            }
        }
        bReader.close();
        out.close();
    }

    public static void decDataFromFile(String filePath, byte[] key, byte[] iv, int mode) throws Exception {
        if (mode == 0) {
            System.out.println("using ecb mode dec");
        } else if (mode == 1) {
            System.out.println("using cbc mode dec");
        } if (mode == 2) {
            System.out.println("using ctr mode dec");
        }

        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        String cipher = "";

        File writerName = new File(pathPrefix + "/plainText.txt");
        writerName.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writerName));

        while ((cipher =bReader.readLine()) != null) {
            if (!"".equals(cipher)) {
                String plainText = sm4DecStr(cipher, key, iv, mode);
                out.write(plainText + "\n"); // \r\n即为换行
                out.flush();
            }
        }
        bReader.close();
        out.close();
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
        byte[] output = new SM4Jni().sm4Encry(plainText.getBytes(), key, iv, mode);
        System.out.println("加密后byte数组:");
        printByteToStr(output, output.length);
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
        byte[] plainText = new SM4Jni().sm4Decry(bytes, key, iv, mode);

        if (mode == 0) {
            System.out.println("using ecb mode");
        } else if (mode == 1) {
            System.out.println("using cbc mode");
        } if (mode == 2) {
            System.out.println("using ctr mode");
        }
        if (mode == 0 || mode == 1) {
            byte[] realPlain = offPadding(plainText);
            return realPlain;
        }

        return plainText;
    }

    //去padding
    private static byte[] offPadding(byte[] bytes) {
        int length = bytes.length;
        int ptr = length - 1;
        while (bytes[ptr] == (byte)0x00) {
            ptr--;
        }
        byte[] ret = new byte[ptr];
        if (bytes[ptr] == (byte)0x80) {
            System.arraycopy(bytes, 0, ret, 0, ptr);
        }
        return ret;
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
