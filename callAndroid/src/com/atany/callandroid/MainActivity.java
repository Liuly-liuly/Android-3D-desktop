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
	static String ALBUM_PATH=null;//图片存储路径
	private static Drawable icon; // 存放图片
	private static String label; // 存放应用程序名
	private static String packageName; // 存放应用程序包名
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
		// 获取手机内所有应用
		List<PackageInfo> packlist = pManager.getInstalledPackages(0);
        for (int i = 0;i < packlist.size(); i++) {
			PackageInfo pak = (PackageInfo) packlist.get(i);

			// 判断是否为非系统预装的应用程序
			// 这里还可以添加系统自带的，这里就先不添加了，如果有需要可以自己添加
			// if()里的值如果<=0则为自己装的程序，否则为系统工程自带
			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM)<=0) {
		

				icon=pManager.getApplicationIcon(pak.applicationInfo);// 设置图片
				label=pManager.getApplicationLabel(pak.applicationInfo).toString();// 设置应用程序名字
				packageName=pak.applicationInfo.packageName;// 设置应用程序的包名
				//saveImg(label,drawableToBitmap(icon));
			//	try {
					//saveFile(drawableToBitmap(icon),label);
				//	saveFile2(drawableToBitmap(icon),label,null);//三
					
					saveImage(drawableToBitmap(icon),label);//四
					listlab.add(label);
					has.put(label,packageName);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				//saveFile(drawableToBitmap(icon),label);//保存图片
			
				//saveBitmap(drawableToBitmap(icon),label);//保存图片
				//has.put(label,getBytes(drawableToBitmap(icon)));
				j++;
			}

		}
    	//showMessage("路径一"+ALBUM_PATH);
    //	showMessage("四"+Environment.getExternalStorageDirectory());
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
	    * 打开自带的音乐播放器
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
//    	   startActivity(Intent.createChooser(intent, "选择音频播放器"));  
//	    	Intent intent = new Intent("android.intent.action.VIEW");  
//	    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
//	    	intent.putExtra ("oneshot", 0);  
//	    	intent.putExtra ("configchange", 0);  
//	    	Uri uri = Uri.fromFile(new File("/mnt/sdcard/ren.mp3"));  
//	    	intent.setDataAndType (uri, "audio/*");  
//	    	this.startActivity(intent);  
//	    	Intent intent = new Intent(Intent.ACTION_PICK);//intent  action属性
//	        intent.setType("audio/*");
//	        startActivity(intent);
//	    	 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//             intent.setType("audio/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
//             intent.addCategory(Intent.CATEGORY_OPENABLE);
//             startActivityForResult(intent,1);
	    	Intent intent_music = new Intent(Intent.ACTION_PICK);    
            intent_music.setDataAndType(Uri.EMPTY,"vnd.android.cursor.dir/playlist");    
            intent_music.putExtra("withtabs", true); // 显示tab选项卡    
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
	     * 显示应用列表详情
	     */
	    public void OpenotherApplist()
	    {
	    	  Intent intent = new Intent("android.intent.action.ALL_APPS");
            //  intent.setType("com.android.*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
            //  intent.addCategory(Intent.CATEGORY_OPENABLE);
              startActivityForResult(intent,1);
       	   
	    }
	    /*
	     * 寻找应用
	     */
	    public void findapp()
	    {
	    	 Uri uri =Uri.parse("market://search?q=pname:pkg_name");          
	    	    Intent it = new Intent(Intent.ACTION_VIEW,uri);          
	    	    startActivity(it);  
	    }
	    	/*
	    	 * 打开自带的相机
	    	 */
	    public  void openXJ()
	    		{
	    			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	    			startActivity(intent);
	    	  //1  
	    //	fileName="/data/data/com.atany.callandroid/files/";
//	        Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA"); //调用照相机  
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
	    	   * 调用android的图库 
	    	   */
	    public	void openTK()
	    	 {
	    		 Intent i = new Intent(Intent.ACTION_PICK, 
	    				 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    				 startActivityForResult(i, 1); 
	    	

	    	 }
	        /**
	         * 跳转到系统设置
	         */
	        public void systemSetUtils() {
	            Intent intent = new Intent(Settings.ACTION_SETTINGS);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);

	        }
	        /**
	         * 跳转到系统日期设置
	         */
	        public void systemDateUtils() {
	            Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(intent);

	        }
	        
	        
	        /**
	         * 是否开启了闪光灯
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
	         * 闪光灯开关
	         */
	        public void flashlightUtils() {
	            if (camera == null) {
	                camera = Camera.open();
	            }

	            Parameters parameters = camera.getParameters();
	            // String flashMode = parameters.getFlashMode();

	            if (isFlashlightOn()) {

	                parameters.setFlashMode(Parameters.FLASH_MODE_OFF);// 关闭
	                camera.setParameters(parameters);
	                camera.release();
	                camera = null;
	                Toast.makeText(MainActivity.this, "关闭手电筒", Toast.LENGTH_SHORT).show();
	            } else {
	                parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);// 开启
	                camera.setParameters(parameters);
	                Toast.makeText(MainActivity.this, "开启手电筒", Toast.LENGTH_SHORT).show();
	            }

	        }
	        /*
	         * 启动拨号器
	         */
	        public void openCell()
	        {
	        	Intent intent = new Intent(Intent.ACTION_DIAL);
	        	startActivity(intent);
	        }
	        /*
	         * 启动自带的浏览器
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
	              //意图的行为，隐式意图
	        	   intent.setAction(Intent.ACTION_VIEW);
	            //意图的数据
	        	  intent.setData(Uri.parse("http://www.baidu.com"));
	        	  //启动
	        	   startActivity(intent);

	        }
	        /*
	         * 激活自带的日历
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
	         * 启用自带的闹钟
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
	         * 通过包名激活应用
	         */
	        
	        public void openApp(String appPackename)
	        {
	        	// 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等  
	            PackageInfo packageinfo = null;  
	            try {  
	                packageinfo = getPackageManager().getPackageInfo(appPackename, 0);  
	            } catch (NameNotFoundException e) {  
	                e.printStackTrace();  
	            }  
	            if (packageinfo == null) {  
	                return;  
	            }  
	          
	            // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent  
	            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
	            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	            resolveIntent.setPackage(packageinfo.packageName);  
	          
	            // 通过getPackageManager()的queryIntentActivities方法遍历  
	            List<ResolveInfo> resolveinfoList = getPackageManager()  
	                    .queryIntentActivities(resolveIntent, 0);  
	          
	            ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
	            if (resolveinfo != null) {  
	                // packagename = 参数packname  
	                String packageName = resolveinfo.activityInfo.packageName;  
	                // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]  
	                String className = resolveinfo.activityInfo.name;  
	                // LAUNCHER Intent  
	                Intent intent = new Intent(Intent.ACTION_MAIN);  
	                intent.addCategory(Intent.CATEGORY_LAUNCHER);  
	          
	                // 设置ComponentName参数1:packagename参数2:MainActivity路径  
	                ComponentName cn = new ComponentName(packageName, className);  
	          
	                intent.setComponent(cn);  
	                startActivity(intent);  
	            }  
	        }
	        /*
	         *打开视频播放器
	         */
           public void openVideo()
           {
//        	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);   
//        	   intent.setType("video/*");   
//        	   startActivity(Intent.createChooser(intent, "选择视频播放器"));  
        	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               intent.setType("video/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
               intent.addCategory(Intent.CATEGORY_OPENABLE);
               startActivityForResult(intent,1);
        	   
        	  // startAPP("com.meizu.media.vido");
           }
           /*
            * 搜索apk
            */
           public void  Search_applications()
           {
        	   Uri uri = Uri.parse("market://search?Q=pname:pkg_name");  
        	   Intent intent = new Intent(Intent.ACTION_VIEW, uri);  
        	   this.startActivity(intent);    
           }
           /*
            * 安装apk
            */
           public void  Install_the_apk()
           {
        	   Uri installUri = Uri.fromParts("package", "xxx", null);  
        	   Intent  returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);   
        	   this.startActivity(returnIt);     
           }
           /*
            * 卸载apk
            */
           public void  Uninstall_the_apk(String strPackageName)
           {
        	   Uri uri = Uri.fromParts ("package",strPackageName, null);  
        	   Intent intent = new Intent (Intent.ACTION_DELETE, uri);   
        	   this.startActivity(  
        	   Intent.createChooser(intent,"Choose Email Client"));       
           }
           
           /*
            * 打开便签
            */
           public void opennotepaper()
           {
        	   startAPP("com.meizu.notepaper"); 
           }
           /*
            * 打开文件管理器
            */
           public void wenjian()
           {
        	   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               
        	   intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        	             
        	   startActivityForResult(intent, 1);
        	   
           }
           
           /**
            * 启动一个app
            * com -- ComponentName 对象，包含apk的包名和主Activity名
            * param -- 需要传给apk的参数
            */
           private void startApp(ComponentName com, String param) {
               if (com != null) {
                   PackageInfo packageInfo;
                   try {
                       packageInfo = getPackageManager().getPackageInfo(com.getPackageName(), 0);
                   } catch (NameNotFoundException e) {
                       packageInfo = null;
                       Toast.makeText(this, "没有安装", Toast.LENGTH_SHORT).show();
                       e.printStackTrace();
                   }
                   try {
                       Intent intent = new Intent();
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.setComponent(com);
                       if (param != null) {
                           Bundle bundle = new Bundle(); // 创建Bundle对象
                           bundle.putString("flag", param); // 装入数据
                           intent.putExtras(bundle); // 把Bundle塞入Intent里面
                       }
                       startActivity(intent);
                   } catch (Exception e) {
                       Toast.makeText(this, "启动异常", Toast.LENGTH_SHORT).show();
                   }
               }
           }
           /*
            * 启动一个app
            */
           public void startAPP(String appPackageName){
               try{
                   Intent intent = this.getPackageManager().getLaunchIntentForPackage(appPackageName);
                   startActivity(intent);
               }catch(Exception e){
                   Toast.makeText(this, "没有安装", Toast.LENGTH_LONG).show();
               }
           }
         //Drawable 转化成 Bitmap 的通用方法 
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
           
         //保存网络图片到本地 第一个参数是图片名称 第二个参数为需要保存的bitmap

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

           Log.i("", "成功");

           } catch (FileNotFoundException e) {

           e.printStackTrace();

           } catch (IOException e) {

           e.printStackTrace();

           }

           }
           
           /*
            * 保存图片到本应用的文件夹    方法 一
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
         
          // 保存四
           public void saveImage(Bitmap bmp,String fileName) {
        	    File appDir = new File("/data/data/com.atany.callandroid/files");
        	    if (!appDir.exists()) {
        	        appDir.mkdir();
        	    }
        	    String fileName1 = fileName+ ".png";
        	    File file = new File(appDir, fileName1);
        	  //  Toast.makeText(MainActivity.this, "四"+Environment.getExternalStorageDirectory(), Toast.LENGTH_SHORT).show();
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
        	    
        	    //保存五
        	  //指定保存路径
        	    public static void saveImageToGallery (Context context, Bitmap bmp){
        	    // 首先保存图片
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
