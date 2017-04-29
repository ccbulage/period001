package person.zd.base.sync015;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Worker implements Runnable{

	private ConcurrentLinkedQueue<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;
	
	public void setWorkerQueue(ConcurrentLinkedQueue<Task> workQueue) {

		this.workQueue = workQueue;
	}

	public void setResultQueue(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
		
	}

	@Override
	public void run() {
		while(true){
			Task task = this.workQueue.poll(); //重queue中去取的同时移除
			if(task == null) break; //取不到任务的时候就结束
			//做真正的业务
			Object ret = MyWorker.handle(task);
			this.resultMap.put(Integer.toString(task.getId()), ret);
		}
	}

	//【方式1】直接写下父类中
	/** 
	 * @Title: handle
	 * @Description: worker核心方法
	 * @param task
	 * @return    
	 * @return: Object
	 */
	/*
	private Object handle(Task task) {

		Object ret = null;
		try {
			//表处理的task任务耗时，可能是数据加工，也可能是去操作数据库...
			Thread.sleep(500);
			ret = task.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	*/
	
	//【方式2】写在子类中，父类中使用空的实现
	//       这样实现解耦，更灵活
	private static Object handle(Task task){
		return null;
	}
	

}
