
public class Table {
	private boolean empty = true;
	private String requiredMaterial = "";

	public synchronized void makeSandwich(String s) {
		while (empty || !s.equals(requiredMaterial)) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		System.out.println(Thread.currentThread().getName() + " Creat a Sandwich and Eat it. ");
		empty = true;
		requiredMaterial = "";
		notifyAll();
		
	}
	public synchronized void putMaterials(String s) {
		while (!empty) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
		System.out.println(Thread.currentThread().getName() + " Put " + s + " on the table.");
		requiredMaterial = s;
		empty = false;
		notifyAll();
	}

}
