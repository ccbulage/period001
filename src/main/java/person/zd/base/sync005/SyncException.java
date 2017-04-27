package person.zd.base.sync005;

/**
 * @ClassName: SyncException
 * @Description: 有异常的处理方法：（1）多个任务不相关，继续执行
 * 							      （2）多个任务相关（要么同时成功或失败），抛出异常，打断执行
 * @author Gene
 * @date 2017年4月25日 上午12:16:58
 */
public class SyncException {

	private int i = 0;

	public synchronized void operation() {

		while (true) {
			try {
				i++;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + ", i=" + i);
				if(i == 10){
					Integer.parseInt("a");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("log: i = "+ i);
				//throw new RuntimeException();
				//continue;
				
			}
		}
	}

	public static void main(String[] args) {
		
		final SyncException syncException = new SyncException();
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				syncException.operation();
				
			}
		}, "t1");

		thread1.start();
	}
}
