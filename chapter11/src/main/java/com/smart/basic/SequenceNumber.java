package com.smart.basic;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class SequenceNumber {

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    private static class TestClient extends Thread {
        private SequenceNumber sn;

        public TestClient(SequenceNumber sn) {
            this.sn = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
            }
        }
    }

    public static void main(String[] args) {
        SequenceNumber sequenceNumber = new SequenceNumber();
        TestClient t1 = new TestClient(sequenceNumber);
        TestClient t2 = new TestClient(sequenceNumber);
        TestClient t3 = new TestClient(sequenceNumber);
        TestClient t4 = new TestClient(sequenceNumber);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
