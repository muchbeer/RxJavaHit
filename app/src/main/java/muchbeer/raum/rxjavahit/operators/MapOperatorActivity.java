package muchbeer.raum.rxjavahit.operators;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import muchbeer.raum.rxjavahit.R;
import muchbeer.raum.rxjavahit.model.Student;

public class MapOperatorActivity extends AppCompatActivity {

    private final static String TAG = MapOperatorActivity.class.getSimpleName();
    private Observable<Student> myObservable;
    private DisposableObserver<Student> myObserver;


    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObservable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter) throws Exception {
                ArrayList<Student> students = getStudents();

                for (Student observeStudent: students) {
                    emitter.onNext(observeStudent);
                }
            }
        });

        compositeDisposable.add(myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //first input of the function is Input (Student)
                //second Student function is the output depend on your prefences
              /*  .map(new Function<Student, Student>() {
                    @Override
                    public Student apply(Student student) throws Exception {

                        student.setName(student.getName().toUpperCase());

                        return student;
                    }
                })*/

              .flatMap(new Function<Student, ObservableSource<Student>>() {
                  @Override
                  public ObservableSource<Student> apply(Student student) throws Exception {

                      Student student1=new Student();
                      student1.setName(student.getName());

                      Student student2=new Student();
                      student2.setName("New member "+student.getName());

                      student.setName(student.getName().toUpperCase());
                      return Observable.just(student,student1,student2);

                  }
              })

                .subscribeWith(getObserver())
        );
    }

private DisposableObserver getObserver() {

        myObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(Student student) {
                Log.i(TAG,  student.getName());
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
        return myObserver;
}
    private ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();

        Student student1 = new Student();
        student1.setName(" student 1");
        student1.setEmail(" student1@gmail.com ");
        student1.setAge(27);
        students.add(student1);

        Student student2 = new Student();
        student2.setName(" student 2");
        student2.setEmail(" student2@gmail.com ");
        student2.setAge(20);
        students.add(student2);

        Student student3 = new Student();
        student3.setName(" student 3");
        student3.setEmail(" student3@gmail.com ");
        student3.setAge(20);
        students.add(student3);

        Student student4 = new Student();
        student4.setName(" student 4");
        student4.setEmail(" student4@gmail.com ");
        student4.setAge(20);
        students.add(student4);

        Student student5 = new Student();
        student5.setName(" student 5");
        student5.setEmail(" student5@gmail.com ");
        student5.setAge(20);
        students.add(student5);

        return students;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }


}
