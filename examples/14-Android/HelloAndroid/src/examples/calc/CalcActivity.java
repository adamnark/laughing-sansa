package examples.calc;

import examples.com.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalcActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button calcButton = (Button)findViewById(R.id.calcButton);
        
        calcButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				EditText arg1 = (EditText)findViewById(R.id.editText1);
				String arg1Str = arg1.getText().toString();
				int num1 = Integer.parseInt(arg1Str);
				
				EditText arg2 = (EditText)findViewById(R.id.editText2);
				String arg2Str = arg2.getText().toString();
				int num2 = Integer.parseInt(arg2Str);
				
				int result = num1 + num2;
				
				EditText resultTextView = (EditText)findViewById(R.id.resultEditText);
				resultTextView.setText(result+"");
				
				Toast.makeText(getApplicationContext(), "Result = " + result, 5000).show();
			}
		});
    }
}