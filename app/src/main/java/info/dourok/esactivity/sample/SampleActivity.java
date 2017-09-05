package info.dourok.esactivity.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import info.dourok.esactivity.ActivityParameter;
import info.dourok.esactivity.BaseActivityBuilder;
import info.dourok.esactivity.EasyActivity;
import info.dourok.esactivity.RefManager;
import info.dourok.esactivity.Result;
import info.dourok.esactivity.ResultParameter;
import info.dourok.esactivity.TransmitType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;

@RequiresApi(api = Build.VERSION_CODES.N)
@EasyActivity

@Result(name = "wtf", parameters = {
    @ResultParameter(name = "ids", type = ArrayList.class)
})
public class SampleActivity extends AppCompatActivity {

  @ActivityParameter(key = "wtf", keep = true, transmit = TransmitType.Ref)
  String text;
  @ActivityParameter float f;
  @ActivityParameter(transmit = TransmitType.Ref) double d;
  @ActivityParameter Double dd;
  @ActivityParameter byte[] bytes;
  @ActivityParameter ArrayList<Integer> ids;
  @ActivityParameter(transmit = TransmitType.Ref) HashSet set;
  SampleActivityHelper mHelper = new SampleActivityHelper();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mHelper.inject(this);
    if (savedInstanceState != null) {
      mHelper.restore(this, savedInstanceState);
    }

    setContentView(R.layout.activity_sample);
    TextView tv = findViewById(R.id.text);
    tv.setText(text);
    tv.setOnClickListener(view -> {
      setResult(RESULT_OK);
      finish();
    });
  }

  @Override public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
    super.onSaveInstanceState(outState, outPersistentState);
    mHelper.save(this, outState);
  }

  @Result
  public void resultText(String text) {
    Intent intent =new Intent();
    intent.putExtra(text,"text");
  }

  public void forWtf(Consumer<ArrayList<? super Integer>> consumer) {
    Builder<SampleActivity> builder = new Builder<>(this);
    builder = builder.asIntent().asBuilder();
  }

  public static class Builder<A extends Activity> extends BaseActivityBuilder<Builder<A>, A> {
    public Builder(A activity) {
      super(activity);
      setIntent(new Intent(activity, SampleActivity.class));
    }

    public Builder text(String text) {
      Builder<A> b = asIntent().asBuilder();

      getIntent().putExtra("text", text);
      return this;
    }

    public void forText(Consumer<String> consumer) {
    }

    @Override public Builder self() {
      return this;
    }
  }
}
