package person.zd.base.sync015;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	
	//1 有一个盛装任务的集合
	private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
	
	//2 使用HashMap去盛装所有的worker对象
	private HashMap<String, Thread> workers = new HashMap<String, Thread>();
	
	//3 使用一个容器盛装每一个worker并行执行任务的结果集
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	//4 构造方法
	public Master(Worker worker, int workerCount) {
		//每一个worker都需要有master的引用：其中workerQueue用于任务的领取，reusltQueue用于任务的提交
		worker.setWorkerQueue(this.workQueue);
		worker.setResultQueue(this.resultMap);
		for (int i = 0; i < workerCount; i++) {
			//key表示每一个worker的名字，value表示线程执行的对象
			workers.put("子节点" + Integer.toString(i), new Thread(worker));
		}
	}
	
	//5 提交任务方法
	public void submit(Task task){
		this.workQueue.add(task);
	}
	
	//6 执行的方法（启动应用程序让所有的woker都工作起来）
	public void excute(){
		for(Map.Entry<String, Thread> me : workers.entrySet()){
			me.getValue().start();
		}
	}

	public boolean isComplete() {

		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			if(me.getValue().getState() != Thread.State.TERMINATED){
				return false;
			}
		}
		
		return true;
	}

	public int getResult() {
		int sum = 0;
		for(Map.Entry<String, Object> me : resultMap.entrySet()){
			//汇总
			sum += (Integer)me.getValue();
		}
		
		return sum;
	}
	
}	
