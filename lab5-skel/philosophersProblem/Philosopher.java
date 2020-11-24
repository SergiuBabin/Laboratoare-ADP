package philosophersProblem;

import java.util.concurrent.Semaphore;

/**
 * @author cristian.chilipirea
 * 
 */
public class Philosopher implements Runnable {
	Object leftFork, rightFork;
	int id;
	static final Semaphore sem = new Semaphore(Main.N - 1);

	public Philosopher(int id, Object leftFork, Object rightFork) {
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.id = id;

	}

	private void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			sem.acquire();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
		synchronized (leftFork) {
			sleep(); // delay added to make sure the dead-lock is visible
			synchronized (rightFork) {
				System.out.println("Philosopher " + id + " is eating");
			}
			sem.release();
		}
	}
}
