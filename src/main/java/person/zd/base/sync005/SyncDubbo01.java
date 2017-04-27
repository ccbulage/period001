package person.zd.base.sync005;


/**
 * @ClassName: SyncDubbo01
 * @Description: 重入锁:是线程安全的
 * @author Gene
 * @date 2017年4月24日 下午7:07:00
 */
public class SyncDubbo01 {

	public synchronized void method1(){
		
		System.out.println("调用method1方法");
		this.method2();
	}
	public synchronized void method2(){
		
		System.out.println("调用method2方法");
		this.method3();
	}
	public synchronized void method3(){
		
		System.out.println("调用method3方法");
	}
	
	
	public static void main(String[] args) {
		
		final SyncDubbo01 dubbo01 = new SyncDubbo01();
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				dubbo01.method1();
			}
		}, "t1");
		
		thread1.start();
		
	}

}
