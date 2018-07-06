using UnityEngine;
using System.Collections;
using System;
using System.Diagnostics;
using System.Threading;

public class AndroidSDKApp : MonoBehaviour {
    private AndroidJavaObject jo;
    public string hitname = "";
    public RaycastHit hit;
    GameObject b;
   bool count_open ;
   bool count_close ;
   bool UI_active;  
   bool UI_Weather_active;
  
    public string ALBUM_PATH=null;
    Stopwatch sw = new Stopwatch();
    int temp;
    public GameObject ui;
    public GameObject uiWeather;
    //对话框面板

	void Start () {
        temp = 0;
        b = GameObject.Find("magicboxtu");
        AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
      
	}
    void Awake()
    {
        count_open = false;
        count_close = true;
        UI_active = false;
        UI_Weather_active = false;
    }
    Vector2 m_screenpos = new Vector2();
    Vector2 m_screenposend = new Vector2();
	void Update () {
        //当用户按下手机的返回键或home键盖上盖子
        if (Input.GetKeyDown(KeyCode.Escape) || Input.GetKeyDown(KeyCode.Home))
        {
            uiWeather.SetActive(false);
            UI_Weather_active = false;
            if (count_open)//盖子是打开的
            {
                b.transform.Rotate(0, 50f, 0, Space.Self);
                count_close = true;
                count_open = false;
               
                ui.SetActive(false);
                UI_active = false;
                GetComponent<AndroidTouch>().enabled = true;
            }

        }
        if (Input.GetTouch(0).phase == TouchPhase.Began)//按下手指
       {
           // 记录手指触屏的位置
           m_screenpos = Input.touches[0].position;
                  
        }
        if (Input.GetTouch(0).phase == TouchPhase.Ended)//手指离开
        {
            m_screenposend = Input.touches[0].position;
            if (Math.Abs(m_screenpos.x - m_screenposend.x) > 2)//滑动,不触发动作
            {
                ;
            }
            else
            {
            Ray ray = Camera.main.ScreenPointToRay(Input.GetTouch(0).position);
            if (Physics.Raycast(ray, out hit, 1000f))
            {
                hitname = hit.collider.name;
              
            
                switch (hitname)
                {
                    case "postcard":
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                               () =>
                               {
                                   jo.Call("MS");

                               }
                           ));
                        break;
                    case"cgaxis_plants_16_01_MeshPart1"://寻找应用
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                               () =>
                               {
                                   jo.Call("findapp");

                               }
                           ));
                       // findapp
                        break;
                    case"table_top"://桌子顶部  //打开应用列表
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                               () =>
                               {
                                   jo.Call("OpenotherApplist");

                               }
                           ));
                        break;
                case "cgaxis_electronics_11_02_MeshPart1"://打开视频播放器
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                               () =>
                               {
                                   jo.Call("openVideo");

                               }
                           ));
                        break;
                   case"shu":
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                                () =>
                                {
                                    jo.Call("wenjian");

                                }
                            ));
                        break;
                case "shu002":
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                                () =>
                                {
                                    jo.Call("wenjian");

                                }
                            ));
                        break;
                    case"SlidingWindow001"://天气
                       { 
                         if(!UI_active)
                         {
                            uiWeather.SetActive(true);
                            UI_Weather_active = true;
                         }
                       }
                       
                        break;
                    case "canlandar001"://日历
                        {
                           
                            jo.Call("runOnUiThread", new AndroidJavaRunnable(
                                () =>
                                {
                                    jo.Call("opencalendar");
                            
                                }
                            ));
                        }
                        break;
                    case "globe"://浏览器（地球仪）
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                                jo.Call("openWeb");
                            }
                        ));
                        break;
                    case "toolbox"://设置（工具箱）
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                                jo.Call("systemSetUtils");
                            }
                        ));
                        break;
                    case "Line03"://手电筒
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                                jo.Call("flashlightUtils");
                            }
                        ));
                        break;
                    case "xiangji"://相机
                        { jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                              
                                jo.Call("openXJ");
                            
                            }
                        ));
                      
                        }
                        break;
                    case "yinxiang"://音乐（音响）
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                               
                                jo.Call("openMUSIC");
                              
                            }
                        ));
                        break;
                    case "dianhua"://拨号器（电话）
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                                jo.Call("openCell");
                            }
                        ));
                        break;
                    case "xiangce"://图库（相册）
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                                
                                jo.Call("openTK");
                           
                            }
                        ));
                        break;
                    case "shizhong"://闹钟（时钟）
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                            () =>
                            {
                                jo.Call("NZ");

                            }
                        ));
                        break;
                    case "magicbox_topx" :
                        {
                            if (count_close && !UI_Weather_active)//盖子是关闭的
                            {
                                b.transform.Rotate(0, -50f, 0, Space.Self);
                                count_open = true;
                                count_close = false;
                                if (UI_Weather_active) //当前天气NGUI是打开的  
                                {
                                    uiWeather.SetActive(false);
                                }
                                ui.SetActive(true);
                                UI_active=true;
                            }
                        }
                        break;
                   

                    case "postcard_front"://打开便签
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                 () =>
                 {

                     jo.Call("opennotepaper");

                 }
             ));
                        break;
                    case "switch":
                        jo.Call("runOnUiThread", new AndroidJavaRunnable(
                                        () =>
                                        {
                                            jo.Call("formclass","确定退出");
                                         
                                        }
                                    ));
                        Application.Quit();  
                        break;
                    default:
                        break;
                }
            }
         }
        }
	
	}
}
