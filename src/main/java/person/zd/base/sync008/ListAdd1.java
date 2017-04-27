package person.zd.base.sync008;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ListAdd1
 * @Description: 不用wait和notify实现线程间 通知
 * @author Gene
 * @date 2017年4月26日 下午5:56:01
 */
public class ListAdd1 {

	private static volatile List<String> list = new ArrayList<String>();
	
	private void add(){
		list.add("bsada");
	}
	
	private int getSize(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd1 listAdd1 = new ListAdd1();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					for (int i = 0; i < 10; i++) {
						listAdd1.add();
						Thread.sleep(1000);
						System.out.println("当前线程："+Thread.currentThread().getName()+"添加了一个元素...");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread1");
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				/**
				 * 这种轮训方式非常不好
				 */
				while (true) {
					if(listAdd1.getSize() == 5){
						System.out.println("当前线程接收到通知："+Thread.currentThread().getName()+"在list size = 5时停止！");
						throw new RuntimeException();
					}
					
				}
			}
		}, "thread2");
		
		thread1.start();
		thread2.start();
	}

}
