package univ.bigdata.course.movie;

import java.time.Instant;

/**
 * Represents an immutable movie review.
 */
public class MovieReview {

    private final String    productId;
    private final String    userId;
    private final String    profileName;
    private final int       votesHelpful;
    private final int       votesTotal;
    private final double    score;
    private final Instant   time;
    private final String    summary;
    private final String    text;

    /**
     * Constructs a new movie review.
     *
     * @param productId     ID of the product
     * @param userId        ID of the user who left the review
     * @param profileName   Profile name of the user who left the review
     * @param votesHelpful  Number of users who rated this review as helpful
     * @param votesTotal    Number of users who rated this review
     * @param score         The rating score given in this review
     * @param time          The time at which this review was submitted
     * @param summary       Short summary of the review
     * @param text          Full review text
     */
    public MovieReview(
            String  productId,
            String  userId,
            String  profileName,
            int     votesHelpful,
            int     votesTotal,
            double  score,
            Instant time,
            String  summary,
            String  text
    ) {
        this.productId      = productId;
        this .userId        = userId;
        this.profileName    = profileName;
        this.votesHelpful   = votesHelpful;
        this.votesTotal     = votesTotal;
        this.score          = score;
        this.time           = time;
        this.summary        = summary;
        this.text           = text;
    }

    public String   getProductId    () { return productId;      }
    public String   getUserId       () { return userId;         }
    public String   getProfileName  () { return profileName;    }
    public int      getVotesHelpful () { return votesHelpful;   }
    public int      getVotesTotal   () { return votesTotal;     }
    public Instant  getTime         () { return time;           }
    public String   getSummary      () { return summary;        }
    public String   getText         () { return text;           }

    @Override
    public String toString() {
        return "MovieReview{" +
                "productId="        + productId +
                ", userId='"        + userId + '\'' +
                ", profileName='"   + profileName + '\'' +
                ", helpfulness='"   + votesHelpful + '/' + votesTotal + '\'' +
                ", score='"         + score + '\'' +
                ", timestamp="      + time +
                ", summary='"       + summary + '\'' +
                ", review='"        + text + '\'' +
                '}';
    }
}
