package com.example.anoop.tcp_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String server_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText_IPaddr = (EditText) findViewById(R.id.ip_address_editText);
        Button button_submit = (Button) findViewById(R.id.submit_button);
        final TextView label = (TextView) findViewById(R.id.textView);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_IPaddr.getText() != null){
                    server_ip = editText_IPaddr.getText().toString();
                    //label.setText(server_ip);
                    if(!server_ip.isEmpty() && server_ip != null) {
                        Intent intent = new Intent(MainActivity.this, ScreenControls.class);
                        intent.putExtra("ip_addr", server_ip);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    public String get_server_ip(){
        return server_ip;
    }
}
