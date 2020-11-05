public class Main {

    public static void main (String[] args) {
        int Thread_NUMBER = Runtime.getRuntime().availableProcessors();

        Thread[] t = new Thread[Thread_NUMBER];

        for (int i = 0; i < Thread_NUMBER; ++i) {
            t[i] = new Thread(new thread1(i));
            t[i].start();
        }
    for (int i = 0; i < Thread_NUMBER; ++i) {
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
