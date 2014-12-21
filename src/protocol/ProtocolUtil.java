package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 协议工具类
 * @author guohz
 *
 */
public class ProtocolUtil {

	public static Request readRequest(InputStream input) throws IOException {
		//读取编码
		byte[] encodeByte = new byte[1];
		input.read(encodeByte);
		byte encode = encodeByte[0];
		
		//读取命令长度
		byte[] commandLengthBytes = new byte[4];
		input.read(commandLengthBytes);
        int commandLength = ByteUtil.bytesToInt(commandLengthBytes);
				
		//读取命令
        byte[] commandBytes = new byte[commandLength];
        input.read(commandBytes);
        String command = "";
        if(encode == 'u') {
        	command = new String(commandBytes,"UTF8");
        }else if(encode == 'g'){
        	command = new String(commandBytes,"GBK");
        }
        
		//组装请求返回
		Request request = new Request();
		request.setEncode(encode);
		request.setCommand(command);
		request.setCommandLength(commandLength);
		
		return request;
	}
	
	public static void writeResponse(OutputStream output,Response response) throws IOException {
		// 将response响应返回给客户端
		output.write(response.getEncode());
		output.write(ByteUtil.intToByte(response.getResponseLength()));

		if (response.getEncode() == 'u') {
			output.write(response.getResponse().getBytes("UTF-8"));
		} else if (response.getEncode() == 'g') {
			output.write(response.getResponse().getBytes("GBK"));
		}
		output.flush();
			
	}
	
	public static void writeRequest(OutputStream output,Request request) throws IOException {
		// 请request请求提交到服务端
		output.write(request.getEncode());
		output.write(ByteUtil.intToByte(request.getCommandLength()));

		if (request.getEncode() == 'u') {
			output.write(request.getCommand().getBytes("UTF-8"));
		} else if (request.getEncode() == 'g') {
			output.write(request.getCommand().getBytes("GBK"));
		}
		output.flush();
			
	}
	
	public static Response readResponse(InputStream input) throws IOException {
		// 读取编码
		byte[] encodeByte = new byte[1];
		input.read(encodeByte);
		byte encode = encodeByte[0];

		// 读取响应长度
		byte[] responseLengthbytes = new byte[4];
		input.read(responseLengthbytes);
		int responseLength = ByteUtil.bytesToInt(responseLengthbytes);

		// 读取响应
		byte[] responseBytes = new byte[responseLength];
		input.read(responseBytes);
		String responseStr = "";
		if (encode == 'u') {
			responseStr = new String(responseBytes, "UTF-8");
		} else if (encode == 'g') {
			responseStr = new String(responseBytes, "GBK");
		}

		// 组装请求返回
		Response response = new Response();
		response.setEncode(encode);
		response.setResponseLength(ByteUtil.bytesToInt(responseBytes));
		response.setResponse(responseStr);
		
		return response ;
	}
	
}
