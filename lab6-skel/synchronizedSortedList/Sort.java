package synchronizedSortedList;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Sort extends Thread {
    private final List<Integer> list;
    private final Semaphore semaphore;

    public Sort(List<Integer> list, final Semaphore semaphore) {
        super();
        this.list = list;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
        } catch (final InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        Collections.sort(list);

        semaphore.release();
    }
}
