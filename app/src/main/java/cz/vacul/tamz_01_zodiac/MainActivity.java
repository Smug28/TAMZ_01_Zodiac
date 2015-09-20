package cz.vacul.tamz_01_zodiac;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
        final TextView textView = (TextView) findViewById(R.id.textView2);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Calendar calendar = Calendar.getInstance();
        dp.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int y, int m, int d) {
                ZodiacSigns.setSign(new Date(y, m, d), textView, imageView);
            }
        });
        ZodiacSigns.setSign(calendar.getTime(), textView, imageView);
    }
}
