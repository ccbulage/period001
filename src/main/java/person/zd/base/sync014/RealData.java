package person.zd.base.sync014;


/**
 * @ClassName: RealData
 * @Description: 真实数据(真实对象类)
 * @author Gene
 * @date 2017年4月29日 上午11:17:46
 */
public class RealData implements Data{

	private String result;
	
	public RealData(String queryStr) {
		System.out.println("根据" + queryStr + "进行查询");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("查询完成!");
		result = "查询结果";
		
	}
	
	@Override
	public String getRequest() {
		return result;
	}
	
}
