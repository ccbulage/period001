package person.zd.base.sync002;


/**
 * @ClassName: MultiThread
 * @Description: 对象的锁（调用同步快的对象）：每个对象一个锁，无法保证同步
 * 				 类的锁（属于类，静态）：可以保证同步
 * @author Gene
 * @date 2017年4月24日 上午10:37:39
 */
public class MultiThread {

	private static int num = 5;
	
	/*static*/
	public synchronized static void printNum(String tag){
		
		try {
			if(tag.equals("a")){
				num = 100;
				System.out.println("tag a");
				Thread.sleep(1000);
			}else{
				num = 200;
				System.out.println("tag b");
			}
			
			System.out.println("tag "+tag+",num = "+num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		final MultiThread mt1 = new MultiThread();
		final MultiThread mt2 = new MultiThread();
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				mt1.printNum("a");
				
			}
		});
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				mt2.printNum("b");
			}
		});
		
		thread1.start();
		thread2.start();
		
		
	}

}
