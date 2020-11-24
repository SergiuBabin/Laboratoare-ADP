package multipleProducersMultipleConsumersNBuffer;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @author Gabriel Gutu <gabriel.gutu at upb.ro>
 *
 */
public class Buffer {

    Queue queue;
    private static Semaphore semP;
    private static Semaphore semC;
    int count = 0;
    int size;

    public Buffer(int size) {
        queue = new LimitedQueue(size);
        semP = new Semaphore(size);
        semC = new Semaphore(0);
        this.size = size;
    }

	void put(int value) {
        try {
            semP.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            queue.add(value);
            semC.release();
        }
	}

	int get() {
        try {
            semC.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            semP.release();
            return (int)queue.poll();
        }

	}
}
