package thread;

import java.util.Date;

public class TestThread{

	public static void main(String[] args) {
		/*Runner runner = new Runner();
		Thread thread = new Thread(runner);
		thread.start();
		
		Runner2 runner2 = new Runner2();
		runner2.setPriority(Thread.MAX_PRIORITY);
		runner2.start();
		try {
			Runner2.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		runner2.interrupt();
		
		Runner3 runner3 = new Runner3();
		runner3.start();
		
		Runner4 runner4 = new Runner4();
		runner4.setPriority(Thread.MAX_PRIORITY);
		runner4.start();*/
		
	/*	Resource resource = new Resource();
		Runner5 runner5 = new Runner5(resource);
		Thread thread2 = new Thread(runner5);
		Thread thread3 = new Thread(runner5);
		thread2.setName("线程5.1");
		thread3.setName("线程5.2");
		thread2.start();
		thread3.start();*/
		
		Resource2 resource2 = new Resource2();
		Thread thread4 = new Thread(resource2);
		thread4.start();
		
		resource2.toChange2();
		
		System.out.println(Resource2.num);

	/*	for (int i = 0; i < 1000; i++) {
			System.out.println("这是main方法的线程---" + i);
		}*/
	}

}

class Resource extends Thread{
	
	public static int num = 0;
	
	public synchronized void resource(String name) {
		num++;
		try {
			sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name + ", 你是第" + num + "个线程");
	}
}

class Resource2 implements Runnable{

	static int num = 0;
	
	@Override
	public void run() {
		toChange();
	}
	
	public synchronized void toChange() {
		num = 1000;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行tochange方法后num为" + num);
	}
	
	public synchronized void toChange2() {
		num = 2000;
		System.out.println("执行了tochange2方法，num为" + num);
	}
	
}

class Runner implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i <= 1000; i++){
			System.out.println("线程1---" + i);
			if (i%10==0) {
				
			}
		}
	}

}

class Runner2 extends Thread {
	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("--线程2---" + new Date());
			/*try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("线程2结束");
				return;
			}*/
		}
	}
}

class Runner3 extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("----线程3---" + i);
			if (i%10==0) {
				yield();
			}
		}
	}
}

class Runner4 extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("------线程4---" + i);
		}
	}
}

class Runner5 implements Runnable {
	
	Resource resource;
	
	public Runner5(Resource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		resource.resource(Thread.currentThread().getName());
	}
}