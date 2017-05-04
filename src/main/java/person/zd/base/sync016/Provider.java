package person.zd.base.sync016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable {

	//共享缓存区
	private BlockingQueue<Data> queue;

	//多线程间是否启动变量，有强制从主内存中刷新的功能，即时返回线程的状态（volatile:在重复获取时从主线程的内存中去取）
	private volatile boolean isRunning = true;
	
	//id生成器（原子性的，在高并发使用）
	private static AtomicInteger count = new AtomicInteger();
	
	//随机对象
	private static Random random = new Random();
	
	public Provider(BlockingQueue<Data> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(isRunning){
			try {
				//随机休眠0-1000 毫秒 表示获取数据（模拟产生数据的耗时）
				Thread.sleep(random.nextInt(1000));
				//获取数据进行计数
				int id = count.incrementAndGet();
				//比如通过一个getData方法获取了
				Data data = new Data(Integer.toString(id), "数据"+id);
				System.out.println("【生产线程】"+Thread.currentThread().getName()+"获取了id为"+id+"的数据，正在进行装载到公共缓存区中...");
				Thread.sleep(random.nextInt(1000));
 				
				if(!this.queue.offer(data, 2, TimeUnit.SECONDS)){ //两秒装载不进去就提示失败
					System.out.println("提交缓冲区失败...");
					//do something ... 比如重复提交
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void stop(){
		this.isRunning = false;
	}

}
