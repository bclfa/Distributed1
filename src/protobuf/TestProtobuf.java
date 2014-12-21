package protobuf;

import java.util.Arrays;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestProtobuf {
	public static void main(String[] args) throws InvalidProtocolBufferException {
		//序列化
		FirstExample.testBuf.Builder builder = FirstExample.testBuf.newBuilder();
		builder.setAge(22);
		builder.setName("张三");
		
		FirstExample.testBuf info = builder.build();
		byte[] bytes =  info.toByteArray();
		System.out.println(Arrays.toString(bytes));
		
		//反序列化
		FirstExample.testBuf test = FirstExample.testBuf.parseFrom(bytes);
		System.out.println(test.getName());
	}
}
