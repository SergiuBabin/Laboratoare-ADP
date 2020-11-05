package doubleVectorElements;
public class thread extends Thread{
    private int id;

    public thread(int id) {
        this.id = id;
    }

    public void run() {
        int start = (int) (this.id * (double) Main.N / Main.Number_OF_Thread);
        int end = (int) Math.min((this.id + 1) * (double) Main.N / Main.Number_OF_Thread, Main.N);

        System.out.println("start = "+start+" end = "+ end);
        for (int i = start; i < end; i++) {
            Main.v[i] = Main.v[i] * 2;

        }
    }

}
