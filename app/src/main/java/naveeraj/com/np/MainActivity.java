package naveeraj.com.np;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TodoAdapter adapter = new TodoAdapter(this);
    private Subscription subscription;
    ProgressBar progressBar;
    ListView todosList;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar) findViewById(R.id.pb);
        todosList=(ListView) findViewById(R.id.todosList);



        getRestaurants();
    }

    @Override protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }






    private void getRestaurants() {
        progressBar.setIndeterminate(true);


        subscription = HerokuClient.getInstance()
                .getTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Todo>>() {
                    @Override public void onCompleted() {

                        Log.d(TAG, "Doneee------->");
                        progressBar.setIndeterminate(false);
                        progressBar.setProgress(100);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "Errorr------->");
                    }

                    @Override public void onNext(List<Todo> todos) {
                        Log.d(TAG, "TTTTTT------->"+todos.size());
                        Log.d(TAG, "TTTTTT------->"+todos.get(0).getTitle());
                        Log.d(TAG, "TTTTTT------->"+todos.get(0).getDescription());
                        Log.d(TAG, "TTTTTT------->"+todos.get(0).getStartDate());
                        Log.d(TAG, "TTTTTT------->"+todos.get(0).getEndDate());
                        Log.d(TAG, "TTTTTT------->"+todos.get(0).getCreatedAt());
                        Log.d(TAG, "TTTTTT------->"+todos.get(0).getUpdatedAt());
                        adapter.setTodos(todos);
                        todosList.setAdapter(adapter);
                    }
                });
    }
}
