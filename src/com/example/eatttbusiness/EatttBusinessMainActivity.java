package com.example.eatttbusiness;


import org.json.JSONException;
import org.json.JSONObject;
import com.example.library.*;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EatttBusinessMainActivity extends Activity {
	
	private static String url= "http://demo.eattt.com/api/v1/user/rest_login/";
	JSONObject jsonobject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eattt_business_main);
        Button login_button = (Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText user_nameEditText=(EditText) findViewById(R.id.username);
				EditText user_passwordEditText =(EditText) findViewById(R.id.userpassword);
				String user_name= user_nameEditText.getText().toString();
				String user_password=user_passwordEditText.getText().toString();
				new RetrieveUserDetails().execute(user_name,user_password);
				
		}
			
		});//end of on click listener
    }
    
    class RetrieveUserDetails extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... params) {
			String user_name=params[0];
			String user_password=params[1];
			jsonobject=JsonPostLogin.getJSONfromURL(url, user_name,user_password);
			System.out.println("Json object is "+ jsonobject);
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			System.out.println("printing some thing");
			String json_result="result";
			String json_role="role";
			try {
				json_result=jsonobject.getString("success");
				
				System.out.println("role is "+json_result);
				
				if(json_result.equalsIgnoreCase("true")){
					json_role=jsonobject.getString("role");
					Intent intent=new Intent(getApplicationContext(),ManagerActivity.class);
					intent.putExtra("TAG_ROLE", json_role);
					intent.putExtra("TAG_USERNAME",jsonobject.getString("username") );
					startActivity(intent);
				}else{
					Toast.makeText(getApplicationContext(), jsonobject.getString("reason"), Toast.LENGTH_SHORT).show();					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			super.onPostExecute(result);
			
		}
    }
}
