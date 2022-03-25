package proje1;

import java.io.*;
import java.util.Scanner;

public class proje1 {

	public static int NumberOfLines(String input) {
		int count = 0;
		try {
			FileInputStream fStream = new FileInputStream(input);
			DataInputStream dStream = new DataInputStream(fStream);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream, "UTF8"));
			while (bReader.readLine() != null) {
				count = count + 1;
			}
			dStream.close();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		return count;
	}

	public static void main(String[] args) {
		String str = "";
		boolean flag = true;
		String[] data;
		String[] countries = new String[400];
		Athletes[] athletes = new Athletes[400];
		String [] sport = new String[400];
		maclar[] mac = new maclar[400];
		date[] dates = new date[400];

		try {
			//Reading input.
			Scanner scn = new Scanner(System.in);
			System.out.print("Enter file name to load: ");
			String input = scn.next();
			int nol = NumberOfLines(input);
			boolean control = true;
			FileInputStream fStream = new FileInputStream(input);
			DataInputStream dStream = new DataInputStream(fStream);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream, "UTF8"));
			int i = 0;
			int w =0;
			int countryi =0;
			//Transferring the necessary information to the arrays, checking the number of countries and sports
			while ((str = bReader.readLine()) != null) {
				data = str.split(",");
				athletes[i] = new Athletes(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]));
				i++;
				boolean sportflag = true;
			    for (int j = 0; j < w; j++) {
					if (sport[j].equals(data[1])) {
						sportflag = false;
					}
				}
			    if (sportflag == true) {
					sport[w] = data[1];
					w++;
				}
			    boolean countflag = true;
			    for (int j = 0; j < countryi; j++) {
					if (countries[j].equals(data[0])) {
						countflag = false;
					}
				}
			    if (countflag == true) {
			    	countries[countryi] = data[0];
					countryi++;
				}
			}
			dStream.close();
			boolean SportFlag = true;
			if (w>20) {
				System.out.println("There are more than 20 types of sports in the input.");
				SportFlag = false;
			}
			boolean CountryFlag = true;
			if (countryi>20) {
				System.out.println("There are more than 20 different countries in the input.");
				CountryFlag = false;
			}
			//athlete control
			for (int j = 0; j < i; j++) {
				if (athletes[j] != null)
					for (int k = j + 1; k < i; k++) {
						if (athletes[k] != null) {
							if (athletes[j].getCountry().equals(athletes[k].getCountry())
									&& athletes[j].getSport().equalsIgnoreCase(athletes[k].getSport())) {
								if (athletes[j].getSkill() > athletes[k].getSkill()) {
									athletes[k] = null;
								} else if (athletes[j].getSkill() == athletes[k].getSkill())
									athletes[k] = null;
								else
									athletes[j] = null;
							}
						}
					}

			}
			//Gender control
			for (int j = 0; j < i; j++) {
				if (athletes[j] != null) {
					if (!(athletes[j].getGender().equalsIgnoreCase("F")
							|| athletes[j].getGender().equalsIgnoreCase("M"))) {
						System.out.println(athletes[j].getGender() + " is INVALID GENDER !!!");
						control = false;
					}
					// Date control
					String[] kontrol = athletes[j].getBirthdate().split("\\.");
					int inputd = Integer.valueOf(kontrol[0]);
					int inputm = Integer.valueOf(kontrol[1]);
					int inputy = Integer.valueOf(kontrol[2]);
					if (inputm < 0 || inputm > 12) {
						control = false;
					}
					if ((inputm == 2 && inputy % 4 == 0) && (inputd < 0 || inputd > 29)) {
						control = false;
					}
					if ((inputm == 2 && inputy % 4 != 0) && (inputd < 0 || inputd > 28)) {
						control = false;
					}
					if ((inputm == 1 || inputm == 3 || inputm == 5 || inputm == 7 || inputm == 8 || inputm == 10
							|| inputm == 12) && (inputd < 0 || inputd > 31)) {
						control = false;
					}
					if ((inputm == 4 || inputm == 6 || inputm == 9 || inputm == 11) && (inputd < 0 || inputd > 30)) {
						control = false;
					}
					if (control == false) {
						System.out.println(athletes[j].getBirthdate() + " is INVALID DATE !!!");
					}
				}

			}
			if (control == true &&  SportFlag == true && CountryFlag == true) {
				System.out.println("***** MENU *****");
				System.out.println("1.Generate fixture");
				System.out.println("2.Perform tournaments");
				System.out.println("3.Show statistics");
				//Calculation of match dates and transferring to arrays
				int d = 24;
				int m = 07;
				int y = 2020;
				int h = 11;
				int m1 = 00;
				int sayac = 0;
				int sayac1 = 0;
				int macsayýsý = 0;
				for (int j = 0; j < nol; j++) {
					if (athletes[j] != null) {
						for (int j2 = j + 1; j2 < nol; j2++) {
							if (athletes[j2] != null) {
								if (athletes[j].getSport().equals(athletes[j2].getSport())) {
									macsayýsý++;
								}
							}
						}
					}
				}
				for (int j = 0; j < macsayýsý; j++) {
					if (sayac == 3) {
						d++;
						h = 11;
						sayac = 0;
					}
					if (m == 2 && y % 4 == 0 && d > 29) {
						m++;
						d = 01;
					}
					if (m == 2 && y % 4 != 0 && d > 28) {
						m++;
						d = 01;
					}
					if ((m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) && d > 31) {
						m++;
						d = 01;
					}
					if ((m == 4 || m == 6 || m == 9 || m == 11) && d > 30) {
						m++;
						d = 01;
					}
					if (m > 12) {
						y++;
						m = 01;
					}

					dates[j] = new date(d, m, y, h, m1);
					h = h + 2;
					sayac++;
				}
				//Transfer of matches to the arrays with necessary information
				int sayac2 = 0;
				for (int j = 0; j < nol; j++) {
					if (athletes[j] != null) {
						for (int j2 = j + 1; j2 < nol; j2++) {
							if (athletes[j2] != null) {
								if (athletes[j].getSport().equals(athletes[j2].getSport())) {
									mac[sayac2] = new maclar(athletes[j], athletes[j2], dates[sayac2]);
									sayac2++;
								}
							}

						}
					}

				}
				//Fixture
				System.out.print("Enter your choice: ");
				int a = scn.nextInt();
				if (a == 1) {
					for (int j = 0; j < macsayýsý; j++) {
						System.out.println(mac[j].getZaman().getDay() + "." + mac[j].getZaman().getMounth() + "."
								+ mac[j].getZaman().getYear() + " " + mac[j].getZaman().getHour() + ":00" + " "
								+ mac[j].getBirinci().getSport() + " " + mac[j].getBirinci().getCountry() + "-"
								+ mac[j].getBirinci().getName() + " " + mac[j].getIkinci().getCountry() + "-"
								+ mac[j].getIkinci().getName());
					}
				//Perform tournaments	
				} else if (a == 2) {
					System.out.println("Tournaments have been performed.");
					for (int j = 0; j < macsayýsý; j++) {
						System.out.println(mac[j].getZaman().getDay() + "." + mac[j].getZaman().getMounth() + "."
								+ mac[j].getZaman().getYear() + " " + mac[j].getZaman().getHour() + ":00" + " "
								+ mac[j].getBirinci().getSport() + " " + mac[j].getBirinci().getCountry() + "-"
								+ mac[j].getBirinci().getName() + "[" + mac[j].getBirinci().getSkill() + "]" + " "
								+ mac[j].getIkinci().getCountry() + "-" + mac[j].getIkinci().getName() + "["
								+ mac[j].getIkinci().getSkill() + "]");
						System.out.println("		Winner: " + mac[j].getKazanan().getName());

					}
					//Determining champions in their sports
					ScoreTable[] scoresOfAthletes = new ScoreTable[macsayýsý];
					int q = 0;
					int x = 0;
					for (int j = 0; j < macsayýsý; j++) {
						if (mac[j] != null) {
							int scoreAthlete = 1;
							for (int k = j + 1; k < macsayýsý; k++) {
								if (mac[k] != null) {
									if (mac[j].getKazanan() == mac[k].getKazanan()) {
										scoreAthlete++;
										mac[k] = null;
									}
								}
							}
							scoresOfAthletes[q] = new ScoreTable(mac[j].getKazanan(), scoreAthlete);
							q++;

						}
					}
					for (int j = 0; j < q; j++) {
						if (scoresOfAthletes[j] != null) {
							for (int k = 0; k < q; k++) {
								if (scoresOfAthletes[k] != null) {
									if (scoresOfAthletes[j].getWinner().getSport()
											.equals(scoresOfAthletes[k].getWinner().getSport())) {
										if (scoresOfAthletes[j].getScoreAthlete() > scoresOfAthletes[k]
												.getScoreAthlete()) {
											scoresOfAthletes[k] = null;
										}

									}
								}
							}
						}

					}
					for (int j = 0; j < q; j++) {
						if (scoresOfAthletes[j] != null) {
							System.out.println(scoresOfAthletes[j].getWinner().getSport() + " winner country is: "
									+ scoresOfAthletes[j].getWinner().getCountry() + " with "
									+ scoresOfAthletes[j].getScoreAthlete() + " points");
						}

					}
				} else if (a == 3) {
					//Calculation the country which won most of the matches
					System.out.println("***** Statistics Menu *****");
					System.out.println("1.the country which won most of the matches");
					System.out.println("2.the athlete who won most of the matches");
					System.out.println("3.list of matches by date");
					System.out.println("4.list of matches by country");
					System.out.print("Please select which statistics you want to see: ");
					int b = scn.nextInt();
					if (b == 1) {
						ScoreTable[] scoresOfCountry = new ScoreTable[macsayýsý];
						int q = 0;
						int x = 0;
						for (int j = 0; j < macsayýsý; j++) {
							if (mac[j] != null) {
								int scoreCountry = 1;
								for (int k = j + 1; k < macsayýsý; k++) {
									if (mac[k] != null) {
										if (mac[j].getKazanan() == mac[k].getKazanan()) {
											scoreCountry++;
											mac[k] = null;
										}
									}
								}
								scoresOfCountry[q] = new ScoreTable(mac[j].getKazanan().getCountry(), scoreCountry);
								q++;

							}
						}
						for (int j = 0; j < q; j++) {
							if (scoresOfCountry[j] != null) {
								for (int k = j + 1; k < q; k++) {
									if (scoresOfCountry[k] != null) {
										if (scoresOfCountry[j].getCountry()
												.equalsIgnoreCase(scoresOfCountry[k].getCountry())) {
											scoresOfCountry[j] = new ScoreTable(scoresOfCountry[j].getCountry(),
													scoresOfCountry[j].getScoreCountry()
															+ scoresOfCountry[k].getScoreCountry());
											scoresOfCountry[k] = null;
										}
									}
								}
							}
						}
						int countTemp = 0;
						for (int j = 0; j < q; j++) {
							if (scoresOfCountry[j] != null && scoresOfCountry[j].getScoreCountry() > countTemp) {
								countTemp = scoresOfCountry[j].getScoreCountry();
							}
						}
						for (int j = 0; j < q; j++) {
							if (scoresOfCountry[j] != null && scoresOfCountry[j].getScoreCountry() < countTemp) {
								scoresOfCountry[j] = null;
							}
						}
						for (int j = 0; j < q; j++) {
							if (scoresOfCountry[j] != null) {
								System.out.println(scoresOfCountry[j].getCountry() + " Score: "
										+ scoresOfCountry[j].getScoreCountry());
							}

						}
					}
					//Calculation the athlete which won most of the matches
					if (b == 2) {
						ScoreTable[] scoresOfAthletes = new ScoreTable[macsayýsý];
						int q = 0;
						int x = 0;
						for (int j = 0; j < macsayýsý; j++) {
							if (mac[j] != null) {
								int scoreAthlete = 1;
								for (int k = j + 1; k < macsayýsý; k++) {
									if (mac[k] != null) {
										if (mac[j].getKazanan() == mac[k].getKazanan()) {
											scoreAthlete++;
											mac[k] = null;
										}
									}
								}
								scoresOfAthletes[q] = new ScoreTable(mac[j].getKazanan(), scoreAthlete);
								q++;

							}
						}
						int temp = 0;
						for (int j = 0; j < q; j++) {
							if (scoresOfAthletes[j].getScoreAthlete() > temp) {
								temp = scoresOfAthletes[j].getScoreAthlete();
							}
						}
						for (int j = 0; j < q; j++) {
							if (scoresOfAthletes[j] != null && scoresOfAthletes[j].getScoreAthlete() < temp) {
								scoresOfAthletes[j] = null;
							}
						}
						for (int j = 0; j < q; j++) {
							if (scoresOfAthletes[j] != null) {
								System.out.println(scoresOfAthletes[j].getWinner().getName() + " Score: "
										+ scoresOfAthletes[j].getScoreAthlete());
							}

						}
						//Calculation the list of matches by date
					} else if (b == 3) {
						boolean control2 = true;
						System.out.print("Enter date: ");
						String inputdate = scn.next();
						//Date Control
						String[] Idate = inputdate.split("\\.");
						int inputd = Integer.valueOf(Idate[0]);
						int inputm = Integer.valueOf(Idate[1]);
						int inputy = Integer.valueOf(Idate[2]);
						if (inputm < 0 || inputm > 12) {
							control2 = false;
						}
						if ((inputm == 2 && inputy % 4 == 0) && (inputd < 0 || inputd > 29)) {
							control2 = false;
						}
						if ((inputm == 2 && inputy % 4 != 0) && (inputd < 0 || inputd > 28)) {
							control2 = false;
						}
						if ((inputm == 1 || inputm == 3 || inputm == 5 || inputm == 7 || inputm == 8 || inputm == 10
								|| inputm == 12) && (inputd < 0 || inputd > 31)) {
							control2 = false;
						}
						if ((inputm == 4 || inputm == 6 || inputm == 9 || inputm == 11)
								&& (inputd < 0 || inputd > 30)) {
							control2 = false;
						}
						if (control2 == false) {
							System.out.println(inputdate + " is INVALID DATE !!!");
						}
						if (control2 == true) {
							boolean controlflag = true;
							for (int j = 0; j < macsayýsý; j++) {
								if (mac[j].getZaman().getDay() == Integer.valueOf(Idate[0])
										&& mac[j].getZaman().getMounth() == Integer.valueOf(Idate[1])
										&& mac[j].getZaman().getYear() == Integer.valueOf(Idate[2])) {
									controlflag = false;
									System.out.println(mac[j].getZaman().getDay() + "." + mac[j].getZaman().getMounth()
											+ "." + mac[j].getZaman().getYear() + " " + mac[j].getZaman().getHour()
											+ ":00" + " " + mac[j].getBirinci().getSport() + " "
											+ mac[j].getBirinci().getCountry() + "-" + mac[j].getBirinci().getName()
											+ " " + mac[j].getIkinci().getCountry() + "-"
											+ mac[j].getIkinci().getName());

								}
							}
							if (controlflag == true) {
								System.out.println("No tournament match on " + inputdate);
							}
						}
					}
					//Calculation the list of matches by country
					if (b == 4) {
						System.out.print("Enter country: ");
						String inputCountry = scn.next();
						boolean controlflag1 = true;
						for (int j = 0; j < macsayýsý; j++) {
							if (mac[j].getBirinci().getCountry().equalsIgnoreCase(inputCountry)
									|| mac[j].getIkinci().getCountry().equalsIgnoreCase(inputCountry)) {
								System.out.println(mac[j].getZaman().getDay() + "." + mac[j].getZaman().getMounth()
										+ "." + mac[j].getZaman().getYear() + " " + mac[j].getZaman().getHour() + ":00"
										+ " " + mac[j].getBirinci().getSport() + " " + mac[j].getBirinci().getCountry()
										+ "-" + mac[j].getBirinci().getName() + " " + mac[j].getIkinci().getCountry()
										+ "-" + mac[j].getIkinci().getName());
								controlflag1 = false;
							}
						}
						if (controlflag1 == true) {
							System.out.println(inputCountry + " did not participate in the Olympics.");
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}

	}

}
