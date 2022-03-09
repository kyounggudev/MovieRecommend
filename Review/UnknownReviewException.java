package Review;
import java.lang.Exception;

public class UnknownReviewException extends Exception{
	UnknownReviewException(String pMessage){
		super(pMessage);
	}
}
