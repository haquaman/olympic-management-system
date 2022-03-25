package proje1;

public class maclar {
	private Athletes birinci;
	private Athletes ikinci;
	private Athletes kazanan;
	private date zaman;
	public maclar(Athletes birinci, Athletes ikinci,date zaman) {
		this.birinci = birinci;
		this.ikinci = ikinci;
		this.zaman = zaman;
		
		
	}
	public date getZaman() {
		return zaman;
	}
	public void setZaman(date zaman) {
		this.zaman = zaman;
	}
	public Athletes getBirinci() {
		return birinci;
	}
	public void setBirinci(Athletes birinci) {
		this.birinci = birinci;
	}
	public Athletes getIkinci() {
		return ikinci;
	}
	public void setIkinci(Athletes ikinci) {
		this.ikinci = ikinci;
	}
	public Athletes getKazanan() {
		if(birinci.getSkill()>ikinci.getSkill()) {
			kazanan = birinci;
		}
		else kazanan = ikinci;
		return kazanan;
	}
	public void setKazanan(Athletes kazanan) {
		this.kazanan = kazanan;
	}
	
	

}
