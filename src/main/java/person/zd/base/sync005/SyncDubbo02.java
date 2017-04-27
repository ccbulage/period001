package person.zd.base.sync005;


/**
 * @ClassName: SyncDubbo02
 * @Description: 有父子继承关系，如果都加上synchronized ，则是线程安全的
 *				原因：父子之间始终是同一把锁（子类对象的锁） 	
 * @author Gene
 * @date 2017年4月24日 下午9:52:08
 */
public class SyncDubbo02 {

	static class Main{
		public int i = 10;
		
		public synchronized void operateSup(){
			try {
				i--;
				System.out.println("Main: i = " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static class Sub extends Main{
		
		public synchronized void operateSub(){
			
			try {
				while(i > 0){
					i--;
					System.out.println("Sub: i = " + i);
					Thread.sleep(100);
					this.operateSup();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
			
		final Sub sub = new Sub();
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				sub.operateSub();
				
			}
		}, "t1");
		
		thread1.start();
		
		
	}
	
}
