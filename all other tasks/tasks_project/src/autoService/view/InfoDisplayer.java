package autoService.view;


import java.util.List;

public class InfoDisplayer {
	
	public static <T> void  printListWithNumeration(List<T> list) {
		long counter = 1;
		for(T element : list) {
			System.out.println(counter+"."+ element);
			counter+=1;
		}
	}
}
