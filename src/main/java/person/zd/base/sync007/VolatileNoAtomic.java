package person.zd.base.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: VolatileNoAtomic
 * @Description: volatile无法保证原子性
 * @author Gene
 * @date 2017年4月26日 下午3:05:24
 */
public class VolatileNoAtomic extends Thread {

	private static volatile int count;
	// private static AtomicInteger count = new AtomicInteger(0);

	private static void addCount() {

		for (int i = 0; i < 1000; i++) {
			count++;
			// count.incrementAndGet();
		}
		System.out.println(count);// 打印也需要时间

	}

	@Override
	public void run() {
		addCount();
	}

	public static void main(String[] args) {

		VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = new VolatileNoAtomic();
		}
		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}

	}
}
