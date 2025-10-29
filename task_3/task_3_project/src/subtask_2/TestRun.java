package subtask_2;

import java.math.BigDecimal;

public class TestRun {
	
	public static void main(String args[]) {
		
		
		System.out.println("Тестовый запуск цветочного магазина");
		System.out.println();
		
		Bouquet bouquet = new Bouquet("\"Очень красивая упаковка\"", new BigDecimal("1000.20"));
		Rose whiteRose = new Rose(new BigDecimal(120),"Белая", false);
		Rose redRose = new Rose(new BigDecimal(100),"Крассная", true);
		Lily blackLily = new Lily(new BigDecimal(500), "Чёрная", true);
		Lily greenLily = new Lily(new BigDecimal(800), "Зелёная", true);
		Tulip pinkTulip = new Tulip(new BigDecimal(50.5), "Розовый");
		
		bouquet.addFlower(pinkTulip, 10);
		bouquet.addFlower(whiteRose, 2);
		bouquet.addFlower(redRose, 1);
		bouquet.addFlower(blackLily, 3);
		bouquet.addFlower(redRose, 5);
		bouquet.addFlower(greenLily, 2);
		System.out.println();
		bouquet.calculatePriceOfBouquet();
	}
	
}
