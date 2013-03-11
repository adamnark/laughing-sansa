package andoird.chat.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        Button loginButton = (Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				String host = ((EditText)findViewById(R.id.hostEditText)).getText().toString();
				final String username = ((EditText)findViewById(R.id.loginEditText)).getText().toString();

				//set hostname that will be used with all communication to the server
				HttpUtils.setHostname(host);
				
				Thread loginThread = new Thread(new Runnable() {
					public void run() {
						//send a login request to the /login servlet with a username parameter
						HttpUtils.sendHTTPRequest("/login", "username", username);
						
						//create a new Intent (it's like a message) that will load the ChatActivity class
						Intent myIntent = new Intent(getApplicationContext(), ChatActivity.class);
						//add additional data to be passed to the ChatActivity - it's going to be used to show the username in the title
						myIntent.putExtra("username", username);
						//send the message to Android and it will create and load the ChatActivity
						startActivity(myIntent);
					}
				});
				loginThread.start();
			}
		});
    }
}