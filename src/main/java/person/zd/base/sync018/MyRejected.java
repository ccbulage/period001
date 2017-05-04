package person.zd.base.sync018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: MyRejected
 * @Description: 自定义拒绝策略
 * @author Gene
 * @date 2017年5月3日 下午10:06:34
 */
public class MyRejected implements RejectedExecutionHandler {

	
	public MyRejected() {}
	
	@Override					//当前任务对象   , 当前线程池对象
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		
		/*这里的自定义处理方式有两种（生产环境）：
		 * 	1、给源服务器发送http请求 （很耗系统资源不建议）
		 *  2、记录下log（任务id）,后面再进行定时job进行后续处理
		 *  
		 *  */
		System.out.println("自定义处理...");
		System.out.println("当前拒绝的任务id为：" + r.toString());
		
	}

}
