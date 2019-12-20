package muchbeer.raum.rxjavahit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DisposableObserver extends AppCompatActivity {
    private final static String TAG="myApp";
    private String greeting="Hello From RxJava";

    //create an Observer
    private Observable<String> myObservable;

    //Listen to an observer
    private io.reactivex.observers.DisposableObserver<String> myObserver;
    private io.reactivex.observers.DisposableObserver<String> myObserver2;


    private TextView textView;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposable_observer);

        textView = findViewById(R.id.txt_disposable);
        myObservable = Observable.just(greeting);

       // myObservable.subscribeOn(Schedulers.io());
      //  myObservable.observeOn(AndroidSchedulers.mainThread());


        myObserver = new io.reactivex.observers.DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, " onNext invoked");
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, " onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, " onComplete invoked");
            }
        };

       // compositeDisposable.add(myObserver);
      //  myObservable.subscribe(myObserver);
        //this simple way
        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(myObserver)
        );

        myObserver2 = new io.reactivex.observers.DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.i(TAG, " onNext invoked");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, " onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, " onComplete invoked");
            }
        };

        compositeDisposable.add(myObserver2);
        myObservable.subscribe(myObserver2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    //    myObserver.dispose();
       // myObserver2.dispose();

        //destroy of Disposable
        compositeDisposable.clear();
    }
}
