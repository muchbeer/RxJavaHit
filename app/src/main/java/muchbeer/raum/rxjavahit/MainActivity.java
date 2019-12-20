package muchbeer.raum.rxjavahit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG="myApp";
    private String greeting="Hello From RxJava";

    //create an Observer
    private Observable<String> myObservable;

    //Listen to an observer
    private Observer<String> myObserver;

    private TextView textView;
    private Disposable disposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_observer);

        //our observable receives input which is the string and it uses the operator just
        myObservable = Observable.just(greeting);

        //When subscribeOn and pass scheduler then any data pass through the observable
        //will be passed to the schedulers.io()
        myObservable.subscribeOn(Schedulers.io());

        //UI Scheduler is happening here
        myObservable.observeOn(AndroidSchedulers.mainThread());

        //This observer subscribe to the observable and use the information passed through the observable
                myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG," onSubscribe invoked");

                disposable = d;
            }

            //This will be received here
            @Override
            public void onNext(String s) {

                Log.i(TAG," onNext invoked");

                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG," onError invoked");
            }

            @Override
            public void onComplete() {

                Log.i(TAG," onComplete invoked");
            }
        };

myObservable.subscribe(myObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
