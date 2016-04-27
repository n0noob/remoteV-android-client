package com.example.anoop.tcp_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ScreenControls extends AppCompatActivity {

    private static String TAG = "ScreenControls";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_controls);

        Button button_play = (Button) findViewById(R.id.play_button);
        Button button_pause = (Button) findViewById(R.id.pause_button);
        Button button_stop = (Button) findViewById(R.id.stop_button);
        Button button_ff = (Button) findViewById(R.id.ff_button);
        Button button_rev = (Button) findViewById(R.id.rev_button);
        String server_addr;

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            server_addr = extra.getString("ip_addr");

            final TCP_connection con1 = new TCP_connection(server_addr, 5000);

                button_play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "Play button pressed");
                        Thread t1 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //Open the connection
                                    con1.connect_now();

                                    con1.write_line("$PLAY/home/anoop/Downloads/Dexter - Season 1 - BRRip - x264 - AC3 5.1 -={SPARROW}=-/Dexter S01 E06 - BRRip - x264 - AC3 5.1 -={SPARROW}=-.mkv");
                                    //Close the connection
                                    con1.close_connection();
                                } catch (Exception e) {
                                    Log.e(TAG, "Exception caught" + Log.getStackTraceString(e));
                                }
                            }
                        });
                        t1.start();
                    }
                });
        }
    }
}