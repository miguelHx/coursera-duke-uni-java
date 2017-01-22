
/**
 * 
 * 
 * @author Miguel Hernandez 
 * @version nil
 */
import java.util.*;
public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        //Create a SecondRatings object
        SecondRatings sr = new SecondRatings();
        
        //print the number of movies
        System.out.println("Number of movies in file: " + sr.getMovieSize());
        //print the number of raters
        System.out.println("Number of raters in file: " + sr.getRaterSize());
        
        //print a list of movies and their average ratings, for 
        //all those movies with a specified number of ratings
        //average ratings can be obtained from getAverageRatings method in second ratings object
        int minimalRaters = 12;
        ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
        
        System.out.println("There are " + ratings.size() + " movies that have " + minimalRaters + " or more ratings.");
        
        System.out.println("Sorting the ratings...");
        //Since the rating class implements a Comparable, meaning that we override the compareTo method to compare
        //one property of the object in the array list, which is the average rating
        //This will sort the array list easily without having to use selection sort or other tedious algorithms
        Collections.sort(ratings);
        
        System.out.println("Sorting done.");
        
        for (int i = 0; i < ratings.size(); i++) {
            //Get rating
            double rating = ratings.get(i).getValue();
            //Get movie title
            String movieTitle = sr.getTitle(ratings.get(i).getItem());
            //print out rating and then movie title
            System.out.println(rating + " " + movieTitle);
        }
    }
    
    public void getAverageRatingOneMovie() {
        //create a SecondRatings object
        SecondRatings sr = new SecondRatings();
        
        //movie title of interst
        String movieTitle = "Vacation";
        
        //get id of movieTitle
        String movieID = sr.getID(movieTitle);
        
        //Get array list of average ratings
        ArrayList<Rating> avgRatings = sr.getAverageRatings(1);
        
        for (int i = 0; i < avgRatings.size(); i++) {
            //get current movie rating object
            Rating r = avgRatings.get(i);
            
            //get rating ID to compare with movieID
            String ratingID = r.getItem();
            //Check if movie id matches
            if (movieID.equals(ratingID)) {
                //If there is a match, then print out the average rating of this movie
                System.out.println("Average rating for " + movieTitle + " is " + r.getValue());
                return;
            }
        }
        //if we get down here, no value/movie was found
        System.out.println("Nothing found.");
        
        
        
    }
}
