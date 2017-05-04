package person.zd.base.sync018;

/**
 * @ClassName: MyTask
 * @Description: 线程任务
 * @author Gene
 * @date 2017年5月3日 下午9:12:22
 */
public class MyTask implements Runnable{

	private int taskId;
	private String taskName;

	public MyTask(int taskId, String taskName) {
		this.taskId = taskId;
		this.taskName = taskName;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			System.out.println("run taskId:"+taskId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public int getTaskId() {

		return taskId;
	}

	public void setTaskId(int taskId) {

		this.taskId = taskId;
	}

	public String getTaskName() {

		return taskName;
	}

	public void setTaskName(String taskName) {

		this.taskName = taskName;
	}
	
	@Override
	public String toString() {
	
		return Integer.toString(this.taskId);
	}
	

}
