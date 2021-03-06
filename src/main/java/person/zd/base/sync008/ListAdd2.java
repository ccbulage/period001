package person.zd.base.sync008;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ListAdd2【Ali面试题】
 * @Description: 不用wait和notify实现线程间 通知
 * 				wait在获取锁执行完毕后 会立即释放锁
 * 				notify获取锁后 不会释放锁
 * 
 * 			下面这种通知方式的弊端：不能够实时反馈（不能让thread1一直抱着锁）
 * @author Gene
 * @date 2017年4月26日18:01:19
 */
public class ListAdd2 {

	private static volatile List<String> list2 = new ArrayList<String>();

	private void add() {

		list2.add("wait notify");
	}

	private int getSize() {

		return list2.size();
	}

	public static void main(String[] args) {

		final ListAdd2 listAdd2 = new ListAdd2();

		/**
		 * 实例化一个锁lock
		 * 当使用wait和notify的时候一定要配合着synchronized去使用
		 */
		final Object lock = new Object();

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (lock) {
					try {
						for (int i = 0; i < 10; i++) {
							listAdd2.add();
							Thread.sleep(500);
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素...");
							if (listAdd2.getSize() == 5) {
								lock.notify();
								System.out.println("当前线程：" + Thread.currentThread().getName() + "已发出通知！");
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "thread1");

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (lock) {
					if (listAdd2.getSize() != 5) {
						try {
							System.out.println("t2进入...");
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程接收到通知：" + Thread.currentThread().getName() + "在list size = 5时停止！");
					throw new RuntimeException();
				}
					
			}
		}, "thread2");

		thread2.start();
		thread1.start();
		
		/*
		 * thread1放在上面，thread根本就不会执行了，因为没有thread1不会去通知thread2
	 	thread1.start();
		thread2.start();*/
	}

}
