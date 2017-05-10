package person.zd.base.sync019;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: UseSemaphore
 * @Description: 使用信号量在Java层面限流
 * @author Gene
 * @date 2017年5月4日 下午5:17:28
 */
public class UseSemaphore {

	public static void main(String[] args) {

        // 线程池  
        ExecutorService exec = Executors.newCachedThreadPool();  
        // 只能5个线程同时访问 ：一个线程执行完了后，另一个线程才能访问
        final Semaphore semp = new Semaphore(5); //这里应该根据以往经验设定一个阈(yu)值
        // 模拟20个客户端访问  
        for (int index = 0; index < 20; index++) {  
            final int NO = index;  
            Runnable run = new Runnable() {  
                public void run() {  
                    try {  
                        // 获取许可  
                        semp.acquire();  
                        System.out.println("Accessing: " + NO);  
                        //模拟实际业务逻辑
                        Thread.sleep((long) (Math.random() * 10000));  
                        // 访问完后，释放  
                        semp.release();  
                    } catch (InterruptedException e) {  
                    }  
                }  
            };  
            exec.execute(run);  
        } 
        
        try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        //System.out.println(semp.getQueueLength());
        
        // 退出线程池  
        exec.shutdown();  
	}
}
