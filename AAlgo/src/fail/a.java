package fail;

public class a {

	public static void main(String[] args) {
		int a = -1120;//음수는 문제가 되는군..
		System.out.println((char)(a+'0'));
		System.out.println((char)(a+'0')-'0');
		
		System.out.println('a'-'A');
		System.out.println((char)('B'+32));
	}

}
