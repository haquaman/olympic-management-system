package proje1;

public class ScoreTable {
	private Athletes winner;
	private int scoreAthlete;
	private  String country;
	private int scoreCountry;
	public ScoreTable(Athletes winner, int scoreAthlete) {
		this.winner = winner;
		this.scoreAthlete = scoreAthlete;
	}
	public ScoreTable(String country, int scoreCountry) {
		this.country = country;
		this.scoreCountry = scoreCountry;
	}
	public Athletes getWinner() {
		return winner;
	}
	public void setWinner(Athletes winner) {
		this.winner = winner;
	}
	public int getScoreAthlete() {
		return scoreAthlete;
	}
	public void setScoreAthlete(int scoreAthlete) {
		this.scoreAthlete = scoreAthlete;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getScoreCountry() {
		return scoreCountry;
	}
	public void setScoreCountry(int scoreCountry) {
		this.scoreCountry = scoreCountry;
	}
	

}
