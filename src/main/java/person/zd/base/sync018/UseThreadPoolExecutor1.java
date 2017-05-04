package person.zd.base.sync018;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UseThreadPoolExecutor1
 * @Description: 创建自定义的线程池（使用有界队列）
 * @author Gene
 * @date 2017年5月3日 下午8:58:33
 */
public class UseThreadPoolExecutor1 {

	/* 使用【有界队列】作为缓冲线程队列：
	 * （1）如果线程池实际线程数小于corePoolSize，则优先创建线程；
	 * （2）若大于corePoolSize，则会将任务加入队列；
	 * （3）若队列已满，则在总线程数小于maximumPoolSize的前提下，创建新的线程；
	 * （4）若线程数大于maximumPoolSize，则执行拒绝策略，或其他定义方式
	 * 
	 * */
	
	public static void main(String[] args) {
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1,    //corePoolSize
				2, 	  //maximumPoolSize
				60,   //keepAliveTime
				TimeUnit.SECONDS,  //unit
				new ArrayBlockingQueue<Runnable>(3) //workQueue :指定一种队列
				, new MyRejected()    //【自定义拒绝策略】
		);
		
		
		MyTask myTask1 = new MyTask(1, "任务1");
		MyTask myTask2 = new MyTask(2, "任务2");
		MyTask myTask3 = new MyTask(3, "任务3");
		MyTask myTask4 = new MyTask(4, "任务4");
		MyTask myTask5 = new MyTask(5, "任务5");
		MyTask myTask6 = new MyTask(6, "任务6");
		
		pool.execute(myTask1); //只执行一个线程
		pool.execute(myTask2);
		pool.execute(myTask3);
		pool.execute(myTask4); //刚好装满缓冲队列
		pool.execute(myTask5); //总线程数小于maximumPoolSize
		pool.execute(myTask6); //总线程数大于maximumPoolSize
		
		pool.shutdown();
		
		
	}
}
