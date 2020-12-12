package BankBook;

public class BankBookDTO {
	private int bbcode;
	private String bbkind;
	private String bbname;
	private int percent;
	private int limit;
	
	
	//奄沙 持失切
	public BankBookDTO() {
	}
	
	//持失切
	public BankBookDTO(int bbcode, String bbkind, String bbname, int percent, int limit) {
		super();
		this.bbcode = bbcode;
		this.bbkind = bbkind;
		this.bbname = bbname;
		this.percent = percent;
		this.limit = limit;
	}
	
	
	public int getBBCode() {
		return bbcode;
	}
	public void setBBCode(int bbcode) {
		this.bbcode = bbcode;
	}

	public String getBBKind() {
		return bbkind;
	}
	public void setBBKind(String bbkind) {
		this.bbkind = bbkind;
	}
	
	public String getBBName() {
		return bbname;
	}
	public void setBBName(String bbname) {
		this.bbname = bbname;
	}
	
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
