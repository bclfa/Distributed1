package httpRPC;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ServiceConsumer extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 参数
		String service = "httpRPC.SayHelloService";
		String format = "json";
		String arg1 = "hello";
		String url = "http://localhost:8080///Distributed1/provider.do?"
				+ "service=" + service + "&format=" + format + "&arg1=" + arg1;

		// 组装请求
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		// 接收响应
		HttpResponse resp = httpClient.execute(httpGet);

		HttpEntity entity = resp.getEntity();
		byte[] bytes = EntityUtils.toByteArray(entity);
		String jsonresult = new String(bytes, "utf8");

		JsonResult result = (JsonResult) JsonUtil.jsonToObject(jsonresult,
				JsonResult.class);
		response.getWriter().write(result.getResult().toString());
	}

}
