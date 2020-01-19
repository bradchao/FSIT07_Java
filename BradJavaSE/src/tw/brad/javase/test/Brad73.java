package tw.brad.javase.test;

import java.util.Timer;
import java.util.TimerTask;

public class Brad73 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		MyTask1 mt1 = new MyTask1();
		EndTask end = new EndTask(timer);
		timer.schedule(end, 12*1000);
		timer.schedule(mt1, 0, 500);
		System.out.println("OK");
		
	}

}

class MyTask1 extends TimerTask {
	int i;
	@Override
	public void run() {
		System.out.println(i++);
	}
}

class EndTask extends TimerTask {
	Timer timer;
	EndTask(Timer timer){this.timer = timer;}
	@Override
	public void run() {
		timer.cancel();
		timer.purge();
		timer = null;
	}
}
