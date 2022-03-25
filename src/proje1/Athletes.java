package proje1;

public class Athletes 
{
	private String country;
	private String sport;
	private String name;
	private String gender;
	private String birthdate;
	private double skill;
	public Athletes(String country, String sport, String name, String gender, String birthdate, double skill) {
		this.country = country;
		this.sport = sport;
		this.name = name;
		this.gender = gender;
		this.birthdate = birthdate;
		this.skill = skill;
		
		
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public double getSkill() {
		return skill;
	}
	public void setSkill(double skill) {
		this.skill = skill;
	}
	
	
	
}
