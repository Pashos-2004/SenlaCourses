package subtask_1;



public class RandomNumbers {
	
	/*
	Написать программу:
	4. Выводящую на экран случайно сгенерированное трёхзначное натуральное число и сумму его цифр.
	*/
	
	public static void main(String args[]) {
		
		int number = generateThreeDigitNumber();
		System.out.println("Созданное число: " + number);
		System.out.println("Сумма его цифр: " + getSumOfDigitsOfNumber(number));
		
	}
	
	public static int getSumOfDigitsOfNumber(int number) {
		int sum = 0;
		while(number>0) {
			sum+=number%10;
			number/=10;
		}
		
		return sum;
	}
	
	public static int generateThreeDigitNumber() {
		return (100+(new java.util.Random()).nextInt(900));
	}
	
}
