package task4;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MyTask extends RecursiveTask<Void> {
	ArrayList<Integer> partialPath;
	int destination;

	public MyTask(ArrayList<Integer> partialPath, int destination) {
		this.partialPath = partialPath;
		this.destination = destination;
	}

	@Override
	protected Void compute() {
		if (partialPath.get(partialPath.size() - 1) == destination) {
			System.out.println(partialPath);
		} else {
			int lastNodeInPath = partialPath.get(partialPath.size() - 1);
			List<MyTask> tasks = new ArrayList<>();
			for (int[] ints : Main.graph) {
				if (ints[0] == lastNodeInPath) {
					if (partialPath.contains(ints[1]))
						continue;
					ArrayList<Integer> newPartialPath = new ArrayList<>(partialPath);
					newPartialPath.add(ints[1]);
					MyTask t = new MyTask(newPartialPath, destination);
					tasks.add(t);
					t.fork();
				}
			}

			for (MyTask task : tasks) {
				task.join();
			}
		}
		return null;
	}
}
