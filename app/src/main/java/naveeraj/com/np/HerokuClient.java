package naveeraj.com.np;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class HerokuClient {



    private static final String HEROKU_BASE_URL = "https://todo-boot.herokuapp.com/api/";

    private static HerokuClient instance;
     HerokuService service;

    public HerokuClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(HEROKU_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(HerokuService.class);
    }

    public static HerokuClient getInstance() {
        if (instance == null) {
            instance = new HerokuClient();
        }
        return instance;
    }

    public Observable<List<Todo>> getTodos() {
        return service.getRestuants();
    }
}
