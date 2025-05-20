package io.cornerlabeltextviewsample;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.cornerlabeltextviewsample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        CornerLabelTextview stv = (CornerLabelTextview) findViewById(R.id.txt1);
//
//        stv.setText("CornerLabel")
//                .setTextColor(Color.WHITE)
//                .setCornerLabelBackgroundColor(Color.BLACK)
//                .setTextSize(18)
//                .setCornerLabelLength(50)
//                .setMode(CornerLabelTextview.MODE_LEFT);
        
    }
}