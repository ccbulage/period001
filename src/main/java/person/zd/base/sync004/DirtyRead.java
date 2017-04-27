package person.zd.base.sync004;


/**
 * @ClassName: DirtyRead
 * @Description: 避免脏读（一致性读）：考虑问题的整体性——加锁的一致性
 * @author Gene
 * @date 2017年4月24日 下午12:16:22
 */
public class DirtyRead {

	private String name = "z3";
	private Integer age = 24;
	
	public synchronized void setValue(String name, Integer age){
		
		this.name = name;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.age = age;
		
		System.out.println("setValue方法得到：name = "+ this.name +",age = "+this.age);
	}
	
	/*synchronized*/
	public void getValue(){
		System.out.println("getValue方法得到：name = "+ this.name +",age = "+this.age);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		final DirtyRead dirtyRead = new DirtyRead();
		
		Thread thread1 = new Thread(new Runnable(){
			@Override
			public void run() {
			
				dirtyRead.setValue("z4", 25);
				
			}
		});
		
		thread1.start();
		Thread.sleep(1000);
		
		dirtyRead.getValue();
		
	}

}
