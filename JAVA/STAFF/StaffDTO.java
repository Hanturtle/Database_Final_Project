package STAFF;

public class StaffDTO {
	private int snum;
	private String pnum;
	private String name;
	private String position;
	private String ph;
	
	
	//奄沙 持失切
	public StaffDTO() {
	}
	
	//持失切
	public StaffDTO(int snum, String pnum, String name, String position, String ph) {
		super();
		this.snum = snum;
		this.pnum = pnum;
		this.name = name;
		this.position = position;
		this.ph = ph;
	}
	
	
	public int getSnum() {
		return snum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}

	public String getPnum() {
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
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	
}
