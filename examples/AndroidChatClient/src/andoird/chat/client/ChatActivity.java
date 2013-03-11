package andoird.chat.client;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.TextView;


public class ChatActivity extends Activity {

	//EOL means End Of Line
	private static final String EOL = System.getProperty("line.separator");

	//allows non-"edt" thread to be re-inserted into the "edt" queue
	//we need to use this class since there is no SwingUtilities in Android
	final Handler uiThreadCallback = new Handler();

	private Timer timer;
	int chatVersion = 0;
	private String username;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
		
		//get the username value that was send from the LoginActivity and set it in its label
		this.username = getIntent().getExtras().getString("username"); 
		TextView chatTitleTextView = (TextView) findViewById(R.id.chatTitleTextView);
		chatTitleTextView.setText("You are: " + username);
		
		//when the user clicks the send button send the content of the message to the server
		findViewById(R.id.sendButton).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				sendMessage();
			}
		});
		//when the user clicks the enter key send the content of the message to the server
		findViewById(R.id.messageEditText).setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_ENTER) {
					sendMessage();
					return true;
				}
				return false;
			}
		});
		
		//start a new timer that will run a refresh task every second to check the current server chat version
		timer = new Timer();
		//start timer to check the server chat version every second
		timer.scheduleAtFixedRate(new RefreshChatTask(), 1000, 1000);
	}
	
	protected void sendMessage() {
		//get the content of the message text field and if it is not empty, send it to the server
		EditText messageEditText = (EditText)findViewById(R.id.messageEditText);
		if (messageEditText.length() > 0) {
			String message = messageEditText.getText().toString();
			if (message.endsWith(EOL)) {
				message = message.replace(EOL, "");
			}
			final String finalMessage = message;
			Thread sendMessageThread = new Thread(new Runnable() {
				public void run() {
					HttpUtils.sendHTTPRequest("/chat", "userstring", finalMessage);
				}
			});
			sendMessageThread.start();
			messageEditText.setText("");
		}
	}

	@Override
    protected void onDestroy() {
    	super.onDestroy();
    	timer.cancel();
    }
	
	private void updateChatContent(String chatContent) {
		EditText chatEditText = (EditText) findViewById(R.id.chatEditText);
		chatEditText.append(chatContent);
	}
	

	
	class RefreshChatTask extends TimerTask {
		@Override
		public void run() {
			try {
				//send a request to get the latest chat version (also send the current chat version so if there are changes, the chat delta (strings that were added)
				//will be returned as well
				String response = HttpUtils.sendHTTPRequest("/chat", "chatversion", chatVersion+"");
				if (response != null && !response.isEmpty()) {
					//wrap the response (which is a string that contains JSON) in a JSON Object 
					JSONObject jsonObject = new JSONObject(response);
					//read specific fields from the JSON object
					int serverVersion = jsonObject.getInt("version");
					if (serverVersion != chatVersion) {
						chatVersion = serverVersion;
						final String latestChat = jsonObject.getString("chat");
						//update the response in the Android EDT - this is like SwingUtilities.invokeLater(new Runnable()... 
						uiThreadCallback.post(new Runnable() {
							public void run() {
								updateChatContent(latestChat);
							}
						});
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception exception) {
				//something really went wrong here...
			}
		}
    }
}
