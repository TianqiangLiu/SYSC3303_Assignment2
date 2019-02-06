import java.util.Random;

public class Controller {
	public static void main(String[] args) {
		Thread chef1, chef2, chef3, agent;
		Table table;

		table = new Table();

		// Create the producer and consumer threads, passing each thread
		// a reference to the shared BoundedBuffer object.
		chef1 = new Thread(new Chef("bread", table), "Chef with bread");
		chef2 = new Thread(new Chef("peanut butter", table), "Chef with peanut butter");
		chef3 = new Thread(new Chef("jam", table), "Chef with jam");
		agent = new Thread(new Agent(table), "Agent");
		agent.start();
		chef1.start();
		chef2.start();
		chef3.start();
	}
}

class Chef implements Runnable {
	private String material;
	private Table table;

	public Chef(String buf, Table t) {
		material = buf;
		table = t;
	}

	public void run() {
		while (true) {
			table.makeSandwich(material);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
	}
}

class Agent implements Runnable {

	private Table table;

	public Agent(Table t) {
		table = t;
	}

	public void run() {
		while (true) {
			Random random = new Random();
			int i = random.nextInt(3);
			String requiredMaterial;
			if (i == 0) {
					requiredMaterial = "bread";
			}
			else if (i == 1) {
				requiredMaterial = "peanut butter";
			}
			else {
				requiredMaterial = "jam";
			}
			table.putMaterials(requiredMaterial);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}
	}
}