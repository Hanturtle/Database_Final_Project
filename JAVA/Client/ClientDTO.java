package Client;

public class ClientDTO {
	private int cnum;
	private String pnum;
	private String name;
	private String ph;
	
	
	//奄沙 持失切
	public ClientDTO() {
	}
	
	//持失切
	public ClientDTO(int cnum, String pnum, String name, String ph) {
		super();
		this.cnum = cnum;
		this.pnum = pnum;
		this.name = name;
		this.ph = ph;
	}
	
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getpnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	
}
