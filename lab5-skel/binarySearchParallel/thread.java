package binarySearchParallel;

public class thread extends Thread{
    private int id;

    public thread(int id) {
        this.id = id;
    }

    public void run() {
        int mid;
        int low = (int) (this.id * (double) Main.N / Main.Number_OF_Thread);
        int high = (int) ((this.id + 1) * (double) Main.N / Main.Number_OF_Thread);

        while (low < high && !Main.found)  {
            mid = (high - low) / 2 + low;
            if (Main.v[mid] == Main.key)  {
                Main.found = true;
                break;
            }

            else if (Main.v[mid] > Main.key)
                high = mid - 1;
            else
                low = mid + 1;
        }
    }

}
