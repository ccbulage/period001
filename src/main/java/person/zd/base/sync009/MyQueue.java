package person.zd.base.sync009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MyQueue
 * @Description: 利用wait和notify去实现阻塞队列
 * @author Gene
 * @date 2017年4月27日 上午11:06:12
 */
public class MyQueue {

	// (1)存放元素的容器
	private LinkedList<Object> list = new LinkedList<Object>();

	// (2)容器需要一个计数器（因为涉及多线程之间的访问）
	private AtomicInteger count = new AtomicInteger(0);

	// (3)容器的上下和下限
	private final int minSize = 0;
	private final int maxSize;

	// (4)构造方法
	public MyQueue(int maxSize) { // 在构造方法里面也可以初始化 常量
		this.maxSize = maxSize;

	}
	
	public int getSize(){
		return count.get();
	}

	// (5)初始化一个对象用于加锁
	private final Object lock = new Object();

	// put(anObject)：把Object加到BlockingQueue里，如果BlockingQueue没有空间，则被阻塞；知道BlockingQueue有空间为止
	public void put(Object obj) {
		synchronized (lock) {
			
			while(count.get() == this.maxSize){ //利用while循环进行阻塞
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//(1)加入一个元素
			list.add(obj);
			//(2)计数器累加
			count.incrementAndGet();
			System.out.println("阻塞队列中加入了："+obj.toString());
			//(3)通知另一个线程（唤醒）——“容器已空”的极端情况下
			lock.notify();
		}
	}

	// take：取走BlockingQueue里排在首位的对象，若BlockingQueue为空，阻塞进入，等待BlockingQueue有新的数据加入的时候在去取
	public Object take(){

		synchronized (lock) {
			
			while(count.get() == this.minSize){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//(1)移除元素
			Object ret = list.removeFirst();
			//(2)计数器递减
			count.decrementAndGet();
			System.out.println("阻塞队列中移除:"+ ret);
			//(3)通知另一个线程（唤醒）——“容器已满”的极端情况下
			lock.notify();
			return ret;
		}
	}

	public static void main(String[] args) {

		final MyQueue myQueue = new MyQueue(5);
		myQueue.put("a");
		myQueue.put("b");
		myQueue.put("c");
		myQueue.put("d");
		myQueue.put("e");

		System.out.println("当前容器大小："+myQueue.getSize());
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				myQueue.put("f");
				myQueue.put("g");
			}
		}, "thread1");
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				myQueue.take();
				myQueue.take();
			}
		},"thread2");
		
		thread1.start();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		thread2.start();
		
	}

}
