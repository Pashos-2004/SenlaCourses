package subtask_1;


/**
 * Эта программа:
 * 1. Генерирует случайное трёхзначное натуральное число в диапазоне от 100 до 999
 * 2. Вычисляет сумму цифр сгенерированного числа
 * 3. Выводит на экран исходное число и полученную сумму цифр
 * 
 */

public class RandomNumbers {
	
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
