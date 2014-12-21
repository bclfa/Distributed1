package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 * @author guohz
 *
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Request request = new Request();
		request.setEncode((byte) 'u');
		request.setCommand("HH");
		request.setCommandLength(request.getCommand().length());
		
		Socket client = new Socket("127.0.0.1",1234);
		OutputStream output = client.getOutputStream();
		
		//发送请求
		ProtocolUtil.writeRequest(output, request);
		
		//读取响应数据
		InputStream input = client.getInputStream();
		Response response =  ProtocolUtil.readResponse(input);
		System.out.println("响应的内容为："+response.getResponse());
		
	}
}
