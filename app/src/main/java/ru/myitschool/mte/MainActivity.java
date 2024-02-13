package ru.myitschool.mte;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.myitschool.mte.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int c =0;
    private TextView t;
    private boolean boo=false;
    private ActivityMainBinding binding;
    Button buttonstar = (Button) findViewById(R.id.button_start);
    Button buttonstop = (Button) findViewById(R.id.button_stop);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        t=findViewById(R.id.num_iteration);
        buttonstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boo= true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(boo){
                            c++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    t.setText(String.valueOf(c));
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }

                        }
                    }
                }).start();
            }
        });
        buttonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boo= false;
            }
        });

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        boo=false;
    }
}
