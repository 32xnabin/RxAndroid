package naveeraj.com.np;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface HerokuService {

    @GET("todos")
    Observable<List<Todo>> getRestuants();

}
