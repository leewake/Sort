package com.pangpang.multithread;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @description: //评测题目: 现在有大量文本文件（10000+），
 * 预计一共涉及单词数量1000W左右。要求并发计算，最终输出出现频率最高的100个单词及对应次数
 * @author: leewake
 * @create: 2018-11-13 20:06
 **/

public class GetTop100Word {

    private static final int BATCH_SIZE = 10;

    static class Count implements Runnable {

        private String[] words;

        private Map<String, Integer> wordMap;

        public Count(String[] words, Map<String, Integer> wordMap) {
            this.words = words;
            this.wordMap = wordMap;
        }

        @Override
        public void run() {
            for (int i = 0; i < words.length; i++) {
                if ((wordMap.get(words[i]) != null)) {
                    int value = ((Integer) wordMap.get(words[i])).intValue();
                    value++;
                    wordMap.put(words[i].toLowerCase(), new Integer(value)); // 将单词转换为小写存放以统一格式
                } else {
                    wordMap.put(words[i].toLowerCase(), new Integer(1));
                }
            }
        }
    }

    /**
     * <B>Description:</B> 读入文件 <br>
     * <B>Create on:</B> 2018/11/13 下午8:30 <br>
     *
     * @author leewake
     */
    public static String[] read() throws IOException {
        File file = new File("/tmp/word.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close(); // 关闭流

        return sb.toString().split(",");
    }

    public static void main(String[] args) throws IOException {
        Map<String, Integer> wordMap = new ConcurrentHashMap<>();
        String[] totalWord = read();

        int length = totalWord.length;

        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(BATCH_SIZE);

        for(int i = 0; i < BATCH_SIZE; i++){
            String[] words = new String[length / BATCH_SIZE];
            cachedThreadPool.execute(new Count(words, wordMap));
        }

        //对map进行一个降序
        Stream<Map.Entry<String, Integer>> st = wordMap.entrySet().stream();
        st.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEachOrdered(e -> wordMap.put(e.getKey(), e.getValue()));

        //输出前100个
        for (int i = 0; i < 100; i++) {
            System.out.println("第" + i + "名单词,出现次数为" + wordMap.get(i));
        }

    }


}

