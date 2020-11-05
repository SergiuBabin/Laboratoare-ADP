public class thread1 implements Runnable {
    private int id;

    public thread1(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Hello from thread " + this.id);
    }

}
