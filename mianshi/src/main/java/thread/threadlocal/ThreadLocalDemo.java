package thread.threadlocal;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        int nThreads = 10;
        final Counter counter = new Counter();

        ExecutorService exec = Executors.newFixedThreadPool(nThreads);
        final CountDownLatch latch = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            exec.submit(()->{
                for (int ii = 0; ii < 10000; ii++) {
                    counter.increase();
                }
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("Expected:" + nThreads * 10000 + ",Actual:" + counter.count);
    }

    static class Counter {
        int count = 0;

        public synchronized void increase() {
            this.count++;
        }
    }
}