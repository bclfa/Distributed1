package springRESTfull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import httpRPC.BaseService;
import httpRPC.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceProvider {
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		//设置日期转换
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//		sdf.setLenient(false);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
//	}
	
	
	@ResponseBody
	@RequestMapping(value = "/provider/{servicename}/{timestamp}", method = RequestMethod.POST)
//	public JsonResult provide(HttpServletRequest request,HttpServletResponse response,@RequestParam("servicename") String servicename,@RequestParam("timestamp") Date timestamp) {
	public JsonResult provide(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println(request.getRequestURI());
		String[] strs = request.getRequestURI().split("/");
		String servicename = strs[3];
		System.out.println(servicename);
		String dateTime = strs[4];
		System.out.println(dateTime);
		
		Map parameters =  request.getParameterMap();
		
		//实例化服务并放到Map里
		Map<String, BaseService> serviceMap = new HashMap<String, BaseService>();
		try {

			BaseService s = (BaseService) Class.forName(servicename)
					.newInstance();
			serviceMap.put(servicename, s);

		} catch (Exception e) {
		}
		
		BaseService service =  serviceMap.get(servicename);
		Object result =  service.execute(parameters);
		
		//生成JSON结果集
		JsonResult jsonResult = new JsonResult();
		jsonResult.setResult(result);
		jsonResult.setMessage("success");
		jsonResult.setResultCode(200);
		
		return jsonResult;
	}
}
