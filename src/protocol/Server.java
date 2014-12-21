package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �����
 * @author guohz
 *
 */
public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(1234); 
		
		while(true) {
			//��ȡ��Ӧ����
			Socket socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			Request request = ProtocolUtil.readRequest(input);
			
			OutputStream output = socket.getOutputStream();
			
			//��װ��Ӧ
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
