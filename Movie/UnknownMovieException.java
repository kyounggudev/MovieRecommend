package Movie;
import java.lang.Exception;

public class UnknownMovieException extends Exception{
	UnknownMovieException(String pMessage){
		super(pMessage);
	}
}
