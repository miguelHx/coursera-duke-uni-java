/**
 * 
 * This class will focus on doing calculations on computing averages on movie ratings
 * 
 * @author Miguel Hernandez
 * @version 0
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        //FirstRatings object aka FRO
        FirstRatings fro = new FirstRatings();
        //Call loadMovies and store result in myMovies
        myMovies = fro.loadMovies(moviefile);
        //Call loadRaters and store result in myRaters
        myRaters = fro.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        //Initialize totalRatings
        double totalRatings = 0.0;
        //Initialize numRatings
        int numRatings = 0;
        //Loop through myRaters private array list
        for (int i = 0; i < myRaters.size(); i++) {
            //Check if rater has rated movie
            if (myRaters.get(i).hasRating(id)) {
                //Get movie rating
                double movieRating = myRaters.get(i).getRating(id);
                //Add that to totalRatings
                totalRatings += movieRating;
                //Increment numRatings by 1
                numRatings++;
            }
            //otherwise, move to next iteration
        }
        //Check if numRatings is less than minimalRaters
        if (numRatings < minimalRaters) {
            //We are not going to count the average for this movie, so return 0.0
            return 0.0;
        }
        
        //Return the average movie rating
        return (totalRatings / numRatings);
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> output = new ArrayList<Rating>();
        
        //For each Movie object
        for (int k = 0; k < myMovies.size(); k++) {
            //Get current movie ID
            String currID = myMovies.get(k).getID();
            //Get average rating of currID
            double avgRating = getAverageByID(currID, minimalRaters);
            //If avgRating is NOT zero
            if (!(avgRating == 0.0)) {
                //Create a new rating object
                Rating r = new Rating(currID, avgRating);
                //Store the Rating object into the output array list
                output.add(r);
            }
        }
        //output is the answer
        return output;
    }
    
    
    public String getTitle(String id) {
        //for each movie object
        for (int i = 0; i < myMovies.size(); i++) {
            //get current movie object
            Movie currMovie = myMovies.get(i);
            //get currrent movie ID
            String currID = currMovie.getID();
            //Check if currID matches input id
            if (currID.equals(id)) {
                //if so, return movie title
                return currMovie.getTitle();
            }
        }
        //If we land down here, then the movie id was not found
        return "The id " + id + " was not found.";      
    }
    
    
    public String getID(String title) {
        //for each movie object
        for (int i = 0; i < myMovies.size(); i++) {
            //get current movie object
            Movie currMovie = myMovies.get(i);
            //get current movie title
            String currTitle = currMovie.getTitle();
            //Check if currTitle matches input title
            if (currTitle.equals(title)) {
                //if so, return movie ID
                return currMovie.getID();
            }
        }
        //If we land down here, then the movie title was not found
        return "NO SUCH TITLE";   
    }
    
    
    
    
    
    
    
    
}
