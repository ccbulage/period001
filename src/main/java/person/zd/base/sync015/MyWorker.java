package person.zd.base.sync015;


public class MyWorker extends Worker {
	
	public static Object handle(Task task) {

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
}
