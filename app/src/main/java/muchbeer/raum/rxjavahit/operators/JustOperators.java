package muchbeer.raum.rxjavahit.operators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import muchbeer.raum.rxjavahit.R;

public class JustOperators extends AppCompatActivity {

    private final static String TAG = "myApp";
    //  private String[] greetings = {"Hello A","Hello B","Hello C"};
    private Observable<String> myObservable;
    private DisposableObserver<String> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_operators);

        myObservable = Observable.just("Hello A","Hello B","Hello C");
       // myObservable = Observable.just(greetings);

        compositeDisposable.add(
                myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObsver())

        );
    }

    private DisposableObserver getObsver() {

        myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, " onNext invoked "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, " onError invoked");
            }

            @Override
            public void onComplete() {

                Log.d(TAG, " onComplete invoked");
            }
        };
        return myObserver;
    }
}
