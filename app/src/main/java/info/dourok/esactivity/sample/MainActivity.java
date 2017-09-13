package info.dourok.esactivity.sample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import info.dourok.esactivity.BuilderUtils;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @RequiresApi(api = Build.VERSION_CODES.N) @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> {
          SampleActivityBuilder<MainActivity> builder =
              BuilderUtils.smallCreate(this, SampleActivity.class);
          builder
              //SampleActivityBuilder.create(this)
              .forCancel(data -> {
                Snackbar.make(view, "You're Cancel", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
              })
              .forText((s) -> {
                Toast.makeText(this, "" + s.get(0), Toast.LENGTH_SHORT).show();
              })
              .forAbcd((s, s2, s3, s4) -> {

              })
              .text("hahah")
              .asIntent()
              .asBuilder()
              .start();
        });
  }

  public void handleOk(Intent data) {
    Log.d(TAG, toString());
    Snackbar.make(findViewById(R.id.fab), "You're Ok", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
