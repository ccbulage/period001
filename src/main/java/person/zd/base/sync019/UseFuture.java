package person.zd.base.sync019;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: UseFuture
 * @Description: 使用JDK封装的Future模式：
 * 					在主线程中开启另外的线程，这样主线程可以继续执行，新开启的线程可以异步执行，并最终和主线程汇合
 * @author Gene
 * @date 2017年5月4日 下午4:01:31
 */
public class UseFuture implements Callable<String> {

	private String para;
	
	public UseFuture(String para){
		this.para = para;
	}
	
	/**
	 * 这里是真实的业务逻辑，其执行可能很慢
	 */
	@Override
	public String call() throws Exception {
		//模拟执行耗时
		Thread.sleep(3000);
		String result = this.para + "处理完成";
		return result;
	}
	
	public static void main(String[] args) throws Exception{

		String queryStr = "query";
		//构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
		FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
		//创建一个固定线程的线程池且线程数为1,
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//这里提交任务future,则开启线程执行RealData的call()方法执行——相当于在线程池里面开启了另一个线程（异步线程）
		executor.submit(future); //注意submit与execute的区别：submit更强大（1、有返回值，2、参数不止一种）
		System.out.println("请求完毕");
		
		//主线程
		try {
			//这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			System.out.println("处理实际的业务逻辑...");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待（这里不是主线程阻塞了，而是get方法阻塞在等待结果）
		System.out.println("数据：" + future.get()); //这里get方法是异步的
		System.out.println("get方法在等待call处理完成...");
		
		
		executor.shutdown();
	}
}
