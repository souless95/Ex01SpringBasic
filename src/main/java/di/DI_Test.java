package di;

class Persons {
	String name;
	int age;
	
	//생성자가 public이면 외부에서 접근할 수 있다.
	public Persons() {
		System.out.println("public 생성자 호출");
	}
	
	//private으로 선언되면 외부에서 객체생성이 불가능한다.
	/*
	private Persons() { System.out.println("public 생서자 호출"); }
	 */
}

public class DI_Test {
	/*
	강한결합(독립성낮음) : new를 통해 직접 객체를 생성한다.
		이경우 객체간의 결합도가 높기때문에 persons클래스의
		변화에 직접적인 영향을 받는다.
	*/
	public static void aPerson() {
		//생성자가 private으로 선언되는 순간 에러가 발생한다.
		Persons persons1 = new Persons();
		persons1.age = 11;
		persons1.name = "성유겸";
	}
	/*
	약한결합(독립성높음) : 미리 생성된 객체를 주입(Injection) 받아 사용한다.
		결합도가 낮아지기 때문에 persons클래스에 변화가 생기더라도 직접적인 영향을 받지 않는다.
		또한 코드도 간결해진다.
	*/	
	//생성자의 접근지정자의 변화에 전혀 영향을 받지 않는다.
	public static void bPerson(Persons persons2) {
		persons2.name = "성유겸";
		persons2.age = 12;
	}
}
