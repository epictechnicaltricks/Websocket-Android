
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class MainActivity extends AppCompatActivity {
   
    Button send;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ws_layout);
        send = findViewById(R.id.button1);
      
		
	//code by Shubhamjit (date : 27th july 2023)
	// library credit : naiksoftware
		
	
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
			
                    Log.d("wsTAG",jsonObject.getString("message") + "\n");
			
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        });


        // send button on click
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
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
