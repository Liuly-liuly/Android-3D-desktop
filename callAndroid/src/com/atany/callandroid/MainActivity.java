package com.atany.callandroid;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.unity3d.player.UnityPlayerActivity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.provider.Settings;
import android.provider.Telephony.Mms;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends UnityPlayerActivity implements Runnable  {
	private static Camera camera;
	static String ALBUM_PATH=null;//ͼƬ�洢·��
	private static Drawable icon; // ���ͼƬ
	private static String label; // ���Ӧ�ó�����
	private static String packageName; // ���Ӧ�ó������
	private static List<String> listlab=new ArrayList<String>();
	String strings[];
	int j=0;
	Hashtable has=new Hashtable();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Thread thread=new Thread(this);  
       // thread.start(); 
        Applist();
    }
    public void Applist()
    {
    	PackageManager pManager = MainActivity.this.getPackageManager();
		// ��ȡ�ֻ�������Ӧ��
		List<PackageInfo> packlist = pManager.getInstalledPackages(0);
        for (int i = 0;i < packlist.size(); i++) {
			PackageInfo pak = (PackageInfo) packlist.get(i);

			// �ж��Ƿ�Ϊ��ϵͳԤװ��Ӧ�ó���
			// ���ﻹ�������ϵͳ�Դ��ģ�������Ȳ�����ˣ��������Ҫ�����Լ����
			// if()���ֵ���<=0��Ϊ�Լ�װ�ĳ��򣬷���Ϊϵͳ�����Դ�
			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM)<=0) {
		

				icon=pManager.getApplicationIcon(pak.applicationInfo);// ����ͼƬ
				label=pManager.getApplicationLabel(pak.applicationInfo).toString();// ����Ӧ�ó�������
				packageName=pak.applicationInfo.packageName;// ����Ӧ�ó���İ���
				//saveImg(label,drawableToBitmap(icon));
			//	try {
					//saveFile(drawableToBitmap(icon),label);
				//	saveFile2(drawableToBitmap(icon),label,null);//��
					
					saveImage(drawableToBitmap(icon),label);//��
					listlab.add(label);
					has.put(label,packageName);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				//saveFile(drawableToBitmap(icon),label);//����ͼƬ
			
				//saveBitmap(drawableToBitmap(icon),label);//����ͼƬ
				//has.put(label,getBytes(drawableToBitmap(icon)));
				j++;
			}

		}
    	//showMessage("·��һ"+ALBUM_PATH);
    //	showMessage("��"+Environment.getExternalStorageDirectory());
      //  showMessage(""+j);
       strings=new String[listlab.size()];
        for(int i=0,j=listlab.size();i<j;i++){
        	strings[i]=listlab.get(i);
        	}
    //    Toast.makeText(MainActivity.this,strings[0] , Toast.LENGTH_SHORT).show();
    }
    public void openapps(String appname)
    {
    	startAPP((String) has.get(appname));
    	
    }
    public String[] LabName()
    {  
    	return strings;
    }
    public int getcount()
    {
    	return j;
    }

    
	public  void showMessage(final String msg){
		
		 Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
		
	}
  
    /*
	    * ���Դ������ֲ�����
	    */
	    public	void openMUSIC()
			{
//				 Intent intent = new Intent("com.meizu.music");
//				  startActivity(intent);
				  //2.
				 // Intent intent = new Intent();
				 // intent.setAction(Intent.ACTION_VIEW);
				  //3.
//				  Intent mIntent = new Intent();
//				     ComponentName comp = new ComponentName("com.android.music","com.android.music.MusicBrowserActivity");
//				     mIntent.setComponent(comp);
//				     mIntent.setAction(android.content.Intent.ACTION_VIEW);
//				     startActivity(mIntent);
//				  4.
//				  Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
//				  startActivity(intent);
//	    	startAPP("com.meizu.media.music");
//	 	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);   
//    	   intent.setType("audio/");   
//    	   startActivity(Intent.createChooser(intent, "ѡ����Ƶ������"));  
//	    	Intent intent = new Intent("android.intent.action.VIEW");  
//	    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
//	    	intent.putExtra ("oneshot", 0);  
//	    	intent.putExtra ("configchange", 0);  
//	    	Uri uri = Uri.fromFile(new File("/mnt/sdcard/ren.mp3"));  
//	    	intent.setDataAndType (uri, "audio/*");  
//	    	this.startActivity(intent);  
//	    	Intent intent = new Intent(Intent.ACTION_PICK);//intent  action����
//	        intent.setType("audio/*");
//	        startActivity(intent);
//	    	 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//             intent.setType("audio/*");//�������ͣ����������������ͣ������׺�Ŀ�������д��
//             intent.addCategory(Intent.CATEGORY_OPENABLE);
//             startActivityForResult(intent,1);
	    	Intent intent_music = new Intent(Intent.ACTION_PICK);    
            intent_music.setDataAndType(Uri.EMPTY,"vnd.android.cursor.dir/playlist");    
            intent_music.putExtra("withtabs", true); // ��ʾtabѡ�    
            intent_music.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
              
            Intent j =Intent.createChooser(intent_music, "Choose an application to open with:");  
            if (j == intent_music) {  
                startActivity(j);   
            } else {  
                Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");      
                startActivity(intent);  
            }  

			}
	    /*
	     * ��ʾӦ���б�����
	     */
	    public void OpenotherApplist()
	    {
	    	  Intent intent = new Intent("android.intent.action.ALL_APPS");
            //  intent.setType("com.android.*");//�������ͣ����������������ͣ������׺�Ŀ�������д��
            //  intent.addCategory(Intent.CATEGORY_OPENABLE);
              startActivityForResult(intent,1);
       	   
	    }
	    /*
	     * Ѱ��Ӧ��
	     */
	    public void findapp()
	    {
	    	 Uri uri =Uri.parse("market://search?q=pname:pkg_name");          
	    	    Intent it = new Intent(Intent.ACTION_VIEW,uri);          
	    	    startActivity(it);  
	    }
	    	/*
	    	 * ���Դ������
	    	 */
	    public  void openXJ()
	    		{
	    			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    			startActivity(intent);
	    	  //1  
	    //	fileName="/data/data/com.atany.callandroid/files/";
//	        Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA"); //���������  
//	        startActivity(intent);  
//	        //2  
//	        Intent i = new Intent(Intent.ACTION_CAMERA_BUTTON, null);  
//	        this.sendBroadcast(i);  
//	        //3  
//	        long dateTaken = System.currentTimeMillis();  
//	        String name = createName(dateTaken) + ".jpg";  
//	        String  fileName = folder + name;  
//	        ContentValues values = new ContentValues();  
//	        values.put(Images.Media.TITLE, fileName);  
//	        values.put("_data", fileName);  
//	        values.put(Images.Media.PICASA_ID, fileName);  
//	        values.put(Images.Media.DISPLAY_NAME, fileName);  
//	        values.put(Images.Media.DESCRIPTION, fileName);  
//	        values.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, fileName);  
//	        Uri photoUri = getContentResolver().insert(  
//	        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);  
//	        Intent inttPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
//	        inttPhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);  
//	        startActivityForResult(inttPhoto, 10);  
	    
	    		}
	    	  /*
	    	   * ����android��ͼ�� 
	    	   */
	    public	void openTK()
	    	 {
	    		 Intent i = new Intent(Intent.ACTION_PICK, 
	    				 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    				 startActivityForResult(i, 1); 
	    	

	    	 }
	        /**
	         * ��ת��ϵͳ����
	         */
	        public void systemSetUtils() {
	            Intent intent = new Intent(Settings.ACTION_SETTINGS);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);

	        }
	        /**
	         * ��ת��ϵͳ��������
	         */
	        public void systemDateUtils() {
	            Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);

	        }
	        
	        
	        /**
	         * �Ƿ����������
	         * @return
	         */
	        public boolean isFlashlightOn() {
	            if (camera == null) {
	                camera = Camera.open();
	            }

	            Parameters parameters = camera.getParameters();
	            String flashMode = parameters.getFlashMode();

	            if (flashMode.equals(Parameters.FLASH_MODE_TORCH)) {

	                return true;
	            } else {
	                return false;
	            }
	        }

	        /**
	         * ����ƿ���
	         */
	        public void flashlightUtils() {
	            if (camera == null) {
	                camera = Camera.open();
	            }

	            Parameters parameters = camera.getParameters();
	            // String flashMode = parameters.getFlashMode();

	            if (isFlashlightOn()) {

	                parameters.setFlashMode(Parameters.FLASH_MODE_OFF);// �ر�
	                camera.setParameters(parameters);
	                camera.release();
	                camera = null;
	                Toast.makeText(MainActivity.this, "�ر��ֵ�Ͳ", Toast.LENGTH_SHORT).show();
	            } else {
	                parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);// ����
	                camera.setParameters(parameters);
	                Toast.makeText(MainActivity.this, "�����ֵ�Ͳ", Toast.LENGTH_SHORT).show();
	            }

	        }
	        /*
	         * ����������
	         */
	        public void openCell()
	        {
	        	Intent intent = new Intent(Intent.ACTION_DIAL);
	        	startActivity(intent);
	        }
	        /*
	         * �����Դ��������
	         */
	        public void openWeb()
	        {
//	        	Intent intent= new Intent();
//	        	intent.setAction("android.intent.action.VIEW");
//	        	Uri content_url = Uri.parse("");
//	        	intent.setData(content_url);
//	        	intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
//	        	startActivity(intent);
	        	 Intent intent=new Intent();
	              //��ͼ����Ϊ����ʽ��ͼ
	        	   intent.setAction(Intent.ACTION_VIEW);
	            //��ͼ������
	        	  intent.setData(Uri.parse("http://www.baidu.com"));
	        	  //����
	        	   startActivity(intent);

	        }
	        /*
	         * �����Դ�������
	         */
	        public void opencalendar()
	        {
//	        	try {
//	                Intent i = new Intent();
//	                ComponentName cn = null;
//	                if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
//	                    cn = new ComponentName("com.Android.calendar",
//	                            "com.android.calendar");
//
//	                } else {
//	                    cn = new ComponentName("com.google.android.calendar",
//	                            "com.android.calendar");
//	                }
//	                i.setComponent(cn);
//	                startActivity(i);
//	            } catch (ActivityNotFoundException e) {
//	                // TODO: handle exception
//	              //  Log.e("ActivityNotFoundException", e.toString());
//	            }
	        	startAPP("com.android.calendar");
	        }
	        /*
	         * �����Դ�������
	         */
	        public void NZ()
	        {
	        	//startAPP("com.android.alarmclock");
//	        	Intent intent = new Intent();
//	        	intent.setAction(AlarmClock.ACTION_SET_ALARM);
//	        	startActivity(intent);
	        	Intent alarms = new Intent(AlarmClock.ACTION_SET_ALARM);
	        	   startActivity(alarms);
	        	
	        }
	        /*
	         * ͨ����������Ӧ��
	         */
	        
	        public void openApp(String appPackename)
	        {
	        	// ͨ��������ȡ��APP��ϸ��Ϣ������Activities��services��versioncode��name�ȵ�  
	            PackageInfo packageinfo = null;  
	            try {  
	                packageinfo = getPackageManager().getPackageInfo(appPackename, 0);  
	            } catch (NameNotFoundException e) {  
	                e.printStackTrace();  
	            }  
	            if (packageinfo == null) {  
	                return;  
	            }  
	          
	            // ����һ�����ΪCATEGORY_LAUNCHER�ĸð�����Intent  
	            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
	            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	            resolveIntent.setPackage(packageinfo.packageName);  
	          
	            // ͨ��getPackageManager()��queryIntentActivities��������  
	            List<ResolveInfo> resolveinfoList = getPackageManager()  
	                    .queryIntentActivities(resolveIntent, 0);  
	          
	            ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
	            if (resolveinfo != null) {  
	                // packagename = ����packname  
	                String packageName = resolveinfo.activityInfo.packageName;  
	                // �����������Ҫ�ҵĸ�APP��LAUNCHER��Activity[��֯��ʽ��packagename.mainActivityname]  
	                String className = resolveinfo.activityInfo.name;  
	                // LAUNCHER Intent  
	                Intent intent = new Intent(Intent.ACTION_MAIN);  
	                intent.addCategory(Intent.CATEGORY_LAUNCHER);  
	          
	                // ����ComponentName����1:packagename����2:MainActivity·��  
	                ComponentName cn = new ComponentName(packageName, className);  
	          
	                intent.setComponent(cn);  
	                startActivity(intent);  
	            }  
	        }
	        /*
	         *����Ƶ������
	         */
           public void openVideo()
           {
//        	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);   
//        	   intent.setType("video/*");   
//        	   startActivity(Intent.createChooser(intent, "ѡ����Ƶ������"));  
        	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               intent.setType("video/*");//�������ͣ����������������ͣ������׺�Ŀ�������д��
               intent.addCategory(Intent.CATEGORY_OPENABLE);
               startActivityForResult(intent,1);
        	   
        	  // startAPP("com.meizu.media.vido");
           }
           /*
            * ����apk
            */
           public void  Search_applications()
           {
        	   Uri uri = Uri.parse("market://search?Q=pname:pkg_name");  
        	   Intent intent = new Intent(Intent.ACTION_VIEW, uri);  
        	   this.startActivity(intent);    
           }
           /*
            * ��װapk
            */
           public void  Install_the_apk()
           {
        	   Uri installUri = Uri.fromParts("package", "xxx", null);  
        	   Intent  returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);   
        	   this.startActivity(returnIt);     
           }
           /*
            * ж��apk
            */
           public void  Uninstall_the_apk(String strPackageName)
           {
        	   Uri uri = Uri.fromParts ("package",strPackageName, null);  
        	   Intent intent = new Intent (Intent.ACTION_DELETE, uri);   
        	   this.startActivity(  
        	   Intent.createChooser(intent,"Choose Email Client"));       
           }
           
           /*
            * �򿪱�ǩ
            */
           public void opennotepaper()
           {
        	   startAPP("com.meizu.notepaper"); 
           }
           /*
            * ���ļ�������
            */
           public void wenjian()
           {
        	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               
        	   intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        	             
        	   startActivityForResult(intent, 1);
        	   
           }
           
           /**
            * ����һ��app
            * com -- ComponentName ���󣬰���apk�İ�������Activity��
            * param -- ��Ҫ����apk�Ĳ���
            */
           private void startApp(ComponentName com, String param) {
               if (com != null) {
                   PackageInfo packageInfo;
                   try {
                       packageInfo = getPackageManager().getPackageInfo(com.getPackageName(), 0);
                   } catch (NameNotFoundException e) {
                       packageInfo = null;
                       Toast.makeText(this, "û�а�װ", Toast.LENGTH_SHORT).show();
                       e.printStackTrace();
                   }
                   try {
                       Intent intent = new Intent();
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.setComponent(com);
                       if (param != null) {
                           Bundle bundle = new Bundle(); // ����Bundle����
                           bundle.putString("flag", param); // װ������
                           intent.putExtras(bundle); // ��Bundle����Intent����
                       }
                       startActivity(intent);
                   } catch (Exception e) {
                       Toast.makeText(this, "�����쳣", Toast.LENGTH_SHORT).show();
                   }
               }
           }
           /*
            * ����һ��app
            */
           public void startAPP(String appPackageName){
               try{
                   Intent intent = this.getPackageManager().getLaunchIntentForPackage(appPackageName);
                   startActivity(intent);
               }catch(Exception e){
                   Toast.makeText(this, "û�а�װ", Toast.LENGTH_LONG).show();
               }
           }
         //Drawable ת���� Bitmap ��ͨ�÷��� 
           public static Bitmap drawableToBitmap(Drawable drawable) {

              Bitmap bitmap = Bitmap.createBitmap(
                      drawable.getIntrinsicWidth(), 
                      drawable.getIntrinsicHeight(), 
                      drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

              Canvas canvas = new Canvas(bitmap);

              //canvas.setBitmap(bitmap);

              drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

              drawable.draw(canvas);

              return bitmap;

          }
           
         //��������ͼƬ������ ��һ��������ͼƬ���� �ڶ�������Ϊ��Ҫ�����bitmap

           public void saveImg(String imgName, Bitmap bitmap) {

           File file = new File(ALBUM_PATH, imgName + ".png");
         
           if (!file.exists()) {
        	   file.mkdirs();
        	   }

           try {

           FileOutputStream out = new FileOutputStream(file);

           bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);

           out.flush();

           out.close();

           Log.i("", "�ɹ�");

           } catch (FileNotFoundException e) {

           e.printStackTrace();

           } catch (IOException e) {

           e.printStackTrace();

           }

           }
           
           /*
            * ����ͼƬ����Ӧ�õ��ļ���    ���� һ
            */
           public static void saveFile(Bitmap bm, String fileName) throws IOException {  
               File dirFile = new File(ALBUM_PATH);  
               
               if(!dirFile.exists()){  
                   dirFile.mkdir(); 
               }  
               try { File myCaptureFile = new File(ALBUM_PATH , fileName+".png");  
               if (!myCaptureFile.exists()) {
               	myCaptureFile.createNewFile();
               	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));  
                   bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
                   bos.flush();  
                   bos.close(); 
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
                
           }
         
          // ������
           public void saveImage(Bitmap bmp,String fileName) {
        	    File appDir = new File("/data/data/com.atany.callandroid/files");
        	    if (!appDir.exists()) {
        	        appDir.mkdir();
        	    }
        	    String fileName1 = fileName+ ".png";
        	    File file = new File(appDir, fileName1);
        	  //  Toast.makeText(MainActivity.this, "��"+Environment.getExternalStorageDirectory(), Toast.LENGTH_SHORT).show();
        	    try {
        	        FileOutputStream fos = new FileOutputStream(file);
        	        bmp.compress(CompressFormat.PNG, 100, fos);
        	        fos.flush();
        	        fos.close();
        	        
        	    } catch (FileNotFoundException e) {
        	        e.printStackTrace();
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        	    }
        	    
        	    //������
        	  //ָ������·��
        	    public static void saveImageToGallery (Context context, Bitmap bmp){
        	    // ���ȱ���ͼƬ
        	    File appDir = new File(Environment.getExternalStorageDirectory(), "ttys");
        	    if(!appDir.exists()){
        	    appDir.mkdir();
        	    }
        	    String fileName = System.currentTimeMillis() +".png";
        	    File file = new File(appDir, fileName);

        	        try{
        	            FileOutputStream fos = new FileOutputStream(file);
        	            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
        	            fos.flush();
        	            fos.close();
        	        }catch(FileNotFoundException e){
        	            e.printStackTrace();
        	        }catch(IOException e){
        	            e.printStackTrace();
        	        }
        	  
        	}
        	  /*
        	   */
			public void MS()
			{
				Intent intent4 = new Intent(); 
				intent4.setClassName("com.android.mms","com.android.mms.ui.ConversationList"); 
				startActivity(intent4); 

			}
				public void run() {
					Applist();
					
				}

     
  
           

}
