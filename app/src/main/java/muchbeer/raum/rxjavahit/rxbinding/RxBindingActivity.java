package muchbeer.raum.rxjavahit.rxbinding;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Unit;
import muchbeer.raum.rxjavahit.R;

public class RxBindingActivity extends AppCompatActivity {

    private EditText inputText;
    private TextView viewText;
    private Button clearButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_operator);


        inputText = findViewById(R.id.edtView);
        viewText = findViewById(R.id.txtView);
        clearButton = findViewById(R.id.button);

/*        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

//rxjava using binding
Disposable disposable = RxTextView.textChanges(inputText)
                        .subscribe(new Consumer<CharSequence>() {
                            @Override
                            public void accept(CharSequence charSequence) throws Exception {
                                viewText.setText(charSequence);
                            }
                        });

Disposable disposableButton = RxView.clicks(clearButton)
                                .subscribe(new Consumer<Unit>() {
                                    @Override
                                    public void accept(Unit unit) throws Exception {
                                            inputText.setText("");
                                            viewText.setText("");
                                    }
                                });
    }
}
