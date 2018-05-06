package authorize.jaeho.yoon.common.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	
	//컨트롤러의 메서드 실행 전에 처리(True일 경우 원래 가려던 경로로 보낸다)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if ( id == null) {
			
			//루트경로(절대경로)를 구하는 방법
			String path = request.getContextPath();
			
			response.sendRedirect(path+"/");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
	
	

}
