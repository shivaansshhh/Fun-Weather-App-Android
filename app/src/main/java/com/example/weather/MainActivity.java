package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog();

    }
    private void dialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(false);
        CardView button = dialog.findViewById(R.id.button);

        button.setOnClickListener(v -> dialogResult());

        dialog.show();


    }

    private int currentTextIndex = 0;
    private final String[] loadingTexts = {
            "Analyzing the clouds...",
            "Looking outside your window...",
            "Gathering last information..."
    };

    private void dialogResult() {
        Dialog result = new Dialog(this);
        result.setContentView(R.layout.dialog_result);
        TextView loading_text = result.findViewById(R.id.loading_text);
        loading_text.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading_text.setText(loadingTexts[currentTextIndex]);
                currentTextIndex++;
                if (currentTextIndex < loadingTexts.length) {
                    handler.postDelayed(this, 2000);
                } else {
                    messageDialog();
                    result.dismiss();
                }
            }
        }, 2000);

        result.show();
    }


    private void messageDialog(){
        Dialog messageDialog = new Dialog(this);
        messageDialog.setContentView(R.layout.message);
        messageDialog.show();

    }
}