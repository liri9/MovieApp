package com.example.movieapp;

import com.example.movieapp.models.Categories;
import com.example.movieapp.models.Movie;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DBManager {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static ArrayList<Movie> allMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie()
                .setName("Titanic")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/1/18/Titanic_%281997_film%29_poster.png")
                .setRating(7.9)
                .setYear(1997)
                .setDescription("A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic."));

        movies.add(new Movie()
                .setName("The Shawshank Redemption")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/8/81/ShawshankRedemptionMoviePoster.jpg")
                .setRating(9.3)
                .setYear(1994)
                .setDescription("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."));

        movies.add(new Movie()
                .setName("The Godfather")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg")
                .setRating(9.2)
                .setYear(1972)
                .setDescription("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son."));
        movies.add(new Movie()
                .setName("The Dark Knight")
                .setCategory(Categories.ACTION)
                .setImage("https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_.jpg")
                .setRating(9.0)
                .setYear(2008)
                .setDescription("When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham. The Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice."));

        movies.add(new Movie()
                .setName("The Lord of the Rings: The Return of the King")
                .setCategory(Categories.FANTASY)
                .setImage("https://m.media-amazon.com/images/I/91+ni21hctL._UF1000,1000_QL80_.jpg")
                .setRating(8.9)
                .setYear(2003)
                .setDescription("Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."));

        movies.add(new Movie()
                .setName("The Matrix")
                .setCategory(Categories.SCI_FI)
                .setImage("https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg")
                .setRating(8.7)
                .setYear(1999)
                .setDescription("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers."));

        movies.add(new Movie()
                .setName("Pulp Fiction")
                .setCategory(Categories.CRIME)
                .setImage("https://upload.wikimedia.org/wikipedia/en/3/3b/Pulp_Fiction_%281994%29_poster.jpg")
                .setRating(8.9)
                .setYear(1994)
                .setDescription("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."));

        movies.add(new Movie()
                .setName("Interstellar")
                .setCategory(Categories.SCI_FI)
                .setImage("https://upload.wikimedia.org/wikipedia/en/b/bc/Interstellar_film_poster.jpg")
                .setRating(8.6)
                .setYear(2014)
                .setDescription("A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival."));

        movies.add(new Movie()
                .setName("Avengers: Endgame")
                .setCategory(Categories.ACTION)
                .setImage("https://upload.wikimedia.org/wikipedia/en/0/0d/Avengers_Endgame_poster.jpg")
                .setRating(8.4)
                .setYear(2019)
                .setDescription("After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));

        movies.add(new Movie()
                .setName("La La Land")
                .setCategory(Categories.MUSICAL)
                .setImage("https://upload.wikimedia.org/wikipedia/en/a/ab/La_La_Land_%28film%29.png")
                .setRating(8.0)
                .setYear(2016)
                .setDescription("While navigating their careers in Los Angeles, a pianistand an actress fall in love while attempting to reconcile their aspirations for the future."));

        movies.add(new Movie()
                .setName("The Departed")
                .setCategory(Categories.CRIME)
                .setImage("https://upload.wikimedia.org/wikipedia/en/5/50/Departed234.jpg")
                .setRating(8.5)
                .setYear(2006)
                .setDescription("An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston."));

        movies.add(new Movie()
                .setName("The Social Network")
                .setCategory(Categories.DRAMA)
                .setImage("https://movieposters2.com/images/706045-b.jpg")
                .setRating(7.7)
                .setYear(2010)
                .setDescription("Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, but is later sued by two brothers who claimed he stole their idea, and the co-founder who was later squeezed out of the business."));

        movies.add(new Movie()
                .setName("The Grand Budapest Hotel")
                .setCategory(Categories.COMEDY)
                .setImage("https://upload.wikimedia.org/wikipedia/he/a/a6/The_Grand_Budapest_Hotel_Poster.jpg")
                .setRating(8.1)
                .setYear(2014)
                .setDescription("A writer encounters the owner of an aging high-class hotel who tells him of his early years serving as a lobby boy in the hotel's glorious years under an exceptional concierge."));
        movies.add(new Movie()
                .setName("The Wolf of Wall Street")
                .setCategory(Categories.COMEDY)
                .setImage("https://m.media-amazon.com/images/I/51MAuTMt+gL._AC_UF894,1000_QL80_.jpg")
                .setRating(8.2)
                .setYear(2013)
                .setDescription("Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption, and the federal government."));

        movies.add(new Movie()
                .setName("Black Panther")
                .setCategory(Categories.ACTION)
                .setImage("https://m.media-amazon.com/images/I/81dae9nZFBS._AC_UF894,1000_QL80_.jpg")
                .setRating(7.3)
                .setYear(2018)
                .setDescription("T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and confront a challenger from his country's past."));

        movies.add(new Movie()
                .setName("Whiplash")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/0/01/Whiplash_poster.jpg")
                .setRating(8.5)
                .setYear(2014)
                .setDescription("A promising young drummer enrolls at a cutthroat music conservatory where his dreams of greatness are mentored by an instructor who will stop at nothing to realize a student's potential."));

        movies.add(new Movie()
                .setName("The Revenant")
                .setCategory(Categories.ADVENTURE)
                .setImage("https://upload.wikimedia.org/wikipedia/en/b/b6/The_Revenant_2015_film_poster.jpg")
                .setRating(8.0)
                .setYear(2015)
                .setDescription("A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear and left for dead by members of his own hunting team."));

        movies.add(new Movie()
                .setName("Her")
                .setCategory(Categories.ROMANCE)
                .setImage("https://upload.wikimedia.org/wikipedia/en/4/44/Her2013Poster.jpg")
                .setRating(8.0)
                .setYear(2013)
                .setDescription("In a near future, a lonely writer develops an unlikely relationship with an operating system designed to meet his every need."));

        movies.add(new Movie()
                .setName("Mad Max: Fury Road")
                .setCategory(Categories.ACTION)
                .setImage("https://upload.wikimedia.org/wikipedia/en/6/6e/Mad_Max_Fury_Road.jpg")
                .setRating(8.1)
                .setYear(2015)
                .setDescription("In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search of her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max."));
        movies.add(new Movie()
                .setName("The Prestige")
                .setCategory(Categories.MYSTERY)
                .setImage("https://upload.wikimedia.org/wikipedia/en/d/d2/Prestige_poster.jpg")
                .setRating(8.5)
                .setYear(2006)
                .setDescription("After a tragic accident, two stage magicians engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other."));

        movies.add(new Movie()
                .setName("Eternal Sunshine of the Spotless Mind")
                .setCategory(Categories.ROMANCE)
                .setImage("https://static.wikia.nocookie.net/universalstudios/images/6/62/Eternal_sunshine_of_the_spotless_mind_ver3.jpg")
                .setRating(8.3)
                .setYear(2004)
                .setDescription("When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories."));

        movies.add(new Movie()
                .setName("The Bourne Identity")
                .setCategory(Categories.ACTION)
                .setImage("https://upload.wikimedia.org/wikipedia/en/a/ae/BourneIdentityfilm.jpg")
                .setRating(7.9)
                .setYear(2002)
                .setDescription("A man is picked up by a fishing boat, bullet-riddled and suffering from amnesia, before racing to elude assassins and attempting to regain his memory."));

        movies.add(new Movie()
                .setName("Inglourious Basterds")
                .setCategory(Categories.ACTION)
                .setImage("https://upload.wikimedia.org/wikipedia/en/c/c3/Inglourious_Basterds_poster.jpg")
                .setRating(8.3)
                .setYear(2009)
                .setDescription("In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owner's vengeful plans for the same."));

        movies.add(new Movie()
                .setName("The Avengers")
                .setCategory(Categories.ACTION)
                .setImage("https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg")
                .setRating(8.0)
                .setYear(2012)
                .setDescription("Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity."));

        movies.add(new Movie()
                .setName("The Imitation Game")
                .setCategory(Categories.DRAMA)
                .setImage("https://www.hollywoodreporter.com/wp-content/uploads/2014/09/the_imitation_game_a_p.jpg")
                .setRating(8.0)
                .setYear(2014)
                .setDescription("During World War II, the English mathematical genius Alan Turing tries to crack the German Enigma code with help from fellow mathematicians."));
        movies.add(new Movie()
                .setName("The Shawshank Redemption")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/8/81/ShawshankRedemptionMoviePoster.jpg")
                .setRating(9.3)
                .setYear(1994)
                .setDescription("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."));

        movies.add(new Movie()
                .setName("Fight Club")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/f/fc/Fight_Club_poster.jpg")
                .setRating(8.8)
                .setYear(1999)
                .setDescription("An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more."));

        movies.add(new Movie()
                .setName("Forrest Gump")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/6/67/Forrest_Gump_poster.jpg")
                .setRating(8.8)
                .setYear(1994)
                .setDescription("The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other historical events unfold through the perspective of an Alabama man with an IQ of 75."));

        movies.add(new Movie()
                .setName("The Matrix")
                .setCategory(Categories.SCI_FI)
                .setImage("https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg")
                .setRating(8.7)
                .setYear(1999)
                .setDescription("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers."));

        movies.add(new Movie()
                .setName("Goodfellas")
                .setCategory(Categories.CRIME)
                .setImage("https://upload.wikimedia.org/wikipedia/en/7/7b/Goodfellas.jpg")
                .setRating(8.7)
                .setYear(1990)
                .setDescription("The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate."));

        movies.add(new Movie()
                .setName("Inception")
                .setCategory(Categories.ACTION)
                .setImage("https://upload.wikimedia.org/wikipedia/en/7/7f/Inception_ver3.jpg")
                .setRating(8.7)
                .setYear(2010)
                .setDescription("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O."));

        movies.add(new Movie()
                .setName("Schindler's List")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/3/38/Schindler%27s_List_movie.jpg")
                .setRating(8.9)
                .setYear(1993)
                .setDescription("In German-occupied Poland during World War II, Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis."));

        movies.add(new Movie()
                .setName("Seven")
                .setCategory(Categories.THRILLER)
                .setImage("https://upload.wikimedia.org/wikipedia/en/6/68/Seven_%28movie%29_poster.jpg")
                .setRating(8.6)
                .setYear(1995)
                .setDescription("Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives."));

        movies.add(new Movie()
                .setName("The Silence of the Lambs")
                .setCategory(Categories.THRILLER)
                .setImage("https://upload.wikimedia.org/wikipedia/en/8/86/The_Silence_of_the_Lambs_poster.jpg")
                .setRating(8.6)
                .setYear(1991)
                .setDescription("A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims."));

        movies.add(new Movie()
                .setName("The Green Mile")
                .setCategory(Categories.DRAMA)
                .setImage("https://m.media-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1_FMjpg_UX1000_.jpg")
                .setRating(8.6)
                .setYear(1999)
                .setDescription("The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift."));

        movies.add(new Movie()
                .setName("The Departed")
                .setCategory(Categories.CRIME)
                .setImage("https://upload.wikimedia.org/wikipedia/en/5/50/Departed234.jpg")
                .setRating(8.5)
                .setYear(2006)
                .setDescription("An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston."));

        movies.add(new Movie()
                .setName("City of God")
                .setCategory(Categories.CRIME)
                .setImage("https://upload.wikimedia.org/wikipedia/en/1/10/CidadedeDeus.jpg")
                .setRating(8.6)
                .setYear(2002)
                .setDescription("In the slums of Rio de Janeiro, two boys growing up in the same neighborhood take different paths: one becomes a photographer, the other a drug dealer."));
        movies.add(new Movie()
                .setName("The Lion King")
                .setCategory(Categories.ANIMATION)
                .setImage("https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg")
                .setRating(8.5)
                .setYear(1994)
                .setDescription("A young lion prince flees his kingdom after the murder of his father, while learning the true meaning of responsibility and bravery."));

        movies.add(new Movie()
                .setName("Avatar")
                .setCategory(Categories.ACTION)
                .setImage("https://m.media-amazon.com/images/I/41kTVLeW1CL._AC_UF894,1000_QL80_.jpg")
                .setRating(7.8)
                .setYear(2009)
                .setDescription("A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home."));

        movies.add(new Movie()
                .setName("The Dark Knight Rises")
                .setCategory(Categories.ACTION)
                .setImage("https://upload.wikimedia.org/wikipedia/en/8/83/Dark_knight_rises_poster.jpg")
                .setRating(8.4)
                .setYear(2012)
                .setDescription("Eight years after the Joker's reign of anarchy, Batman, with the help of the enigmatic Catwoman, is forced from his exile to save Gotham City from the brutal guerrilla terrorist Bane."));

        movies.add(new Movie()
                .setName("Gladiator")
                .setCategory(Categories.ACTION)
                .setImage("https://upload.wikimedia.org/wikipedia/sco/8/8d/Gladiator_ver1.jpg")
                .setRating(8.5)
                .setYear(2000)
                .setDescription("A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery."));

        movies.add(new Movie()
                .setName("The Prestige")
                .setCategory(Categories.MYSTERY)
                .setImage("https://upload.wikimedia.org/wikipedia/en/d/d2/Prestige_poster.jpg")
                .setRating(8.5)
                .setYear(2006)
                .setDescription("After a tragic accident, two stage magicians engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other."));

        movies.add(new Movie()
                .setName("Requiem for a Dream")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/9/92/Requiem_for_a_dream.jpg")
                .setRating(8.3)
                .setYear(2000)
                .setDescription("The drug-induced utopias of four Coney Island individuals are shattered when their addictions run deep."));
        movies.add(new Movie()
                .setName("Don't Worry Darling")
                .setCategory(Categories.THRILLER)
                .setImage("https://m.media-amazon.com/images/M/MV5BMzFkMWUzM2ItZWFjMi00NDY0LTk2MDMtZDhkMDE2MjRlYmZlXkEyXkFqcGdeQXVyNTAzNzgwNTg@._V1_FMjpg_UX1000_.jpg")
                .setRating(7.5)
                .setYear(2022)
                .setDescription("A 1950s housewife living with her husband in a utopian experimental community begins to worry that his glamorous company may be hiding disturbing secrets."));

        movies.add(new Movie()
                .setName("My Policeman")
                .setCategory(Categories.DRAMA)
                .setImage("https://upload.wikimedia.org/wikipedia/en/thumb/3/34/My_Policeman_%28film%29.jpg/220px-My_Policeman_%28film%29.jpg")
                .setRating(6.8)
                .setYear(2021)
                .setDescription("In the 1950s, a schoolteacher named Marion first meets Tom, who is working as a museum curator. They embark on a whirlwind romance, but Tom is also in love with Patrick, a handsome and charming policeman."));

        movies.add(new Movie()
                .setName("The Eternals")
                .setCategory(Categories.ACTION)
                .setImage("https://cdn.marvel.com/content/1x/eternals_lob_crd_06.jpg")
                .setRating(7.2)
                .setYear(2021)
                .setDescription("A group of ancient beings with superhuman abilities reunite to protect humanity from their evil counterparts, who seek to destroy the world."));

        movies.add(new Movie()
                .setName("Spider-Man: No Way Home")
                .setCategory(Categories.ACTION)
                .setImage("https://m.media-amazon.com/images/M/MV5BZWMyYzFjYTYtNTRjYi00OGExLWE2YzgtOGRmYjAxZTU3NzBiXkEyXkFqcGdeQXVyMzQ0MzA0NTM@._V1_FMjpg_UX1000_.jpg")
                .setRating(8.7)
                .setYear(2021)
                .setDescription("In the third installment of the Spider-Man franchise, Peter Parker's world is turned upside down when his secret identity is revealed, and he seeks help from alternate versions of Spider-Man to fix the multiverse."));

        movies.add(new Movie()
                .setName("The Flash")
                .setCategory(Categories.ACTION)
                .setImage("https://moviedocmelbourne.files.wordpress.com/2023/06/the-flash-film-poster.jpg")
                .setRating(7.6)
                .setYear(2022)
                .setDescription("Barry Allen, also known as the Flash, races against time to navigate the complexities of the Speed Force and save his mother from a tragic fate while facing dangerous adversaries from different dimensions."));

        movies.add(new Movie()
                .setName("Barbie")
                .setCategory(Categories.COMEDY)
                .setImage("https://deadline.com/wp-content/uploads/2023/04/barbie-BARBIE_VERT_TSR_W_TALENT_2764x4096_DOM_rgb.jpg?w=800")
                .setRating(7.2)
                .setYear(2023)
                .setDescription("In this live-action comedy, Margot Robbie stars as Barbie, a young woman who is magically transported into the world of Barbie, where she must embrace her uniqueness and help save the land from an evil enchantress."));

        return movies;
    }

    private DBManager() {

    }

    public FirebaseAuth getInstance() {
        return mAuth;
    }


}
