


public class StompUtils {
    @SuppressWarnings({"ResultOfMethodCallIgnored", "CheckResult"})
    public static void lifecycle(StompClient stompClient) {
        stompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                   // Log.d(TAG, "Stomp connection opened");
                    Log.d("stomp","Stomp connection opened");

                    break;

                case ERROR:
                  //  Log.e(TAG, "Error", lifecycleEvent.getException());
                    Log.d("Error",lifecycleEvent.getException().toString());

                    break;

                case CLOSED:
                   // Log.d(TAG, "Stomp connection closed");
                    Log.d("stomp closed","Stomp connection closed");

                    break;
            }
        });
    }
}
