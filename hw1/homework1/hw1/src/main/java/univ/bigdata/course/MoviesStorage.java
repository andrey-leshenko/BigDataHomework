package univ.bigdata.course;

import javafx.util.Pair;
import univ.bigdata.course.movie.Movie;
import univ.bigdata.course.movie.MovieReview;

import java.util.*;

/**
 * Main class which capable to keep all information regarding movies review.
 * Has to implements all methods from @{@link IMoviesStorage} interface.
 * Also presents functionality to answer different user queries, such as:
 * <p>
 * 1. Total number of distinct movies reviewed.
 * 2. Total number of distinct users that produces the review.
 * 3. Average review score for all movies.
 * 4. Average review score per single movie.
 * 5. Most popular movie reviewed by at least "K" unique users
 * 6. Word count for movie review, select top "K" words
 * 7. K most helpful users
 */
public class MoviesStorage implements IMoviesStorage {
    private final List<MovieReview> reviews;

    public MoviesStorage(Iterator<MovieReview> reviewIterator) {
        List<MovieReview> reviewList = new ArrayList<>();
        // NOTE (Andrey): We assume all the reviews will fit in memory
        while (reviewIterator.hasNext())
            reviewList.add(reviewIterator.next());

        this.reviews = reviewList;

        // NOTE (Andrey): This is here temporarily for debugging purposes
        System.out.println("Got the following reviews:");
        reviews.forEach(System.out::println);
    }

    private HashMap<String, List<MovieReview> > getReviewsHashMap(){
        HashMap<String,List<MovieReview> > reviewsMap = new HashMap<>();
        for (MovieReview review : reviews){
            reviewsMap.get(review.getProductId()).add(review);
        }
        return reviewsMap;
    }

    @Override
    public double totalMoviesAverageScore() {
        HashMap<String, List<MovieReview> > reviewsMap = getReviewsHashMap();
        double totalSum = 0;
        for(String key : reviewsMap.keySet()){
            double movieSum = 0;
            for(MovieReview review : reviewsMap.get(key)){
                movieSum += review.getScore();
            }
            totalSum += movieSum/reviewsMap.get(key).size();
        }
        return totalSum/reviewsMap.size();
    }

    @Override
    public double totalMovieAverage(String productId) {
        double total = 0;
        int counter = 0;
        for (MovieReview review: reviews) {
            if(review.getProductId().equals(productId)){
                counter++;
                total += review.getScore();
            }
        }
        return total/counter;
    }

    @Override
    public List<Movie> getTopKMoviesAverage(long topK) {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public Movie movieWithHighestAverage() {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public List<Movie> getMoviesPercentile(double percentile) {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public String mostReviewedProduct() {
        HashMap<String,List<MovieReview> > reviewsMap = getReviewsHashMap();
        String bestProduct = new String();
        int maxReviews = 0;
        for(String key : reviewsMap.keySet()){
            if(reviewsMap.get(key).size() > maxReviews){
                maxReviews = reviewsMap.get(key).size();
                bestProduct = key;
            }
        }
        return bestProduct;
    }

    @Override
    public Map<String, Long> reviewCountPerMovieTopKMovies(int topK) {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public String mostPopularMovieReviewedByKUsers(int numOfUsers) {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public Map<String, Long> moviesReviewWordsCount(int topK) {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public Map<String, Long> topYMoviewsReviewTopXWordsCount(int topMovies, int topWords) {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public Map<String, Double> topKHelpfullUsers(int k) {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }

    @Override
    public long moviesCount() {
        throw new UnsupportedOperationException("You have to implement this method on your own.");
    }
}
