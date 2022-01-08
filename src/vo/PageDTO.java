package vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	private static int PAGE_AMOUNT=10;
	private int total;
	private Criteria cri;
	
	private int startPage;
	private int endPage;
	private boolean prev; // 이전 페이지
	private boolean next; //다음 페이지가 있는가 기준
	
	
	public PageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PageDTO(int total, Criteria cri) {
		this.total = total;
		this.cri = cri;
		// TODO Auto-generated constructor stub
		
		//수식계산
		endPage = ((cri.getPageNum() - 1) / PAGE_AMOUNT+1)*PAGE_AMOUNT;
		
		startPage = endPage - PAGE_AMOUNT + 1;
		
		int realEnd = (total + cri.getAmount() - 1) / cri.getAmount();
		
		endPage = realEnd < endPage ? realEnd : endPage;
		
		prev = startPage > 1;
		next = endPage < realEnd;
		
		System.out.println("페이지DTO탐");
	}
	public static void main(String[] args) {
		System.out.println(new PageDTO(90, new Criteria(1, 5)));
	}
	
	
}
