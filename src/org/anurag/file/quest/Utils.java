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

package org.anurag.file.quest;

import java.io.File;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class Utils {
	
	static String type;
	Resources res;
	static View v;
	public static boolean loaded = false;
	public static ArrayList<Item> music;
	public static ArrayList<Item> apps;
	public static ArrayList<Item> vids;
	public static ArrayList<Item> doc;
	public static ArrayList<Item> zip;
	public static ArrayList<Item> mis;
	public static ArrayList<Item> img;
	
	
	static String msize;
	static String asize;
	static String psize;
	static String dsize;
	static String vsize;
	static String zsize;
	static String misize;
	
	
	static long musicsize=0;
	static long apksize=0;
	static long vidsize=0;
	static long docsize=0;
	static long zipsize=0;
	static long missize=0;
	static long imgsize=0;
	
	File file; 	
	Handler handle;
	TextView count;
	Context ctx;
	
	static TextView musicText,musicTextCount;
	static TextView appText,appTextCount;
	static TextView imgText,imgTextCount;
	static TextView vidText,vidTextCount;
	static TextView docText,docTextCount;
	static TextView arcText,arcTextCount;
	static TextView misText,misTextCount;
	
	public Utils() {
		// TODO Auto-generated constructor stub
		
		file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
		music = new ArrayList<Item>();
		apps = new ArrayList<Item>();
		vids = new ArrayList<Item>();
		doc = new ArrayList<Item>();
		zip = new ArrayList<Item>();
		mis = new ArrayList<Item>();
		img = new ArrayList<Item>();
		//ctx = cont;
		
		musicsize=0;
		apksize=0;
		vidsize=0;
		docsize=0;
		zipsize=0;
		missize=0;
		imgsize=0;
	}
	public Utils(View view,Context cont) {
		// TODO Auto-generated constructor stub
		v = view;
		musicText = (TextView)v.findViewById(R.id.mSize);
		musicTextCount = (TextView)v.findViewById(R.id.mFiles);
		
		appText = (TextView)v.findViewById(R.id.aSize);
		appTextCount = (TextView)v.findViewById(R.id.aFiles);
		
		docText = (TextView)v.findViewById(R.id.dSize);
		docTextCount = (TextView)v.findViewById(R.id.dFile);
		
		imgText = (TextView)v.findViewById(R.id.pSize);
		imgTextCount = (TextView)v.findViewById(R.id.pFiles);
		
		vidText = (TextView)v.findViewById(R.id.vSize);
		vidTextCount = (TextView)v.findViewById(R.id.vFiles);
		
		arcText = (TextView)v.findViewById(R.id.zSize);
		arcTextCount = (TextView)v.findViewById(R.id.zFiles);
		
		misText = (TextView)v.findViewById(R.id.misFiles);
		misTextCount = (TextView)v.findViewById(R.id.misSize);
		
		file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
		
		music = new ArrayList<Item>();
		apps = new ArrayList<Item>();
		vids = new ArrayList<Item>();
		doc = new ArrayList<Item>();
		zip = new ArrayList<Item>();
		mis = new ArrayList<Item>();
		img = new ArrayList<Item>();
		
		
		
		ctx = cont;
		res = ctx.getResources();
		loaded = false;
		musicsize=0;
		apksize=0;
		vidsize=0;
		docsize=0;
		zipsize=0;
		missize=0;
		imgsize=0;
	}
	
	public void setView(View view){
		v = view;
	}
	
	public void load(){
		
		handle = new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what){
					case 1:
							//Displaying File counts of each type
							loaded = true;
							break;
				
					case 2:
							try{
								//DISLPAYS MUSIC SIZE..
								musicText.setText(msize);								
								musicTextCount.setText(music.size() + " "+ctx.getString(R.string.items));
							}catch(NullPointerException e){
								
							}
							break;
							
					case 3:
							try{
								//DISPLAYS APPS SIZE...
								appText.setText(asize);								
								appTextCount.setText(apps.size() + " "+ctx.getString(R.string.items));
							}catch(NullPointerException e){
								
							}
							break;
							
					case 4:
						
							try{
								//DSIPLAYS DOCS SIZE...
								docText.setText(dsize);							
								docTextCount.setText(doc.size() + " "+ctx.getString(R.string.items));
							}catch(NullPointerException e){
								
							}
							break;
					case 5:
							try{
								//displays IMAGE SIZE..
								imgText.setText(psize);								
								imgTextCount.setText(img.size() + " "+ctx.getString(R.string.items));
							}catch(NullPointerException e){
								
							}
							break;
							
					case 6:
							try{
								//displays video size...
								vidText.setText(vsize);								
								vidTextCount.setText(vids.size() + " "+ctx.getString(R.string.items));
							}catch(NullPointerException e){
								
							}
							break;
							
							
					case 7:
						
							try{
								//displays archive size...
								arcText.setText(zsize);								
								arcTextCount.setText(zip.size() + " "+ctx.getString(R.string.items));
							}catch(NullPointerException e){
								
							}
							break;
							
					case 8:
							//displays miscellaneous size...
							try{
								misText.setText(misize);								
								misTextCount.setText(mis.size() + " "+ctx.getString(R.string.items));
							}catch(NullPointerException e){
								
							}
							break;
				}
			}
		};		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//loaded = false;
				makelist(file);
				loaded = true;			
			}
		});
		/*if(!loaded)
			thread.start();
		else{
			if(music.size()>0)
				handle.sendEmptyMessage(2);
			if(apps.size()>0)
				handle.sendEmptyMessage(3);
			if(doc.size()>0)
				handle.sendEmptyMessage(4);
			if(img.size()>0)
				handle.sendEmptyMessage(5);
			if(vids.size()>0)
				handle.sendEmptyMessage(6);
			if(zip.size()>0)
				handle.sendEmptyMessage(7);
			if(mis.size()>0)
				handle.sendEmptyMessage(8);
		}*/
	//	makelist(file);
		//loaded = true;
		new MusicAsyncTask().execute();
		new ImageAsyncTask().execute();
		loaded  = true;
	}
	
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public String size(long size){
		if(size>Constants.GB)
			return String.format(ctx.getString(R.string.appsizegb), (double)size/(Constants.GB));
		
		else if(size > Constants.MB)
			return String.format(ctx.getString(R.string.appsizemb), (double)size/(Constants.MB));
		
		else if(size>1024)
			return String.format(ctx.getString(R.string.appsizekb), (double)size/(1024));
		
		else
			return String.format(ctx.getString(R.string.appsizebytes), (double)size);
	}
	
	/**
	 * 
	 * @param file
	 */
	void makelist(File file){
		if(file.isFile()){
			identifyType(file);
		}else if(file.isDirectory()){
			try{
				//FOR ME A NULLPOINTER RAISED WHEN USB WAS CONNECTED AND APP WAS OPEND...	
				for(File f:file.listFiles())
					makelist(f);
			}catch(Exception e){
				
			}
		}
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	private void identifyType(final File file){
		String name = file.getName();
		if(name.endsWith(".zip")||name.endsWith(".tar")||name.endsWith(".rar")||name.endsWith(".7z")
				||name.endsWith(".tar.gz")||name.endsWith(".tar.bz2")||name.endsWith(".ZIP")||name.endsWith(".TAR")||
				name.endsWith(".RAR")||name.endsWith(".7Z")||name.endsWith(".TAR.GZ")||name.endsWith(".TAR.BZ2")){
			
			/*zip.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
			zipsize+=file.length();
			zsize = size(zipsize);
			if(Constants.UPDATE_FILEGALLERY)
				handle.sendEmptyMessage(7);*/
			new AsyncTask<Void, Void, Void>(){
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					arcText.setText(zsize);								
					arcTextCount.setText(zip.size() + " "+ctx.getString(R.string.items));
				}
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					zip.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
					zipsize+=file.length();
					zsize = size(zipsize);
					return null;
				}
				
			}.execute();
		}
		else if(name.endsWith(".mp3")||name.endsWith(".ogg")||name.endsWith(".m4a")||name.endsWith(".wav")
				||name.endsWith(".amr")||name.endsWith(".MP3")||name.endsWith(".OGG")||name.endsWith(".M4A")||
				name.endsWith(".WAV")||name.endsWith(".AMR")){
			/*music.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
			musicsize+=file.length();
			msize = size(musicsize);
			if(Constants.UPDATE_FILEGALLERY)
				handle.sendEmptyMessage(2);*/
			new AsyncTask<Void, Void, Void>(){
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					musicText.setText(msize);								
					musicTextCount.setText(music.size() + " "+ctx.getString(R.string.items));
				}
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					music.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
					musicsize+=file.length();
					msize = size(musicsize);
					return null;
				}
				
			}.execute();
		}
		else if(name.endsWith(".apk")||name.endsWith(".APK")){
			/*apps.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
			apksize+=file.length();
			asize = size(apksize);
			if(Constants.UPDATE_FILEGALLERY)
				handle.sendEmptyMessage(3);*/
			new AsyncTask<Void, Void, Void>(){
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					appText.setText(asize);								
					appTextCount.setText(apps.size() + " "+ctx.getString(R.string.items));
				}
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					apps.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
					apksize+=file.length();
					asize = size(apksize);
					return null;
				}
				
			}.execute();
		}		
		else if(name.endsWith(".flv")||name.endsWith(".mp4")||name.endsWith(".3gp")||name.endsWith(".avi")
				||name.endsWith(".mkv")||name.endsWith(".FLV")||name.endsWith(".MP4")||name.endsWith(".3GP")||name.endsWith(".AVI")
				||name.endsWith(".MKV")){
			/*vids.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
			vidsize+=file.length();
			vsize = size(vidsize);
			if(Constants.UPDATE_FILEGALLERY)
				handle.sendEmptyMessage(6);*/
			new AsyncTask<Void, Void, Void>(){
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					vidText.setText(vsize);								
					vidTextCount.setText(vids.size() + " "+ctx.getString(R.string.items));
				}
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					vids.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
					vidsize+=file.length();
					vsize = size(vidsize);
					return null;
				}
				
			}.execute();
		}	
		else if(name.endsWith(".bmp")||name.endsWith(".gif")||name.endsWith(".jpeg")||name.endsWith(".jpg")
				||name.endsWith(".png")||name.endsWith(".BMP")||name.endsWith(".GIF")||name.endsWith(".JPEG")||name.endsWith(".JPG")
				||name.endsWith(".PNG")){
			
			new AsyncTask<Void, Void, Void>(){
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					imgText.setText(psize);								
					imgTextCount.setText(img.size() + " "+ctx.getString(R.string.items));
				}
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					img.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
					imgsize+=file.length();
					psize = size(imgsize);
					return null;
				}				
			}.execute();
			
			
			//if(Constants.UPDATE_FILEGALLERY)
				//handle.sendEmptyMessage(5);
		}
		else if(name.endsWith(".txt")||name.endsWith(".log")||name.endsWith(".ini")||name.endsWith(".doc")
				||name.endsWith(".ppt")||name.endsWith(".docx")||name.endsWith(".TXT")||name.endsWith(".LOG")||name.endsWith(".INI")||name.endsWith(".DOC")
				||name.endsWith(".PPT")||name.endsWith(".DOCX")){
			/*doc.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
			docsize+=file.length();
			dsize = size(docsize);
			if(Constants.UPDATE_FILEGALLERY)
				handle.sendEmptyMessage(4);*/
			new AsyncTask<Void, Void, Void>(){
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					docText.setText(dsize);							
					docTextCount.setText(doc.size() + " "+ctx.getString(R.string.items));
				}
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					doc.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
					docsize+=file.length();
					dsize = size(docsize);
					return null;
				}
				
			}.execute();
		}
		else{
			/*mis.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
			missize+=file.length();
			misize = size(missize);
			if(Constants.UPDATE_FILEGALLERY)
				handle.sendEmptyMessage(8);*/
			new AsyncTask<Void, Void, Void>(){
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					misText.setText(misize);								
					misTextCount.setText(mis.size() + " "+ctx.getString(R.string.items));
				}
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					mis.add(new Item(file, buildIcon(file), type, RootManager.getSize(file)));
					missize+=file.length();
					misize = size(missize);
					return null;
				}
				
			}.execute();
		}		
	}
	/**
	 * 
	 * @return
	 */
	private Drawable buildIcon(File f){
		String name = f.getName();
		if(name.endsWith(".zip")||name.endsWith(".ZIP")){
			type=ctx.getString(R.string.zip);
			return res.getDrawable(R.drawable.ic_launcher_zip_it);			
		}else if(name.endsWith(".7z")||name.endsWith(".7Z")){
			type=ctx.getString(R.string.zip7);
			return res.getDrawable(R.drawable.ic_launcher_7zip);
		}else if(name.endsWith(".rar")||name.endsWith(".RAR")){
			type=ctx.getString(R.string.rar);
			return res.getDrawable(R.drawable.ic_launcher_rar);
		}else if(name.endsWith(".tar")||name.endsWith(".TAR")||name.endsWith(".tar.gz")||name.endsWith(".TAR.GZ")
				||name.endsWith(".TAT.BZ2")||name.endsWith(".tar.bz2")){
			type=ctx.getString(R.string.tar);
			return res.getDrawable(R.drawable.ic_launcher_tar);
		}
		else if(name.endsWith(".mp3")||name.endsWith(".ogg")||name.endsWith(".m4a")||name.endsWith(".wav")
				||name.endsWith(".amr")||name.endsWith(".MP3")||name.endsWith(".OGG")||name.endsWith(".M4A")||
				name.endsWith(".WAV")||name.endsWith(".AMR")){
			type=ctx.getString(R.string.music);
			return res.getDrawable(R.drawable.ic_launcher_music);
		}
		else if(name.endsWith(".apk")||name.endsWith(".APK")){
			type=ctx.getString(R.string.application);
			return res.getDrawable(R.drawable.ic_launcher_apk);
		}else if(name.endsWith(".sh")||name.endsWith(".SH")||name.endsWith(".prop")||name.endsWith("init")
				||name.endsWith(".default")||name.endsWith(".rc")){
			type=ctx.getString(R.string.script);
			return res.getDrawable(R.drawable.ic_launcher_sh);
		}else if(name.endsWith(".pdf")||name.endsWith(".PDF")){
			type=ctx.getString(R.string.pdf);
			return res.getDrawable(R.drawable.ic_launcher_adobe);
		}else if(name.endsWith(".htm")||name.endsWith(".html")||name.endsWith(".mhtml")){
			type=ctx.getString(R.string.web);
			return res.getDrawable(R.drawable.ic_launcher_web_pages);
		}else if(name.endsWith(".flv")||name.endsWith(".mp4")||name.endsWith(".3gp")||name.endsWith(".avi")
				||name.endsWith(".mkv")||name.endsWith(".FLV")||name.endsWith(".MP4")||name.endsWith(".3GP")||name.endsWith(".AVI")
				||name.endsWith(".MKV")){
			type=ctx.getString(R.string.vids);
			return res.getDrawable(R.drawable.ic_launcher_video);
		}	
		else if(name.endsWith(".bmp")||name.endsWith(".gif")||name.endsWith(".jpeg")||name.endsWith(".jpg")
				||name.endsWith(".png")||name.endsWith(".BMP")||name.endsWith(".GIF")||name.endsWith(".JPEG")||name.endsWith(".JPG")
				||name.endsWith(".PNG")){
			type=ctx.getString(R.string.image);
			return res.getDrawable(R.drawable.ic_launcher_images);
		}else if(name.endsWith(".txt")||name.endsWith(".TXT")||name.endsWith(".log")||name.endsWith(".LOG")
				||name.endsWith(".ini")||name.endsWith(".INI")){
			type=ctx.getString(R.string.text);
			return res.getDrawable(R.drawable.ic_launcher_text);
		}
		else if(name.endsWith(".doc")||name.endsWith(".ppt")||name.endsWith(".docx")||name.endsWith(".DOC")
				||name.endsWith(".PPT")||name.endsWith(".DOCX")||name.endsWith(".pptx")||name.endsWith(".PPTX")
				||name.endsWith(".csv")||name.endsWith(".CSV")){
			type=ctx.getString(R.string.docs);
			return res.getDrawable(R.drawable.ic_launcher_ppt);
		}
		else{
			type=ctx.getString(R.string.unknown);
			return res.getDrawable(R.drawable.ic_launcher_unknown);
		}		
	}
	
	/**
	 * 
	 * @author Anurag.....
	 *
	 */
	private class ImageAsyncTask extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}	
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			imgText.setText(psize);								
			imgTextCount.setText(img.size() + " "+ctx.getString(R.string.items));
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Cursor cursor = ctx.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
					null, 
					null, 
					null, 
					MediaStore.Images.Media.TITLE + " ASC");
			String name;
			Drawable draw = ctx.getResources().getDrawable(R.drawable.ic_launcher_images); 
			String typ = ctx.getString(R.string.image);
			while(cursor.moveToNext()){
				name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
				File fil = new File(name);
				img.add(new Item(fil,
						draw,
						typ,
						RootManager.getSize(fil)));
				imgsize+=fil.length();
				psize = size(imgsize);
				publishProgress((Void[])null);
			}
			cursor.close();
			return null;
		}		
	}	
	
	/**
	 * 
	 * @author Anurag....
	 *
	 */
	private class MusicAsyncTask extends AsyncTask<Void, Void , Void>{
		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			musicText.setText(msize);								
			musicTextCount.setText(music.size() + " "+ctx.getString(R.string.items));
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Cursor cursor = ctx.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
					null,
					null,
					null,
					MediaStore.Audio.Media.TITLE+" ASC");
			String name;
			Drawable draw = ctx.getResources().getDrawable(R.drawable.ic_launcher_music); 
			String typ = ctx.getString(R.string.music);
			while(cursor.moveToNext()){
				name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
				File fil = new File(name);
				music.add(new Item(fil,
						draw,
						typ,
						RootManager.getSize(fil)));
				musicsize+=fil.length();
				msize = size(musicsize);
				publishProgress((Void[])null);
			}
			cursor.close();
			return null;
		}		
	}
}
