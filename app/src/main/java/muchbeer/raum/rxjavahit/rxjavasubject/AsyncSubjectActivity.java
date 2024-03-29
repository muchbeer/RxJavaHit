package muchbeer.raum.rxjavahit.rxjavasubject;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import muchbeer.raum.rxjavahit.R;

public class AsyncSubjectActivity extends AppCompatActivity {

    private final static String TAG = AsyncSubjectActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asyncSubjectDemo1();
        //asyncSubjectDemo2();
        //behaviorSubjectDemo1();
        //behaviorSubjectDemo2();
        //publishSubjectDemo1();
        //publishSubjectDemo2();
        //replaySubjectDemo1();
        replaySubjectDemo2();

    }

    //emit the last item of the observable
    void asyncSubjectDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        observable.subscribe(asyncSubject);

        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());
    }

//Async Subject : - Only emits the last value of the Observable .
    void asyncSubjectDemo2() {


        AsyncSubject<String> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(getFirstObserver());

        asyncSubject.onNext("JAVA");
        asyncSubject.onNext("KOTLIN");
        asyncSubject.onNext("XML");

        asyncSubject.subscribe(getSecondObserver());

        asyncSubject.onNext("JSON");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());


    }

//subject Emit recently emited item and all the subsequenty item
    void behaviorSubjectDemo1() {
        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        observable.subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());


    }


    void behaviorSubjectDemo2() {


        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.subscribe(getFirstObserver());

        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("KOTLIN");
        behaviorSubject.onNext("XML");

        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("JSON");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());


    }

//emit all item at the time of the subscribe to it
    void publishSubjectDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        PublishSubject<String> publishSubject = PublishSubject.create();

        observable.subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecondObserver());
        publishSubject.subscribe(getThirdObserver());


    }

    void publishSubjectDemo2() {


        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext("JAVA");
        publishSubject.onNext("KOTLIN");
        publishSubject.onNext("XML");

        publishSubject.subscribe(getSecondObserver());

        publishSubject.onNext("JSON");
        publishSubject.onComplete();

        publishSubject.subscribe(getThirdObserver());


    }

    //Emit all items that were item by the source
    void replaySubjectDemo1() {

        Observable<String> observable = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        ReplaySubject<String> replaySubject = ReplaySubject.create();

        observable.subscribe(replaySubject);

        replaySubject.subscribe(getFirstObserver());
        replaySubject.subscribe(getSecondObserver());
        replaySubject.subscribe(getThirdObserver());


    }


    void replaySubjectDemo2() {


        ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject.subscribe(getFirstObserver());

        replaySubject.onNext("JAVA");
        replaySubject.onNext("KOTLIN");
        replaySubject.onNext("XML");

        replaySubject.subscribe(getSecondObserver());

        replaySubject.onNext("JSON");
        replaySubject.onComplete();

        replaySubject.subscribe(getThirdObserver());


    }


    private Observer<String> getFirstObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {


                Log.i(TAG, " First Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {

                Log.i(TAG, " First Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " First Observer onError ");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " First Observer onComplete ");

            }
        };

        return observer;
    }

    private Observer<String> getSecondObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, " Second Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, " Second Observer Received " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, " Second Observer onError ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, " Second Observer onComplete ");

            }
        };

        return observer;
    }

    private Observer<String> getThirdObserver() {

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, " Third Observer onSubscribe ");
            }

            @Override
            public void onNext(String s) {

                Log.i(TAG, " Third Observer Received " + s);

            }

            @Override
            public void onError(Throwable e) {

                Log.i(TAG, " Third Observer onError ");
            }

            @Override
            public void onComplete() {

                Log.i(TAG, " Third Observer onComplete ");

            }
        };

        return observer;
    }


}
