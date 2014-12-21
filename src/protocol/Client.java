package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �ͻ���
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
		
		//��������
		ProtocolUtil.writeRequest(output, request);
		
		//��ȡ��Ӧ����
		InputStream input = client.getInputStream();
		Response response =  ProtocolUtil.readResponse(input);
		System.out.println("��Ӧ������Ϊ��"+response.getResponse());
		
	}
}
