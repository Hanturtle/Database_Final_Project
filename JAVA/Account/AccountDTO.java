package Account;

public class AccountDTO {
	private String acnum;
	private int cnum;
	private int snum;
	private int bbcode;
	private String day;
	private int money;
	
	
	//奄沙 持失切
	public AccountDTO() {
	}
	
	//持失切
	public AccountDTO(String acnum, int cnum, int snum, int bbcode, String day, int money) {
		super();
		this.acnum = acnum;
		this.cnum = cnum;
		this.snum = snum;
		this.bbcode = bbcode;
		this.day = day;
		this.money = money;
	}
	
	
	
	public String getAcnum() {
		return acnum;
	}
	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	
	public int getBbcode() {
		return bbcode;
	}
	public void setBbcode(int bbcode) {
		this.bbcode = bbcode;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
}
