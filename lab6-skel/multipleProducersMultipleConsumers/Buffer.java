package multipleProducersMultipleConsumers;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Buffer {
	int capacity = 1;
	int timeout = 1;
	private final ArrayBlockingQueue<Integer> queue;

	public Buffer(){
		this.queue = new ArrayBlockingQueue<>(capacity);
	}

	void put(int value) {
		try {
			this.queue.offer(value, timeout, TimeUnit.SECONDS);
		} catch (final InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	int get() {
		try {
			return this.queue.poll(timeout, TimeUnit.SECONDS);
		} catch (final InterruptedException interruptedException) {
			interruptedException.printStackTrace();
			return 0;
		}
	}
}
