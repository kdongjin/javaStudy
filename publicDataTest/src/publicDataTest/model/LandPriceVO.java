package publicDataTest.model;

public class LandPriceVO {
	private int nodeno; 		//<nodeno>44810</nodeno> pk
	private double gpslati; 	//<gpslati>36.43535</gpslati>
	private double gpslong; 	//<gpslong>127.3863</gpslong>
	private String nodeid; 		//<nodeid>DJB8001793</nodeid>
	private String nodenm; 		//<nodenm>송강전통시장</nodenm>
	public LandPriceVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LandPriceVO(int nodeno, double gpslati, double gpslong, String nodeid, String nodenm) {
		super();
		this.nodeno = nodeno;
		this.gpslati = gpslati;
		this.gpslong = gpslong;
		this.nodeid = nodeid;
		this.nodenm = nodenm;
	}
	public int getNodeno() {
		return nodeno;
	}
	public void setNodeno(int nodeno) {
		this.nodeno = nodeno;
	}
	public double getGpslati() {
		return gpslati;
	}
	public void setGpslati(double gpslati) {
		this.gpslati = gpslati;
	}
	public double getGpslong() {
		return gpslong;
	}
	public void setGpslong(double gpslong) {
		this.gpslong = gpslong;
	}
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getNodenm() {
		return nodenm;
	}
	public void setNodenm(String nodenm) {
		this.nodenm = nodenm;
	}
	@Override
	public String toString() {
		return "LandPrice [nodeno=" + nodeno + ", gpslati=" + gpslati + ", gpslong=" + gpslong + ", nodeid=" + nodeid
				+ ", nodenm=" + nodenm + "]";
	}
}
