package protocol;

/**
 * Э����Ӧ����
 * @author guohz
 *
 */
public class Response {

	/**
	 * Э����Ӧ����
	 */
	private byte encode;
	
	/**
	 * ��Ӧ
	 */
	private String response;
	
	/**
	 * ��Ӧ����
	 */
	private int responseLength;

	public byte getEncode() {
		return encode;
	}

	public void setEncode(byte encode) {
		this.encode = encode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getResponseLength() {
		return responseLength;
	}

	public void setResponseLength(int responseLength) {
		this.responseLength = responseLength;
	}
}
