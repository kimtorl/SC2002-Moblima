import control.*;
import boundary.*;
import entity.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/*
 * 		Excecute this file for intital setup of the system
 */

public class InitialiseData {

	public static void main(String[] args) {
		
		
		//Initialise attributes
		AccountManager accMgr = new AccountFileManager();
		CineplexManager cineplexMgr = new CineplexFileManager();
		CinemaManager cinemaMgr = new CinemaFileManager(cineplexMgr);
		ShowtimeManager showtimeMgr = new ShowtimeFileManager(cinemaMgr);
		MovieManager movieMgr = new MovieFileManager();
		HolidayManager holidayMgr = new HolidayFileManager();
		TransactionManager transactionMgr = new TransactionFileManager();
		PriceManager priceMgr = new PriceFileManager();
		
		
		
		
		//create admin Accounts
		ArrayList<Capability> adminCapabilities = new ArrayList<Capability>();
		adminCapabilities.add(new ConfigureSystemSetting(holidayMgr, priceMgr));
		adminCapabilities.add(new EditMovieListing(movieMgr));
		adminCapabilities.add(new EditMovieShowtime(showtimeMgr, movieMgr));
		adminCapabilities.add(new Top5MovieByTicketSale(movieMgr, transactionMgr));
		adminCapabilities.add(new Top5MovieByRating(movieMgr));
		
		accMgr.createAdminAccount("PleaseGiveA+", "WeLoveOODP!", AccountType.ADMIN, adminCapabilities);
		
		//create MovieGoer Account
		ArrayList<Capability> movieGoerCapabilities = new ArrayList<Capability>();
		movieGoerCapabilities.add(new SearchOrListMovie(movieMgr));
		movieGoerCapabilities.add(new ViewMovieDetail(movieMgr));
		movieGoerCapabilities.add(new ReviewMovie(movieMgr));
		movieGoerCapabilities.add(new BookTicket(cinemaMgr, showtimeMgr, movieMgr, transactionMgr, priceMgr));
		movieGoerCapabilities.add(new ViewBookingHistory(transactionMgr));
		movieGoerCapabilities.add(new Top5MovieByTicketSale(movieMgr, transactionMgr));
		movieGoerCapabilities.add(new Top5MovieByRating(movieMgr));
		
		accMgr.createMovieGoerAccount("wakanda", "forever", AccountType.MOVIEGOER, movieGoerCapabilities, 
				"Adam", "93374263", "adam0021@e.ntu.edu.sg");
		
		
		//creates 3 Cineplex
		cineplexMgr.createCineplex(0, "Jem", new ArrayList<Cinema>());
		cineplexMgr.createCineplex(1, "Cineleisure Orchard", new ArrayList<Cinema>());
		cineplexMgr.createCineplex(2, "AMK Hub", new ArrayList<Cinema>());
		
		//creates 3 cinema for each Cineplex
		cinemaMgr.createCinema(0, "C00", ClassOfCinema.STANDARD, new ArrayList<Showtime>());
		cinemaMgr.createCinema(0, "C01", ClassOfCinema.STANDARD, new ArrayList<Showtime>());
		cinemaMgr.createCinema(0, "C02", ClassOfCinema.PLATINUM, new ArrayList<Showtime>());
		cinemaMgr.createCinema(1, "C10", ClassOfCinema.STANDARD, new ArrayList<Showtime>());
		cinemaMgr.createCinema(1, "C11", ClassOfCinema.STANDARD, new ArrayList<Showtime>());
		cinemaMgr.createCinema(1, "C12", ClassOfCinema.PLATINUM, new ArrayList<Showtime>());
		cinemaMgr.createCinema(2, "C20", ClassOfCinema.STANDARD, new ArrayList<Showtime>());
		cinemaMgr.createCinema(2, "C21", ClassOfCinema.STANDARD, new ArrayList<Showtime>());
		cinemaMgr.createCinema(2, "C22", ClassOfCinema.PLATINUM, new ArrayList<Showtime>());
			
		
		
		//create Movies
		movieMgr.createMovie(0, 
				"Black Panther: Wakanda Forever (English Sub) PG13", 
				TypeOfMovie.BLOCKBUSTER_2D, 
				"English", 
				161, 
				ShowingStatus.NOW_SHOWING, 
				"Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the "
				+ "wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with "
				+ "Nakia and Everett Ross to forge a new path for their beloved kingdom.", 
				"Ryan Coogler", 
				new ArrayList<String>(Arrays.asList("Lupita Nyong'o", "Danai Gurira", "Angela Bassett", "Winston Duke", "Letitia Wright", 
						"Florence Kasumba")), 
				new ArrayList<String>(Arrays.asList("Great movie!", "10/10 would recommend!")),
				new ArrayList<Integer>(Arrays.asList(5,5,5,5,5,5,5,5,4)));
		
		
		movieMgr.createMovie(1, 
				"Black Panther: Wakanda Forever (English Sub) PG13", 
				TypeOfMovie.BLOCKBUSTER_3D, 
				"English", 
				161, 
				ShowingStatus.NOW_SHOWING, 
				"Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the "
				+ "wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with "
				+ "Nakia and Everett Ross to forge a new path for their beloved kingdom.", 
				"Ryan Coogler", 
				new ArrayList<String>(Arrays.asList("Lupita Nyong'o", "Danai Gurira", "Angela Bassett", "Winston Duke", "Letitia Wright", 
						"Florence Kasumba")), 
				new ArrayList<String>(Arrays.asList("Great movie!", "10/10 would recommend!")),
				new ArrayList<Integer>(Arrays.asList(5,5,5,5,5,5,5,5,4)));
		
		
		movieMgr.createMovie(2, 
				"Black Adam (English Sub) PG13", 
				TypeOfMovie.NONBLOCKBUSTER_2D, 
				"English", 
				125, 
				ShowingStatus.NOW_SHOWING, 
				"Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods - and imprisoned just as quickly - "
				+"Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.", 
				"Jaume Collet-Serra", 
				new ArrayList<String>(Arrays.asList("Dwayne Johnson", "Aldis Hodge", "Pierce Brosnan", "Noah Centineo", "Sarah Shahi", 
						"Marwan Kenzari", "Quintessa Swindell", "Bodhi Sabongui")), 
				new ArrayList<String>(Arrays.asList("Great movie!", "10/10 would recommend!")),
				new ArrayList<Integer>(Arrays.asList(3,1,2,3,3,4,4,4,4)));
		
		
		movieMgr.createMovie(3, 
				"Ajoomma NC16", 
				TypeOfMovie.NONBLOCKBUSTER_2D, 
				"Mandarin, Korea, English", 
				90, 
				ShowingStatus.NOW_SHOWING, 
				"Produced by award-winning filmmaker Anthony Chen.\n\n"
				+ "Auntie (Hong Huifang), is a middle-aged Singaporean woman who has dedicated the best years of her life to caring for her "
				+ "family. Now widowed with her grown up son, Sam (Shane Pow) about to fly the roost, Auntie is left to contend with a whole new "
				+ "identity beyond her roles of daughter, wife, and mother.\n\n"
				+ "A solo trip to Korea becomes a wild adventure for Auntie, where she meets Kwon-Woo (Kang Hyung Suk), a young tour guide who "
				+ "can’t seem to get his life in order, and Jung Su (Jung Dong-Hwan), an elderly security guard. The trio embark on an unexpected "
				+ "roller coaster ride where hearts flutter and unlikely bonds are formed.\n\n"
				+ "Inspired by the director’s mother, AJOOMMA is the story of a woman’s journey of self-discovery, where Auntie learns to embrace "
				+ "her new independent life with renewed confidence and panache.\n", 
				"He Shuming",
				new ArrayList<String>(Arrays.asList("Hong Huifang", "Jung Dong-Hwan", "Kang Hyung Suk", "Yeo Jingoo")), 
				new ArrayList<String>(Arrays.asList("Great movie!", "10/10 would recommend!")),
				new ArrayList<Integer>(Arrays.asList(5,5,5,5,5,5,5,5,5)));
		
		
		movieMgr.createMovie(4, 
				"Deleted NC16", 
				TypeOfMovie.NONBLOCKBUSTER_2D, 
				"English, Mandarin, Malay", 
				86,
				ShowingStatus.NOW_SHOWING, 
				"The story starts with a Malaysian police detective - Chia Zhong Yi. In his desperate search for his daughter Hazel who was being "
				+ "kidnapped by child traffickers. He unintentionally caused grievous hurt to a male suspect in a moment of rashness. As a "
				+ "consequence of his actions, he was convicted and sentenced to 3 years in prison. Nevertheless, he never gave up hope in "
				+ "finding his daughter. Exploiting his status as an ex-convict, he infiltrated the crime syndicate and befriended a human "
				+ "trafficker Ghost, to find out about his daughter’s whereabouts.\n"
				+ "On the other side of the fence, we have Vincent Yong who was part of the Singapore Police Force - Star Team. He leads a "
				+ "successful raid against Four Faced Buddha, but was unable to apprehend him and his son. Meanwhile, in order to gather a "
				+ "large quantity of human organs for trafficking, Four Faced Buddha instructed his son, a dangerous hacker who goes by the "
				+ "name of Saviour, to steal the medical records from all the major hospitals in the regions.\n"
				+ "To escape from detection by the Interpol, Savior has remained hidden overseas, and it wasn’t until 3 years later that he was "
				+ "caught by the Malaysia Police Inspector Aron. Vincent was being ordered to extradite Savior back to Singapore and to persuade "
				+ "him to turn as a key prosecuting witness against Four Faced Buddha. At the same time, Ghost has been ordered by his boss Four "
				+ "Faced Buddha to rescue his son Savior during the extradition process. The loyalties of these few men are being severely "
				+ "tested. In an intense gun fight, Ghost was killed by Zhong Yi and his identity came under the suspicion of Vincent, creating "
				+ "conflicts between the two of them. In a wicked twist of fate, Vincent also accidentally discovers that his former Star Team "
				+ "instructor Jusuf whom he deeply respects has broken the law, and exploited the sting operation as a decoy to steal a smuggled "
				+ "heart to save his very own sick daughter.\n", 
				"Ken Ng Lai Huat",
				new ArrayList<String>(Arrays.asList("Jack Neo", "Fattah Amin", "Zheng Ge Ping", "Vincent Ng", "Dato Rosyam Nor", "Tien Hsin", 
						"Zhu Hou Ren", "Henley Hii", "Pablo Amirul")), 
				new ArrayList<String>(Arrays.asList("Great movie!", "10/10 would recommend!")),
				new ArrayList<Integer>(Arrays.asList(4,4,4,2,3,4,3,3,3)));
		
		
		movieMgr.createMovie(5, 
				"Love Today (Tamil) PG13", 
				TypeOfMovie.BLOCKBUSTER_2D, 
				"Tamil", 
				153,
				ShowingStatus.NOW_SHOWING, 
				"Imagine a world where the criteria to get married to your loved one is to explore each other's phone. A witty father lays such "
				+ "a trap to make his daughter realise how much she knows about her lover. The girl thinks she knows in and out of the boy and "
				+ "so does the boy. What happens from there is the rest of the movie.\n",
				"Pradeep Ranganathan",
				new ArrayList<String>(Arrays.asList("Pradeep Ranganathan", "Ivana")), 
				new ArrayList<String>(Arrays.asList("Great movie!", "10/10 would recommend!")),
				new ArrayList<Integer>(Arrays.asList(2,2,4,2,3,4,3,3,3)));
		
		movieMgr.createMovie(6, 
				"Talbis Iblis NC16", 
				TypeOfMovie.NONBLOCKBUSTER_2D, 
				"Malay", 
				98,
				ShowingStatus.NOW_SHOWING, 
				"To protect their family’s name from being scarred, Hajar who is pregnant out of wedlock, is being forced by her mother to have "
				+ "the baby far away from Kuala Lumpur. After several tries to aborting the child, her baby is alive, but now with a cleft lip, "
				+ "which only caused Hajar to further despise her unborn child. Hajar and her husband Arshad meet a midwife, Mak Ju who "
				+ "specializes in providing the best care before and after childbirth to fulfil her family’s request. After spending a few days "
				+ "under her care, Hajar finds in Mak Ju the mother she never had – someone loving and motherly. Eventually, Hajar begins to feel "
				+ "love for her baby, inspired by the motherly love Mak Ju had shown to Hajar herself. But unbeknownst to Hajar, Mak Ju’s ill "
				+ "intentions would soon come to harm Hajar and her precious child.",
				"Khabir Bhatia",
				new ArrayList<String>(Arrays.asList("Pradeep Ranganathan", "Ivana")), 
				new ArrayList<String>(Arrays.asList("Great movie!", "10/10 would recommend!")),
				new ArrayList<Integer>(Arrays.asList(2,2,4,2,3,4,3,3,3)));
		
		
		//Create Holiday starting from 9 Aug 2022 until end of 2023
		holidayMgr.createHoliday(LocalDate.of(2022, 8, 9)); 	//National Day
		holidayMgr.createHoliday(LocalDate.of(2022, 10, 24)); 	//Deepavali
		holidayMgr.createHoliday(LocalDate.of(2022, 12, 26));	//Christmas PH in-lieu
		holidayMgr.createHoliday(LocalDate.of(2023, 1, 2));		//New Year's Day PH in-lieu
		holidayMgr.createHoliday(LocalDate.of(2023, 1, 23));	//CNY
		holidayMgr.createHoliday(LocalDate.of(2023, 1, 24));	//CNY
		holidayMgr.createHoliday(LocalDate.of(2023, 4, 7));		//Good Friday
		holidayMgr.createHoliday(LocalDate.of(2023, 4, 22));	//Hari Raya Puasa
		holidayMgr.createHoliday(LocalDate.of(2023, 5, 1));		//Labour Day
		holidayMgr.createHoliday(LocalDate.of(2023, 6, 3));		//Vesak Day
		holidayMgr.createHoliday(LocalDate.of(2023, 6, 29));	//Hari Raya Haji
		holidayMgr.createHoliday(LocalDate.of(2023, 8, 9));		//National Day
		holidayMgr.createHoliday(LocalDate.of(2023, 11, 13));	//Deepavali PH in-lieu
		holidayMgr.createHoliday(LocalDate.of(2023, 12, 25));	//Christmas
		
		
		//creates Showtimes for each Cinema based on current System time
		showtimeMgr.populateAllCinemaShowtime(LocalDateTime.now());
		//Create Past transactions - not needed?
		
		//Create Price files
		priceMgr.initialisePrice();
		
		
		
		
		
		
		
		
		
		
	}//end of main
	
	
	
}
