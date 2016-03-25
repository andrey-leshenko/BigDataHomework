package univ.bigdata.course.providers;

import univ.bigdata.course.movie.MovieReview;

import java.io.*;
import java.time.Instant;
import java.util.*;

/**
 * Iterates over a file with movie reviews.
 *
 * The file consists of 7 tab separated fields on each line, as follows:
 *
 * <p>
 * <ul>
 * <li><code>product/productId  </code> - Id of the product reviewed
 * <li><code>review/userId		</code> - Id of the user who left the review
 * <li><code>review/profileName	</code> - Profile name of the user
 * <li><code>review/helpfulness </code> - Helpfulness written as <em>a/b</em>,
 *      where <em>a</em> out of <em>b</em> users found this review helpful
 * <li><code>review/score		</code> - Rating score written as a decimal
 * <li><code>review/time		</code> - Timestamp of review submission in milliseconds
 * <li><code>review/summary		</code> - Short summary of the review
 * <li><code>review/text		</code> - Full review text
 * </ul>
 */
public class MovieReviewFileIterator implements Iterator<MovieReview> {
    private final BufferedReader reader;
    private MovieReview nextReview;

    public MovieReviewFileIterator(File file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        nextReview = readNextReview(reader);
    }

    @Override
    public boolean hasNext() {
        return nextReview != null;
    }

    @Override
    public MovieReview next() {
        if (nextReview == null)
            throw new NoSuchElementException();

        MovieReview current = nextReview;
        nextReview = readNextReview(reader);

        return current;
    }

    private static MovieReview readNextReview(BufferedReader br) {
        String nextLine = null;

        try {
            nextLine = br.readLine();
        }
        catch (IOException ex) {
            System.err.println("IOException while iterating through movie file: " + ex);
        }

        if (nextLine == null)
            return null;

        return parseReview(nextLine);
    }

    private static MovieReview parseReview(String line) {
        final String[] fieldNames = {
                "product/productId",
                "review/userId",
                "review/profileName",
                "review/helpfulness",
                "review/score",
                "review/time",
                "review/summary",
                "review/text"
        };

        try {
            String[] fields = line.split("\t");

            if (fields.length != fieldNames.length)
                throw new Exception();

            for (int i = 0; i < fields.length; i++) {
                String[] fieldParts = fields[i].split(":", 2);

                if (fieldParts.length != 2 || !fieldParts[0].equals(fieldNames[i]))
                    throw new Exception();

                fields[i] = fieldParts[1].trim();
            }

            final String productId      = fields[0];
            final String userId         = fields[1];
            final String profileName    = fields[2];
            final String helpfulness    = fields[3];
            final double score          = Double.parseDouble(fields[4]);
            final Instant time          = Instant.ofEpochMilli(Long.parseLong(fields[5]));
            final String summary        = fields[6];
            final String text           = fields[7];

            final String[] votes = helpfulness.split("/", 2);
            if (votes.length != 2)
                throw new Exception();

            final int helpfulVotes  = Integer.parseInt(votes[0]);
            final int totalVotes    = Integer.parseInt(votes[1]);

            return new MovieReview(productId, userId, profileName, helpfulVotes, totalVotes, score, time, summary, text);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Movie review not in correct format: `" + line + "`");
        }
    }
}
