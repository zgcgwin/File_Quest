/**
 * Copyright(c) 2014 DRAWNZER.ORG PROJECTS -> ANURAG
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *                             
 *                             anurag.dev1512@gmail.com
 *
 */


package org.anurag.settings;

import java.io.File;

import org.anurag.file.quest.FileQuest;
import org.anurag.file.quest.R;
import org.ultimate.menuItems.GetHomeDirectory;
import org.ultimate.menuItems.Info;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;




public class Settings extends Activity implements View.OnClickListener{

	
	SharedPreferences settingsPrefs;
	SharedPreferences.Editor edit;
	
	
	public static ImageView applied;
	public static boolean settingsChanged;
	public static boolean pagerAnimSettingsChanged;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settingsChanged = false;
		setContentView(R.layout.settings_ui);
		settingsPrefs = getSharedPreferences("MY_APP_SETTINGS", 0);
		edit = settingsPrefs.edit();		
	}


	
	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(settingsChanged || pagerAnimSettingsChanged){
			this.setResult(100);
			finish();
		}	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.anim_effect:
				{
					LinearLayout panel = (LinearLayout)findViewById(R.id.panel_amin);
					LinearLayout ls = (LinearLayout)findViewById(R.id.la_anim);
					if(panel.getVisibility() == View.VISIBLE){
						panel.setVisibility(View.GONE);
						ls.setVisibility(View.GONE);
					}else{
						panel.setVisibility(View.VISIBLE);
						ls.setVisibility(View.VISIBLE);
					}
				}
				break;
		
			case R.id.appearance:
				{
					LinearLayout adj = (LinearLayout)findViewById(R.id.transparency);
					LinearLayout icn = (LinearLayout)findViewById(R.id.folder_icons);
					if(adj.getVisibility() == View.VISIBLE){
						adj.setVisibility(View.GONE);
						icn.setVisibility(View.GONE);
					}else{
						adj.setVisibility(View.VISIBLE);
						icn.setVisibility(View.VISIBLE);
					}
				}
				break;	
		
			case R.id.thmb_set:
				{
					LinearLayout img = (LinearLayout)findViewById(R.id.thmb_img);
					LinearLayout mus = (LinearLayout)findViewById(R.id.thmb_alb);
					LinearLayout app = (LinearLayout)findViewById(R.id.thmb_app);
					if(img.getVisibility() == View.VISIBLE){
						img.setVisibility(View.GONE);
						mus.setVisibility(View.GONE);
						app.setVisibility(View.GONE);
					}else{
						img.setVisibility(View.VISIBLE);
						mus.setVisibility(View.VISIBLE);
						app.setVisibility(View.VISIBLE);
					}
				}
				break;
			case R.id.start_options:
				{
					LinearLayout img = (LinearLayout)findViewById(R.id.panel_startup);
					LinearLayout mus = (LinearLayout)findViewById(R.id.start_dir);
					if(img.getVisibility() == View.VISIBLE){
						img.setVisibility(View.GONE);
						mus.setVisibility(View.GONE);
					}else{
						img.setVisibility(View.VISIBLE);
						mus.setVisibility(View.VISIBLE);
					}
				}
				break;
			case R.id.itm_locker:
				{
					LinearLayout img = (LinearLayout)findViewById(R.id.reset_passwd);
					LinearLayout mus = (LinearLayout)findViewById(R.id.unlock_all);
					LinearLayout ch = (LinearLayout)findViewById(R.id.lock_child);
					if(img.getVisibility() == View.VISIBLE){
						img.setVisibility(View.GONE);
						mus.setVisibility(View.GONE);
						ch.setVisibility(View.GONE);
					}else{
						img.setVisibility(View.VISIBLE);
						mus.setVisibility(View.VISIBLE);
						ch.setVisibility(View.VISIBLE);
					}
				}
				break;
				
			case R.id.folder_options:
				{
					LinearLayout img = (LinearLayout)findViewById(R.id.hide_folder);
					LinearLayout mus = (LinearLayout)findViewById(R.id.sort);
					if(img.getVisibility() == View.VISIBLE){
						img.setVisibility(View.GONE);
						mus.setVisibility(View.GONE);
					}else{
						img.setVisibility(View.VISIBLE);
						mus.setVisibility(View.VISIBLE);
					}
				}
				break;
				
			case R.id.home:
				new GetHomeDirectory(Settings.this, FileQuest.size.x*8/9, settingsPrefs);
				break;
				
			case R.id.cl_gesture:
				boolean del = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/File Quest/.gesture").delete();
				if(del)
					Toast.makeText(Settings.this, R.string.gesturedatacleared, Toast.LENGTH_SHORT).show();
				break;
				
			case R.id.about:
				new Info(Settings.this, FileQuest.size.x*8/9);
				break;
				
			case R.id.panel_amin:
				new PagerAnimDialog(Settings.this, FileQuest.size.x*8/9, edit);
				break;
				
			case R.id.la_anim:
				new ListAnimDialog(Settings.this, FileQuest.size.x*8/9, edit);
				break;
				
			case R.id.transparency:
				new AdjusTranDialog(Settings.this, FileQuest.size.x*8/9, edit);
				break;
				
			case R.id.folder_icons:
				new FolderIconDialog(Settings.this, FileQuest.size.x*8/9, edit);
				break;
				
			case R.id.panel_startup:
				new StartupDirDialog(Settings.this, FileQuest.size.x*8/9, edit);
				break;
				
			case R.id.start_dir:
				new StartupDirDialog(Settings.this, FileQuest.size.x*8/9, edit);
				break;
				
			case R.id.hide_folder:
				Toast.makeText(Settings.this, "Do it", Toast.LENGTH_SHORT).show();
				break;
				
			case R.id.sort:
				new SortDialog(Settings.this, FileQuest.size.x*8/9, edit);
				break;
			
		}		
	}		
}
