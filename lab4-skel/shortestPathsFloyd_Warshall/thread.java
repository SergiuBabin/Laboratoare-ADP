package shortestPathsFloyd_Warshall;
public class thread extends Thread{
    private int id;

    public thread(int id) {
        this.id = id;
    }

    public void run() {
        int start = (int) (this.id * (double) 5 / 4);
        int end = (int) Math.min((this.id + 1) * (double) 5 / 4, 5);

        for (int k = start; k < end; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    MainPar.graph[i][j] = Math.min(MainPar.graph[i][k] + MainPar.graph[k][j], MainPar.graph[i][j]);
                }
            }
        }
    }

}