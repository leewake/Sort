package com.pangpang.filesize;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @description: 使用Future去统计
 * @author: leewake
 * @create: 2018-11-14 11:16
 **/

public class countByFuture {

    private static Long getTotalSize(ExecutorService service, File file) throws InterruptedException, ExecutionException, TimeoutException {
        if (file.isFile()) {
            return file.length();
        }

        long totalSize = 0l;
        File[] subFiles = file.listFiles();
        if (null != subFiles) {
            ArrayList<Future<Long>> partialTotalFutures = new ArrayList<>();
            for (File subFile : subFiles) {
                partialTotalFutures.add(service.submit(new Callable<Long>() {
                    @Override
                    public Long call() throws Exception {
                        return getTotalSize(service, subFile);
                    }
                }));
            }

            for (Future<Long> future : partialTotalFutures) {
                totalSize += future.get(1000, TimeUnit.SECONDS);
            }
        }
        return totalSize;
    }

    private static Long getTotalSize(File file) throws InterruptedException, ExecutionException, TimeoutException {
        Long totalSize;
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        try {
            totalSize = getTotalSize(executorService, file);
        } finally {
            executorService.shutdown();
        }
        return totalSize;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
//        File file = new File("/tmp/test/");
        File file = new File("/Users/lijingwei/github/algorithm/src");
        Long totalSize = 0l;
        try {
            totalSize = getTotalSize(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();

        System.out.println("Time cost: " + (end - start)/ 1.0e9);

        System.out.println("Total size is :" + totalSize / 1024 + "K");
    }
}
