package httpRPC;

import java.util.Map;

public class SayHelloService implements BaseService {

	public Object execute(Map<String, Object> args) {
		String[] helloArg =  (String[])args.get("arg1");
		
		if("hello".equals(helloArg[0])) {
			return "hello";
		}else {
			return "bye bye";
		}
	}

}
