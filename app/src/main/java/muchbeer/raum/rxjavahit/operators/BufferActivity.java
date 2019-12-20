package muchbeer.raum.rxjavahit.operators;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import muchbeer.raum.rxjavahit.R;

public class BufferActivity extends AppCompatActivity {
    private final static String TAG = BufferActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Integer> myObservable = Observable.range(1, 20);

        myObservable.observeOn(AndroidSchedulers.mainThread())
                .buffer(4)
                .subscribe(new Observer<List<Integer>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {
                        Log.i(TAG," Came to onNext ");

                        for (Integer i:integers) {
                            Log.i(TAG, " int value is  "+i);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG," Came to onComplete ");
                    }
                });
    }
}
