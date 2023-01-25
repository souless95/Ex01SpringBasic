package di;

//그래프상에서 원을 표현한 클래스
public class Circle {
	//중심좌표에 해당하는 Point객체를 멤버변수로 선언함
	Point point;
	//반지름 표현
	int radian;
	//생성자 메서드는 정의하지 않음.
	//getter/setter만 정의함.
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getRadian() {
		return radian;
	}
	public void setRadian(int radian) {
		this.radian = radian;
	}
	
	@Override
	public String toString() {
		return point + "반지름:" + radian;
	}
	

}
