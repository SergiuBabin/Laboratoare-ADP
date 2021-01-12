package task1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class MyRunnable implements Runnable {
	ArrayList<Integer> partialPath;
	ExecutorService tpe;
	AtomicInteger inQueue;
	Integer destination;

	public MyRunnable(ArrayList<Integer> path, ExecutorService tpe, AtomicInteger inQueue, Integer destination) {
		this.partialPath = path;
		this.tpe = tpe;
		this.inQueue = inQueue;
		this.destination = destination;
	}

	@Override
	public void run() {
		if (partialPath.get(partialPath.size() - 1) == destination) {
			System.out.println(partialPath);
			return;
		}

			// se verifica nodurile pentru a evita ciclarea in graf
			int lastNodeInPath = partialPath.get(partialPath.size() - 1);
			for (int[] ints : Main.graph) {
				if (ints[0] == lastNodeInPath) {
					if (partialPath.contains(ints[1]))
						continue;
					ArrayList<Integer> newPartialPath = new ArrayList<>(partialPath);
					newPartialPath.add(ints[1]);
					//getPath(newPartialPath, destination);
					inQueue.incrementAndGet();
					tpe.submit(new MyRunnable(newPartialPath, tpe, inQueue, destination));
				}
			}


			int left = inQueue.decrementAndGet();
			if (left == 0) {
				tpe.shutdown();
			}
	}
}
