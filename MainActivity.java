package com.terminus.chatroom;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.terminus.chatroom.Const.Const;

import org.json.JSONException;
import org.json.JSONObject;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class MainActivity extends AppCompatActivity {
    String textfield;
    EditText messageBox,showText;
    Button send;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webb);
        send = findViewById(R.id.button352);
        messageBox = findViewById(R.id.writemessage);
        showText=findViewById(R.id.showText);
		
		//code by shubhamjit (date : 27th july 2023)
		
		// Connection to ws server
        StompClient stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://your_ws_or_wss_url.com");
        Toast.makeText(this, "Start connecting to server", Toast.LENGTH_SHORT).show();
        stompClient.connect();
		
		// lifecycle on StompUtils.java file
        StompUtils.lifecycle(stompClient);
		
      


         // Subscribe to topic
        stompClient.topic("/root_path/your-path/your-id").subscribe(stompMessage -> {
            JSONObject jsonObject = new JSONObject(stompMessage.getPayload());
            Log.i(Const.TAG, "Receive: " + stompMessage.getPayload());
            runOnUiThread(() -> {
                try {
                    showText.append(jsonObject.getString("message") + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textfield = messageBox.getText().toString();
              

                try {
                  
				   // send message to server path 
                    stompClient.send("/path/path_name", "your message").subscribe();
					
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}