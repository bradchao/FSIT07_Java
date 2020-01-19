package tw.brad.javase.test;

public class Brad72 {
	public static void main(String[] args) {
		Thread1 t1 = new Thread1("A");
		Thread1 t2 = new Thread1("B");
		MyRunnable mr1 = new MyRunnable("C");
		Thread t3 = new Thread(mr1);
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("OK");
	}
}

class Thread1 extends Thread {
	private String name;
	Thread1(String name){
		this.name = name;
	}
	@Override
	public void run() {
		for (int i=0; i<20; i++) {
			System.out.println(name + ":" + i);
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				
			}
		}
	}
}

class MyRunnable implements Runnable {
	private String name;
	MyRunnable(String name){
		this.name = name;
	}
	@Override
	public void run() {
		for (int i=0; i<20; i++) {
			System.out.println(name + ":" + i);
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				
			}
		}
	}
}



