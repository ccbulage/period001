package person.zd.base.sync017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledJob {
	
	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("run");
			}
		});
		
		//corePoolSize是初始化时的线程数
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		//参数说明：param1-创建的固定线程,param2-首次创建的延迟时间数,param3-两个线程之间的间隔时间数,param4-时间数单位
		ScheduledFuture<?> scheduleTask = service.scheduleWithFixedDelay(t1, 3, 1, TimeUnit.SECONDS);
	}
	
	
	
}
