package person.zd.base.sync013;

import java.sql.Time;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WangMin implements Delayed {

	// 姓名
	private String name;
	// 身份证号
	private String id;
	// 下机时间
	private long endTime;
	// 定义时间工具类
	private TimeUnit timeUtil = TimeUnit.SECONDS;

	public WangMin(String name, String id, long endTime) {
		this.name = name;
		this.id = id;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Delayed delay) {

		WangMin wangMin = (WangMin)delay;
		return this.getEndTime() - wangMin.getEndTime() > 0 ? 1 : 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {

		return endTime - System.currentTimeMillis();
	}

	
	
	public String getName() {

		return name;
	}


	public String getId() {

		return id;
	}

	
	public long getEndTime() {
	
		return endTime;
	}

}
