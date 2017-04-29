package person.zd.base.sync014;


/**
 * @ClassName: FutureClient
 * @Description: 处理客户端的请求
 * @author Gene
 * @date 2017年4月29日 上午11:33:38
 */
public class FutureClient {

	public Data request(final String queryStr){
		
		//1、这里的FutureData为一个假的对象（代理对象），用这个假的对象发送给客户端，这样告诉他已经收到，可以做其他事情
		final FutureData futureData = new FutureData();
		//2、然后服务器 静默起一个线程，去加载真实的对象，传递给这个代理对象
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//3、启动一个新的线程去加载真实对象，然后传递给代理对象
				RealData realData = new RealData(queryStr);
				futureData.setRealData(realData);
			}
		}).start();
		
		
		return futureData; 
	}
}
