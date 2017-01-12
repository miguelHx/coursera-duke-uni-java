
/**
 * This class will process the movie and ratings data and will 
 * answer questions about them.
 * CSVParser and CSVRecord will be used
 * 
 * @author Miguel Hernandez
 * @version 0
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        //Empty ArrayList of type Movie
        ArrayList<Movie> output = new ArrayList<Movie>();
        
        //Get the file of interest
        String file = "data/" + filename;
        FileResource fr = new FileResource(file);
        
        //Get the CSV parser of the file resource
        CSVParser parser = fr.getCSVParser();
        
        //for each record (row), put info into movie object
        for (CSVRecord record : parser) {
            /*
            System.out.print(record.get("id") + " ");
            System.out.print(record.get("title") + " ");
            System.out.print(record.get("year") + " ");
            System.out.print(record.get("country") + " ");
            System.out.print(record.get("genre") + " ");
            System.out.print(record.get("director") + " ");
            System.out.println(record.get("minutes") + " ");
            */
           
            //Make a new Movie object
            //Get each record and place according to Movie constructor
            Movie currMovie = new Movie(record.get("id"), 
                                        record.get("title"),
                                        record.get("year"),
                                        record.get("genre"),
                                        record.get("director"),
                                        record.get("country"),
                                        record.get("poster"),
                                        Integer.parseInt(record.get("minutes")));
            //Add created movie object to 'output' arraylist
            output.add(currMovie);
            
        }
        
        return output;
        
    }
    
    public void testLoadMovies() {
        //Call loadMovies on the rile 'ratedmovies_short.csv' and store to a local ArrayList variable
        //ArrayList<Movie> movies = loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
        
        //print the number of movies
        System.out.println(movies.size() + " movies were loaded.");
        
        //print each movie
        /*
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
        */
        //check how many movies include the Comedy genre
        int numComedy = 0;
        for (int i = 0; i < movies.size(); i++) {
            //Get current movie object
            Movie currMovie = movies.get(i);
            //Get genres of current movie and store it in a string
            String currGenres = currMovie.getGenres();
            //Check if 'Comedy' index exists in currGenres string
            int idxComedy = currGenres.indexOf("Comedy");
            //if index is equal to -1
            if (idxComedy == -1) {
                //go on to the next iteration
                continue;
            }
            //Otherwise, Comedy is listed in current movie's genre
            //Increment numComedy by one
            numComedy++;
        }
        
        System.out.println("There are " + numComedy + " Comedy movies in the file.");
        
        //Going to try an implementation without using the 'continue' statement.
        //Determine how many movies are greater than 150 minutes in length
        int numGrThan150Min = 0;
        for (int i = 0; i < movies.size(); i++) {
            //get current movie object
            Movie currMovie = movies.get(i);
            //Get minutes of current movie and store it in an integer
            int minutes = currMovie.getMinutes();
            //Check if minutes is greater than 150
            if (minutes > 150) {
                //if so, add to numGrThan150Min counter
                numGrThan150Min++;
            }
            
            //Otherwise, continue the loop
        }
        
        System.out.println("There are " + numGrThan150Min + " movies that are greater than 150 minutes in length in the file.");
        
        
        //Determine the maximum number of movies by any director, and who the directors are that directed that many movies.
        //Some movies may have more than one director.
        
        
        ArrayList<String> directors = new ArrayList<String>();
        ArrayList<Integer> directorCounts = new ArrayList<Integer>();
        
        //for each director in movies
        for (int i = 0; i < movies.size(); i++) {
            //Get director name as a string
            String currDirector = movies.get(i).getDirector();
            //Get index of currDirector in directors
            int checkIdx = directors.indexOf(currDirector);
            //If idx == -1, currDirector has not been added yet
            if (checkIdx == -1) {
                //add current director into directors array list
                directors.add(currDirector);
                //initialize the count to one
                directorCounts.add(1);
            } else {
               //if we reach this code, then the director exists in directors array list
               //Get index of currDirector, which should be stored in 'checkIdx'
               //increment count of director by one
               directorCounts.set(checkIdx, directorCounts.get(checkIdx) + 1);
            }
        }
        
        //Get max number in directorCounts
        int max = 0;
        for (int i = 0; i < directorCounts.size(); i++) {
            if (directorCounts.get(i) > max) {
                max = directorCounts.get(i);
            }
        }
        
        System.out.println("The maximum number of movies by any director is " + max);
        
        //Now that we have the max number in directorCount
        //We must store indices that have the max number into an ArrayList of integers called indices
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        for (int i = 0; i < directorCounts.size(); i++) {
            if (directorCounts.get(i) == max) {
                indices.add(i);
            }
            
        }
        
        //Now, print out the name of each director that has a max number of movies directed in the file
        System.out.println("Directors that directed " + max + " amount of movies are as follows:");
        for (int i = 0; i < indices.size(); i++) {
            String herrDirektor = directors.get(indices.get(i));
            System.out.println(herrDirektor);
        }
        
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        //Empty ArrayList of type Rater
        ArrayList<Rater> output = new ArrayList<Rater>();
        
        //Get the file of interest
        String file = "data/" + filename;
        FileResource fr = new FileResource(file);
        
        //Get the CSV parser of the file resource
        CSVParser parser = fr.getCSVParser();
        
        //for each record (row), put info into rater object
        for (CSVRecord record : parser) {
            //Use this as index for previous rater ID (genius)
            int prevIdx = output.size() - 1;
            //Get rater ID of ith row as an integer
            int currID = Integer.parseInt(record.get("rater_id"));
            //Get previous rater ID
            int prevID;
            if (output.size() == 0) {
                //To prevent duplicate of first rater
                prevID = -1;
            } else {
                prevID = Integer.parseInt(output.get(prevIdx).getID());
            }

            
            //If current row raterID is equal to previous raterID
            if (currID == prevID) {
                //Then just add rating to previous Rater without creating a new Rater object
                output.get(prevIdx).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            } else {
                //If Current ID and previous ID DON'T match, then this means that
                //We have come to next rater, so we can make a new Rater object
                Rater newRater = new Rater(record.get("rater_id"));
                newRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                
                //Add created rater object to 'output' array list
                output.add(newRater);
            }
            
            
        }
        
        System.out.println("Size of output: " + output.size());
        
        return output;
        
    }
    
    public void testLoadRaters() {
        //Call the method loadRaters on the file ratings_short.csv
        //ArrayList<Rater> raters = loadRaters("ratings_short.csv");
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        
        //Hashset for non repeating rater IDs
        HashSet<String> raterIDs = new HashSet<String>();
        
        //ArrayList for keeping track of number of ratings
        ArrayList<Integer> numRatingsList = new ArrayList<Integer>();
        
        //for each rater
        for (int i = 0; i < raters.size(); i++) {
            String raterID = raters.get(i).getID();
            
            //put raterID into hashset to avoid repeats
            if (raterIDs.add(raterID)) {
                //If added to hashset, update numRatings with the number of ratings for current raterID
                numRatingsList.add(raters.get(i).numRatings());
            }
            
            
            
            //print the rater's ID
            //System.out.print("Rater ID: " + raterID);
            //print the number of ratings they did
            //System.out.println(". Number of ratings: " + raters.get(i).numRatings());
            
            //Get all movie IDs rated by the current rater
            /* Comment out because for large size of raters, it is unneccessary to print all of them
            ArrayList<String> movieIDs = raters.get(i).getItemsRated();

             
            for (int j = 0; j < movieIDs.size(); j++) {
                //print movie IDs and corresponding ratings
                System.out.println("For Movie with ID: " + movieIDs.get(j) + " the rating by rater " + raterID +
                                   " is " + raters.get(i).getRating(movieIDs.get(j)));
                
            }
            System.out.println("\n");
            */
            
        }
        
        System.out.println("In total, there are " + raterIDs.size() + " raters in the file.");
        
        //Find number of ratings for a particular rater specified in the code.
        
        int rater = 193;
        
        int ratings = numRatingsList.get(rater-1); //Gets numRatings stored in arraylist
        
        System.out.println("Number of ratings from rater " + rater + " is " + ratings);
        
        /*
        System.out.println("Content inside raterIDs hash set:");
        for (String s : raterIDs) {
            System.out.println(s);
        }
        */
        System.out.println("Amount of IDs from file: " + raterIDs.size());
        
        /*
        System.out.println("Content inside numRatingsList ArrayList:");
        for (int k = 0; k < numRatingsList.size(); k++) {
            System.out.println(numRatingsList.get(k));
        }
        */
        System.out.println("Amount of ID ratings from file: " + numRatingsList.size());
        
        
        //Add code to find the maximum number of ratings by any rater
        int max = 0;
        for (int i = 0; i < raters.size(); i++) {
            if (raters.get(i).numRatings() > max) {
                max = raters.get(i).numRatings();
            }
        }
        
        System.out.println("The maximum number of ratings by any Rater is: " + max);
        
        //Now that we have the max number in raters
        //We must store indices that have the max number into an ArrayList of integers called indices
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        for (int i = 0; i < raters.size(); i++) {
            if (raters.get(i).numRatings() == max) {
                indices.add(i);
            }
            
        }
        
        //Now, print out the name of each rater that has a max number of movies rated in the file
        System.out.println("Raters that have " + max + " number of ratings are as follows:");
        for (int i = 0; i < indices.size(); i++) {
            System.out.println("Rater " + raters.get(indices.get(i)).getID());
        }
        //Add code to find the number of ratings a particular movie has
        
        //ALSO
        //Add code to determine how many different movies have been rated by all these raters.
        //We can use a hashset to get unique strings for movie IDs.
        String testMovie = "1798709";
        HashSet<String> movieIds = new HashSet<String>();
        
        int counter = 0;
        //For each rater
        for (int i = 0; i < raters.size(); i++) {
            //Get arraylist of movie IDs
            ArrayList<String> ratedMovies = raters.get(i).getItemsRated();
            //System.out.println("On rater " + raters.get(i).getID());
            //Loop through ratedMovies
            for (int j = 0; j < ratedMovies.size(); j++) {
                //Get rated movie
                String ratedMovie = ratedMovies.get(j);
                
                //Add ratedMovie to hash set to find how many different movies have been rated in the file
                movieIds.add(ratedMovie);
                //Check if current ratedMovies ID matches testMovie ID
                if (ratedMovie.equals(testMovie)) {
                    //If so, increment counter by one
                    counter++;
                }
                //If not, do nothing
            }
        }
        System.out.println("The movie " + "\"" + testMovie + "\"" + " was rated by " + counter + " raters.");
        //1798709 should have 4 raters in ratings_short.csv
        
        System.out.println("In total, there were " + movieIds.size() + " movies rated");
        //There should be 4 movies rated in ratings_short.csv
       
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
