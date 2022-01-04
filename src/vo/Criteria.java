package vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Criteria {
	private int pagenum;
	private int amount;
	private int category=1;
	
	public Criteria() {
		this(1, 20);
	}
	
	public Criteria(int pagenum, int amount) {
		super();
		this.pagenum = pagenum;
		this.amount = amount;
		this.category = category;
	}
	
	
}
