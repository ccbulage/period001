package person.zd.base.sync013;

import java.util.concurrent.DelayQueue;

/**
 * @ClassName: WangBa
 * @Description: 使用 “延迟Queue” 模拟 网吧上网场景
 * @author Gene
 * @date 2017年4月28日 下午5:17:01
 */
public class WangBa implements Runnable{

	DelayQueue<WangMin> queue = new DelayQueue<WangMin>();
	
	public boolean yinye = true;
	
	public void shangji(String name, String id, long endTime){
		WangMin wangMin = new WangMin(name, id, endTime*1000+System.currentTimeMillis());
		System.out.println("网名为："+name+"用户开始上机");
		this.queue.add(wangMin);
	}
	
	public void xiaji(WangMin wangMin){
		System.out.println("网名为："+wangMin.getName()+"下机");
		
	}
	
	@Override
	public void run() {
	
		while (yinye) {
			try {
				WangMin wangMin = queue.take();//等待到期，移除网民
				xiaji(wangMin);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {

		WangBa wangBa = new WangBa();
		System.out.println("网吧开始营业...");
		Thread thread1 = new Thread(wangBa);
		thread1.start();
		
		wangBa.shangji("张三", "12121", 1);
		wangBa.shangji("李四", "4211", 5);
		wangBa.shangji("王五", "2111", 2);

	}
	
}
	