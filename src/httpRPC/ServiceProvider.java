package httpRPC;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceProvider extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��������
		String serviceName = request.getParameter("service");
		
		//ʵ�������񲢷ŵ�Map��
		Map<String,BaseService> serviceMap = new HashMap<String,BaseService>();
		try {
			  
			BaseService s = (BaseService)Class.forName(serviceName).newInstance();
			serviceMap.put(serviceName,s);
			
		} catch (Exception e) {
		}
		
		String format = request.getParameter("format");
		
		Map parameters =  request.getParameterMap();
		
		BaseService service =  serviceMap.get(serviceName);
		Object result =  service.execute(parameters);
		
		//����JSON�����
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(result);
		jsonResult.setMessage("success");
		jsonResult.setResultCode(200);
		
		String json = JsonUtil.getJson(jsonResult);
		response.getWriter().write(json);
		
	}
	
}
