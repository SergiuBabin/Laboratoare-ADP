package binarySearchParallel;

public class Main {

    public static final int N = 13;
    public static int[] v = new int[N];;
    public static int Number_OF_Thread = 4;
    public static volatile boolean found = false;
    public static int key = 1;

    public static void main(String[] args) {
        thread[] t = new thread[Number_OF_Thread];

        for (int i = N - 1, j = 0 ; i >= 0; i--, j++)
            v[i] = j;

        for (int i = 0; i < Number_OF_Thread; ++i) {
            t[i] = new thread(i);
            t[i].start();
        }

        for (int i = 0; i < Number_OF_Thread; ++i) {
            try {
                t[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = N - 1, j = 0 ; i >= 0; i--, j++)
            System.out.println(v[i]+" \n");
        if (found) {
            System.out.println("Element " + key + " is found in array v[]\n");
        } else {
            System.out.println("Element " + key +" is not found in array v[]\n");
        }
    }

}
