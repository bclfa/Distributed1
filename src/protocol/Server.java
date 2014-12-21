package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * @author guohz
 *
 */
public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1234); 
		
		while(true) {
			//读取响应数据
			Socket socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			Request request = ProtocolUtil.readRequest(input);
			
			OutputStream output = socket.getOutputStream();
			
			//组装响应
			Response response = new Response();
			response.setEncode((byte) 'u');
			if(request.getCommand().equals("HELLO")) {
				response.setResponse("hello!");
			}else {
				response.setResponse("bye bye!");
			}
			
			response.setResponseLength(response.getResponse().length());
			
			ProtocolUtil.writeResponse(output, response);
		}
	}
}
