package cz.vacul.tamz_01_zodiac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        findViewById(R.id.bg1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("drawable", R.drawable.constellationstar);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        findViewById(R.id.bg2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("drawable", R.drawable.bg2);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    @Override
    public boolean onNavigateUp() {
        setResult(RESULT_CANCELED);
        return super.onNavigateUp();
    }
}
