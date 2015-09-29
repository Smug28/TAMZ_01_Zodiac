package cz.vacul.tamz_01_zodiac;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SignActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) findViewById(R.id.signTitle);
        TextView desc = (TextView) findViewById(R.id.signDesc);
        int sign = getIntent().getIntExtra("sign", 0);
        title.setText(ZodiacSigns.values()[sign].name());
        desc.setText(getResources().getStringArray(R.array.sign_desc)[sign]);
    }

}
