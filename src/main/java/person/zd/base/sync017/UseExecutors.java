package person.zd.base.sync017;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseExecutors {

	public static void main(String[] args) {
		//生成10个固定数量的线程
		ExecutorService executor1 = Executors.newFixedThreadPool(10);
		//无界缓冲队列线程池
		ExecutorService executor2 = Executors.newCachedThreadPool();
		//单一线程线程池
		ExecutorService executor3 = Executors.newSingleThreadExecutor();
		
	}
}
