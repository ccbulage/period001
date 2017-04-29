package person.zd.base.sync014;


/**
 * @ClassName: FutureData
 * @Description: 虚拟数据(代理对象类)
 * @author Gene
 * @date 2017年4月29日 上午11:17:57
 */
public class FutureData implements Data {

	private RealData realData;
	
	private boolean isReady = false;
	
	public synchronized void setRealData(RealData realData){
		//如果已经装载，就直接返回
		if(isReady){
			return ;
		}
		//如果没有装载，装载真实对象
		this.realData = realData;
		isReady = true;
		//进行通知
		notify();
		
	}
	
	@Override
	public synchronized String getRequest() {

		//如果没有装载好，程序一直处于阻塞状态
		while(!isReady){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//装载完了直接获取数据即可
		return this.realData.getRequest();
	}

}
