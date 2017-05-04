package person.zd.base.sync016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	private BlockingQueue<Data> queue;

	public Consumer(BlockingQueue<Data> queue) {
		this.queue = queue;
	}
	
	//随机对象
	private Random rand = new Random();

	@Override
	public void run() {
		while(true){
			try {
				//获取数据
				Data data = this.queue.take();
				//进行数据处理，休眠0-1000秒模拟耗时
				Thread.sleep(rand.nextInt(1000));
				System.out.println("【消费线程】" + Thread.currentThread().getName() + "，消费成功。消费的数据id为" + data.getId());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
