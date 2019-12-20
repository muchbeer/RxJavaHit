package muchbeer.raum.rxjavahit.filteroperator;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import muchbeer.raum.rxjavahit.R;

public class DistinctOperatorActivity extends AppCompatActivity {
    private final static String TAG = DistinctOperatorActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operator);

        Observable<Integer> numbersObservable = Observable.just(1,2, 3, 7, 5, 3, 5, 5, 4, 4);

        numbersObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG,"came to onNext "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        Log.i(TAG,"came to onComplete ");
                    }
                });


    }
}
