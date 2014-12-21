package httpRPC;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	
	/**
	 * 把json转成对象
	 * @param json
	 * @param classObject
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object jsonToObject (String json,Class classObject) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
	    return mapper.readValue(json, classObject)	;
	}
	
	/**
	 * 把对象转成json
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static String getJson(Object obj) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
		mapper.writeValue(gen, obj);
		gen.close();
		
		return sw.toString();
	}
}
