package cz.vacul.tamz_01_zodiac;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    SharedPreferences preferences;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences  = PreferenceManager.getDefaultSharedPreferences(this);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        final TextView textView = (TextView) findViewById(R.id.textView2);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Calendar calendar = Calendar.getInstance();
        int year = preferences.getInt("bday_y", calendar.get(Calendar.YEAR));
        int month = preferences.getInt("bday_m", calendar.get(Calendar.MONTH));
        int day = preferences.getInt("bday_d", calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int y, int m, int d) {
                ZodiacSigns.setSign(new Date(y, m, d), textView, imageView);
            }
        });
        ZodiacSigns.setSign(new Date(year, month, day), textView, imageView);
        findViewById(R.id.root).setBackgroundResource(preferences.getInt("background", R.drawable.constellationstar));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignActivity.class);
                i.putExtra("sign", ZodiacSigns.getSign(new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth())));
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.action_settings:
                startActivityForResult(new Intent(this, SettingsActivity.class), 666);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 666 && resultCode == RESULT_OK){
            preferences.edit().putInt("background", data.getIntExtra("drawable", R.drawable.constellationstar)).apply();
            findViewById(R.id.root).setBackgroundResource(data.getIntExtra("drawable", R.drawable.constellationstar));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("bday_d", datePicker.getDayOfMonth());
        editor.putInt("bday_m", datePicker.getMonth());
        editor.putInt("bday_y", datePicker.getYear());
        editor.apply();
    }
}
