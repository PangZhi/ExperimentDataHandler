
public class Engine {
	public static void main(String[] args) {
		Result2csv handler = new Result2csv("/home/echo/output");
		handler.generate();
		handler.output();
	}
}
