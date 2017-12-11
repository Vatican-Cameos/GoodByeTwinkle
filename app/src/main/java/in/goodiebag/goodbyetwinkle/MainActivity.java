package in.goodiebag.goodbyetwinkle;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etSSIDPassword;
    Button tvLowBudgetButton;
    String ssidAndPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSSIDPassword = findViewById(R.id.etSSIDPassword);
        tvLowBudgetButton = findViewById(R.id.tvButton);

        tvLowBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ssidAndPassword = etSSIDPassword.getText().toString().trim();
                checkPermission();
            }
        });

    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" +
                        getPackageName()));
                startActivityForResult(intent, 200);

            }else{
                WifiAccessManager.setWifiApState(MainActivity.this, true, ssidAndPassword);
            }
        }else{
            WifiAccessManager.setWifiApState(MainActivity.this, true, ssidAndPassword);
        }
    }
}