package vn.iotstar.services.implement;

import java.util.List;

import vn.iotstar.dao.IReviewDao;
import vn.iotstar.dao.implement.ReviewDao;
import vn.iotstar.entity.Review;
import vn.iotstar.services.IReviewService;

public class ReviewService implements IReviewService {
	  private IReviewDao reviewDao;

	    public ReviewService() {
	        this.reviewDao = new ReviewDao();
	    }

	    @Override
	    public void saveReview(Review review) {
	    	reviewDao.saveReview(review);
	    }
}