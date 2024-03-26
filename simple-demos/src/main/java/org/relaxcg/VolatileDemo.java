package org.relaxcg;

/**
 * @author relaxcg
 * @date 2024/3/26 15:53
 */
public class VolatileDemo {


    static class C1 {
        int a = 0;
        volatile boolean b = false;

        public void write() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            a = 1;
            b = true;
        }

        public void read() {
            if (b) {
                System.out.println(a);
            }
        }

        public static void main(String[] args) throws InterruptedException {
            // for (int i = 0; i < 1000; i++) {
                C1 c1 = new C1();
                var t1 = new Thread(c1::write);
                t1.start();
                var t2 = new Thread(c1::read);
                t2.start();

                t1.join();
                t2.join();
            }
        // }
    }
}
