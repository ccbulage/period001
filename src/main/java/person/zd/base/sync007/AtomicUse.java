package person.zd.base.sync007;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: AtomicUse
 * @Description: 多个addAndGet，从整个方法来看是原子性的，但在一个方法内是非原子性的，需要加上synchronized修饰，保证4个addAndGet整体的原子性
 * @author Gene
 * @date 2017年4月26日 下午3:46:00
 */
public class AtomicUse {

	private static AtomicInteger count = new AtomicInteger(0);
	
	/*synchronized*/
	public synchronized int multiAdd(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count.addAndGet(1);
		count.addAndGet(2);
		count.addAndGet(3);
		count.addAndGet(4);
		
		return count.get();
	}
	
	public static void main(String[] args) {
		
		final AtomicUse atomicUse = new AtomicUse();
		
		List<Thread> threadList = new ArrayList<Thread>();
		for(int i = 0;i < 100;i++){
			threadList.add(new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(atomicUse.multiAdd());			
				}
			}));
		}
		
		for(Thread currThread:threadList){
			currThread.start();
		}
	}

}
