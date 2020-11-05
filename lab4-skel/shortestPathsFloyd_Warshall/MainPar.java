package shortestPathsFloyd_Warshall;
/**
 * @author cristian.chilipirea
 *
 */
public class MainPar {
    static int M = 9;
    static int graph[][] = { { 0, 1, M, M, M },
            { 1, 0, 1, M, M },
            { M, 1, 0, 1, 1 },
            { M, M, 1, 0, M },
            { M, M, 1, M, 0 } };
    public static void main(String[] args) {

        thread[] t = new thread[4];

        for (int i = 0; i < 4; ++i) {
            t[i] = new thread(i);
            t[i].start();
        }
        // Parallelize me (You might want to keep the original code in order to compare)
        for (int i = 0; i < 4; ++i) {
            try {
                t[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
