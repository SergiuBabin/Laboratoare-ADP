package oneProducerOneConsumer;
/**
 * @author cristian.chilipirea
 *
 */
public class Buffer {
	int a;
	int count;
	synchronized void put(int value) {
		if (count == 1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notify();
		count++;
		a = value;
	}

	synchronized int get() {
		if (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notify();
		count--;
		return a;
	}
}
