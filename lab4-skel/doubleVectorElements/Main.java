package doubleVectorElements;
public class Main {

    public static final int N = 100000013;
    public static int[] v = new int[N];;
    public static int Number_OF_Thread = 4;


    public static void main(String[] args) {
        doubleVectorElements.thread[] t = new doubleVectorElements.thread[Number_OF_Thread];

        for (int i = 0; i < Number_OF_Thread; ++i) {
            t[i] = new doubleVectorElements.thread(i);
            t[i].start();
        }


        for (int i = 0; i < N; i++)
            v[i] = i;


        for (int i = 0; i < Number_OF_Thread; ++i) {
            try {
                t[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < N; i++) {

            if (v[i] != i * 2) {
                System.out.println("Wrong answer");
                System.exit(1);
            }
        }
        System.out.println("Correct");
    }

}