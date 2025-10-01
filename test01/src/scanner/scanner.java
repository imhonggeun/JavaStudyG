package scanner;

import java.util.Scanner;

public class scanner {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("안녕하세요");
		System.out.println("입력하세요 :" + scanner.nextLine());
		scanner.close();
	}

}
