package person.zd.base.sync007;


/**
 * @ClassName: RunThread
 * @Description: 实现变量在多个线程之间可见
 * 				不具备原子性
 * @author Gene
 * @date 2017年4月26日 下午12:58:21
 */
public class RunThread extends Thread{
	/*volatile*/
	private boolean isRunning = true;
	
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	@Override
	public void run() {
		System.out.println("进入run...");
		while(isRunning){
			//...
		}
		System.out.println("线程停止...");
	}

	public static void main(String[] args) throws InterruptedException {
		RunThread runThread = new RunThread();
		
		runThread.start();
		Thread.sleep(1000);
		runThread.setRunning(false);
		System.out.println("isRunning已经被设置到false...");
		Thread.sleep(1000);
		System.out.println(runThread.isRunning);
		
		
	}

}
