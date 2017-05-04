package person.zd.base.sync016;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName: Main
 * @Description: 生产者-消费者模式
 * @author Gene
 * @date 2017年5月3日 上午10:45:41
 */
public class Main {

	public static void main(String[] args) {
		
		//内存缓冲区
		BlockingQueue<Data> queue = new LinkedBlockingQueue<Data>(10);
		
		//生产者
		Provider p1 = new Provider(queue);
		Provider p2 = new Provider(queue);
		Provider p3 = new Provider(queue);
		
		//消费者
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		
		//创建线程池运行，这是一个缓存的线程池。可以创建无穷大的线程，没有任务时不创建线程（空闲线程的存活时间默认为60s)
		ExecutorService cachePool = Executors.newCachedThreadPool(); //Executors线程工厂类，创建一个带缓存的线程池
		cachePool.execute(p1);
		cachePool.execute(p2);
		cachePool.execute(p3);
		cachePool.execute(c1);
		cachePool.execute(c2);
		cachePool.execute(c3);
//		cachePool.submit(c3);  也可以
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//3s中之后，不在生产数据
		p1.stop();
		p2.stop();
		p3.stop();
		
	}

}
