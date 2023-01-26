package env;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/*
해당 VO클래스 작성시 EnvironmentAware, InitializingBean
인터페이스는 필수사항은 아니다. 단, 객체생성 전/후에 별도의 처리가
필요하다면 로직을 삽입할 수 있다. 프로그램 실행시 콘솔을 통해 자동으로
호출되는 것을 확인할 수 있다.
*/
public class ConnectionVO
	implements EnvironmentAware, InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet() 호출");
	}
	
	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("setEnvironment() 호출");
	}
	
	private String userId;
	private String userPw;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
}
