package person.zd.base.sync001;

/**
 * @ClassName: MyThread
 * @Description: 多个线程想要执行，同时去抢占执行run;
 * 				 多个线程去竞争这一把锁，应该避免出现这种情况
 * @author Gene
 * @date 2017年4月23日 上午10:23:24
 */
public class MyThread extends Thread{
	
	private int count = 5;
	
	@Override
	public synchronized void run() {
		count++;
		System.out.println(this.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		
		MyThread myThread = new MyThread();
		Thread thread1 = new Thread(myThread, "thread1");
		Thread thread2 = new Thread(myThread, "thread2");
		Thread thread3 = new Thread(myThread, "thread3");
		
		thread1.start();
		thread2.start();
		thread3.start();
		
		
	}
	
	


}
	