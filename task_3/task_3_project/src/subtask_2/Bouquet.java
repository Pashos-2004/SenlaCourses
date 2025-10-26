package subtask_2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Bouquet {
	private Map<Flower, Integer> flowers = new HashMap<Flower, Integer>();
	private String packaging;
    private double packagingPrice;
	
	public Bouquet(String packaging, double packagingPrice) {
		this.packaging = packaging;
		this.packagingPrice = packagingPrice;
	}
	
	public String getPackaging() {
		return packaging;
	}
	
	public double getPackagingPrice() {
		return getPackagingPrice();
	}
	
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	public void setPackagingPrice(double packagingPrice) {
		this.packagingPrice = packagingPrice;
	}
	
	public void addFlower(Flower flower, int count) {
		flowers.put(flower,flowers.containsKey(flower) ?  flowers.get(flower)+count : count)  ;
		System.out.println(String.format("Добавлен %s в количестве %s шт.",flower.showFlowerInfo(),count));
	}
	
	public void calculatePriceOfBouquet() {
		double sum = 0;
		System.out.println("В ссоставе букета:");
		for(Entry<Flower, Integer> entry: flowers.entrySet()) {
			   Flower flower = entry.getKey();
			   int count = entry.getValue();
			   sum+=count*flower.getPrice();
			   System.out.println(String.format("%s в количестве %s шт.",flower.showFlowerInfo(),count));
			   }
		
		System.out.println(String.format("Упаковка : %s; цена : %s", packaging,packagingPrice));
		sum+=packagingPrice;
		System.out.println("Итого : " + sum );
		
	} 
}
