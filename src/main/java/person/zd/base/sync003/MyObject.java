package person.zd.base.sync003;


/**
 * @ClassName: MyObject
 * @Description: 同时获取一个对象的锁
 * @author Gene
 * @date 2017年4月24日 上午10:56:43
 */
public class MyObject {
	
	public synchronized void method1(){
		
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*sychronized*/
	public synchronized void method2(){
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {

		final MyObject myObject = new MyObject();
		
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				myObject.method1();
			}
		}, "t1");
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				myObject.method2();
			}
		}, "t2");
		
		thread1.start();
		thread2.start();
	}
}
