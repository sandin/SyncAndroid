package com.lds.syncandroid;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.lds.syncpc.client.SyncClient;

public class MainActivity extends Activity {
	
	// UI
	private ProgressDialog progressDialog;
	private Button syncBtn;
	
	// Tasks
	private SyncTask syncTask;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initViews();
    }
    
    private void initViews() {
    	syncBtn = (Button) findViewById(R.id.sync_btn);
    	syncBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 syncTask = new SyncTask();
			     syncTask.execute();
			}
		});
		
	}


	private class SyncTask extends AsyncTask<Void, Integer, Boolean> {
    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		
    		progressDialog = ProgressDialog.show(MainActivity.this, "", "正在同步中...", true, true);
    	}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			SyncClient client = new SyncClient();
			try {
				client.sync();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			
			if (result) {
				Toast.makeText(getApplicationContext(), "同步成功", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "同步失败", Toast.LENGTH_SHORT).show();
				
			}
			
		}
    	
    }
    
    
    
}