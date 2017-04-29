package person.zd.base.sync015;

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {

		//初始化master和10个worker
//		Master master = new Master(new MyWorker(), 10);
		//【优化】使用机器当前可用Processor数量，而不是指定一个定值
		Master master = new Master(new MyWorker(), Runtime.getRuntime().availableProcessors());
		System.out.println("当前可用线程数："+Runtime.getRuntime().availableProcessors());
		
		//创建100个任务
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			Task task = new Task();
			task.setId(i);
			task.setName("任务"+i);
			task.setPrice(r.nextInt(100));
			master.submit(task);
		}
		
		master.excute();
		
		long start = System.currentTimeMillis();
		//判断任务是否执行完成
		while(true){
			if(master.isComplete()){
				int result = master.getResult(); 
				long excuteTime =System.currentTimeMillis() - start;
				System.out.println("最终结果：" + result + "，运行时间：" + excuteTime);
				break;
			}
		}
	
	}
}
