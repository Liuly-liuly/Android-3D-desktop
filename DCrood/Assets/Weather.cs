using Newtonsoft.Json;
using UnityEngine;
using System.Collections;
using System.IO;
using LitJson;
using System.Text.RegularExpressions;
using System.Collections.Generic;

public class Weather : MonoBehaviour
{

    public UIInput inputCityName;
    public GameObject button;
    public GameObject rain;
    public GameObject snow;
    public GameObject weather_cube_current; //天气贴图
    public GameObject weather_cube01_Sunny; //天气贴图
    public GameObject weather_cube02_Cloudy; //天气贴图
    public GameObject weather_cube03_Windy; //天气贴图
    public GameObject weather_cube04_HWind; //天气贴图
    public GameObject weather_cube05_HHWind; //天气贴图
    public GameObject weather_cube06_LRain; //天气贴图
    public GameObject weather_cube07_HRain; //天气贴图
    public GameObject weather_cube08_Snowy; //天气贴图
    public GameObject weather_cube09_IceRain; //天气贴图
    public GameObject weather_cube10_Fog; //天气贴图
    public GameObject weather_cube11_DustStorm; //天气贴图
    Root rt;
    string s1,s2;
    string cityname;
    int lastwea;
    public GameObject ui;

    public class Update
    {
        public string loc;
        public string utc;
    }

    public class Basic
    {
        public string city;

        public string cnty;

        public string id;

        public string lat;

        public string lon;

        public Update update;
    }

    public class Cond
    {
        public string code;
        public string txt;
    }

    public class Wind
    {
        public string deg;
        public string dir;
        public string sc;
        public string spd;
    }

    public class Now
    {
        public Cond cond;
        public string fl;
        public string hum;
        public string pcpn;
        public string pres;
        public string tmp;
        public string vis;
        public Wind wind;
    }

    public class HeWeather5Item
    {
        public Basic basic;
        public Now now;
        public string status;
    }

    public class Root
    {
        public List<HeWeather5Item> HeWeather5;
    }

	// Use this for initialization
    void Start()
    {
        lastwea = 0;
        cityname = "北京";
        inputCityName.value = "北京";
        weather_cube_current=weather_cube01_Sunny;
        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
    }
     

    void Awake(){
        //GameObject button ; //GameObject.Find("UI Root/GetWeather");
        //设置这个按钮的监听，指向本类的ButtonClick方法中。
        UIEventListener.Get(button).onClick = getWeatherInfo;
    }

    void update()
    {
        if( (int) Time.time %60 ==0 )
        {
            string type = "utf-8";
            string Url = "https://free-api.heweather.com/v5/now?city=" + cityname + "&key=29dc35d4b161490cb03639d34935b786";
            System.Net.WebRequest wReq = System.Net.WebRequest.Create(Url);
            System.Net.WebResponse wResp = wReq.GetResponse();
            System.IO.Stream respStream = wResp.GetResponseStream();
            string data = "";
            using (System.IO.StreamReader reader = new System.IO.StreamReader(respStream, System.Text.Encoding.GetEncoding(type)))
            {
                data = reader.ReadToEnd();
            }

            data = Regex.Unescape(data);
            //Debug.Log(data);
            try
            {
                rt = Newtonsoft.Json.JavaScriptConvert.DeserializeObject<Root>(data);
                foreach (HeWeather5Item hw in rt.HeWeather5)
                {
                    s1 = hw.now.tmp;
                    s2 = hw.now.cond.txt.Trim();
                    Debug.Log(hw.now.tmp);
                    Debug.Log(hw.now.cond.txt);
                    if (lastwea != 1 && (s2 == "晴" || s2 == "平静" || s2 == "热" || s2 == "未知"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube01_Sunny.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube01_Sunny; //改变当前图片记录
                        lastwea = 1;
                    }
                    else if (lastwea != 2 && (s2 == "多云" || s2 == "少云" || s2 == "晴间多云"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube02_Cloudy.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube_current = weather_cube02_Cloudy; //改变当前图片记录   
                        lastwea = 2;
                    }
                    else if (lastwea != 3 && (s2 == "有风" || s2 == "微风" || s2 == "和风" || s2 == "清风"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube03_Windy.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube03_Windy; //改变当前图片记录 
                        lastwea = 3;
                    }
                    else if (lastwea != 4 && (s2 == "强风" || s2 == "劲风" || s2 == "疾风" || s2 == "大风" || s2 == "烈风" || s2 == "阴" || s2 == "冷"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube04_HWind.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube04_HWind; //改变当前图片记录 
                        lastwea = 4;
                    }
                    else if (lastwea != 5 && (s2 == "风暴" || s2 == "狂爆风" || s2 == "飓风" || s2 == "龙卷风" || s2 == "热带风暴"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube05_HHWind.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube05_HHWind; //改变当前图片记录  
                        lastwea = 5;
                    }
                    else if (lastwea != 6 && (s2 == "小雨" || s2 == "中雨" || s2 == "毛毛雨" || s2 == "细雨" || s2 == "雨夹雪" || s2 == "阵雨夹雪"))
                    {
                        rain.transform.position = new Vector3(591.99f, 15.8f, -1153.45f);//雨 移到窗前
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube06_LRain.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube06_LRain; //改变当前图片记录  
                        lastwea = 6;
                    }
                    else if (lastwea != 7 && (s2 == "强阵雨" || s2 == "强雷阵雨" || s2 == "雷阵雨" || s2 == "大雨" || s2 == "极端降雨" || s2 == "暴雨" || s2 == "大暴雨" || s2 == "特大暴雨" || s2 == "雷阵雨伴有冰雹" || s2 == "阵雨"))
                    {
                        rain.transform.position = new Vector3(591.99f, 15.8f, -1153.45f);//雨 移到窗前
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube07_HRain.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube07_HRain; //改变当前图片记录 
                        lastwea = 7;
                    }
                    else if (lastwea != 8 && (s2 == "小雪" || s2 == "中雪" || s2 == "大雪" || s2 == "暴雪" || s2 == "雨雪天气" || s2 == "阵雪" || s2 == "暴雨" || s2 == "雨雪天气" || s2 == "雨雪天气" || s2 == "雨雪天气" || s2 == "阵雪"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(591.9f, 17.4f, -1149.3f);//雪 移到窗前
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube08_Snowy.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube08_Snowy; //改变当前图片记录 
                        lastwea = 8;
                    }
                    else if (lastwea != 9 && s2 == "冻雨")
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube09_IceRain.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube09_IceRain; //改变当前图片记录
                        lastwea = 9;
                    }
                    else if (lastwea != 10 && (s2 == "薄雾" || s2 == "雾" || s2 == "霾"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube10_Fog.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube10_Fog; //改变当前图片记录  
                        lastwea = 10;
                    }
                    else if (lastwea != 11 && (s2 == "扬沙" || s2 == "浮尘" || s2 == "沙尘暴" || s2 == "强沙尘暴"))
                    {
                        rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                        snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                        weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                        weather_cube11_DustStorm.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                        weather_cube_current = weather_cube11_DustStorm; //改变当前图片记录 
                        lastwea = 11;
                    }
                }
            }
            catch (JsonSerializationException ex)
            {
                Debug.Log("wrong!");
            }
        }
    }
    




    private void getWeatherInfo(GameObject button)
    {
        //inputCityName.value = "桂林";
       
        cityname = inputCityName.value.Trim();
        string type = "utf-8";

        string Url = "https://free-api.heweather.com/v5/now?city=" + cityname + "&key=29dc35d4b161490cb03639d34935b786";
        System.Net.WebRequest wReq = System.Net.WebRequest.Create(Url);
        System.Net.WebResponse wResp = wReq.GetResponse();
        System.IO.Stream respStream = wResp.GetResponseStream();
        string data = "";
        using (System.IO.StreamReader reader = new System.IO.StreamReader(respStream, System.Text.Encoding.GetEncoding(type)))
        {
            data = reader.ReadToEnd();
        }

        data = Regex.Unescape(data);
        //Debug.Log(data);
        try
        {
            rt = Newtonsoft.Json.JavaScriptConvert.DeserializeObject<Root>(data);
            foreach (HeWeather5Item hw in rt.HeWeather5)
            {
                s1 = hw.now.tmp;
                s2 = hw.now.cond.txt.Trim();
                Debug.Log(hw.now.tmp);
                Debug.Log(hw.now.cond.txt);
                if ( lastwea!=1 && (s2 == "晴" || s2 == "平静" || s2 == "热" || s2 == "未知"))
                {
                    rain.transform.position = new Vector3(596.1f,15.8f,-1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f,17.4f,-1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f,-1.5f,-1158.5f);//将当前图片移走
                    weather_cube01_Sunny.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube01_Sunny; //改变当前图片记录
                    lastwea = 1;
                }
                else if (lastwea!=2 && (s2 == "多云" || s2 == "少云" || s2 == "晴间多云"))
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube02_Cloudy.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube_current = weather_cube02_Cloudy; //改变当前图片记录   
                    lastwea = 2;
                }
                else if (lastwea!=3 && (s2 == "有风" || s2 == "微风" || s2 == "和风" || s2 == "清风"))
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube03_Windy.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube03_Windy; //改变当前图片记录 
                    lastwea = 3;
                }
                else if (lastwea!=4 && (s2 == "强风" || s2 == "劲风" || s2 == "疾风" || s2 == "大风" || s2 == "烈风" || s2 == "阴" || s2 == "冷"))
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube04_HWind.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube04_HWind; //改变当前图片记录 
                    lastwea = 4;
                }
                else if (lastwea!=5 && (s2 == "风暴" || s2 == "狂爆风" || s2 == "飓风" || s2 == "龙卷风" || s2 == "热带风暴"))
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube05_HHWind.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube05_HHWind; //改变当前图片记录  
                    lastwea = 5;
                }
                else if (lastwea!=6 && (s2 == "小雨" || s2 == "中雨" || s2 == "毛毛雨" || s2 == "细雨" || s2 == "雨夹雪" || s2 == "阵雨夹雪"))
                {
                    rain.transform.position = new Vector3(591.99f,15.8f,-1153.45f);//雨 移到窗前
                    snow.transform.position = new Vector3(593.6f,17.4f,-1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube06_LRain.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube06_LRain; //改变当前图片记录  
                    lastwea = 6;
                }
                else if (lastwea!=7 && (s2 == "强阵雨" || s2 == "强雷阵雨" || s2 == "雷阵雨" || s2 == "大雨" || s2 == "极端降雨" || s2 == "暴雨" || s2 == "大暴雨" || s2 == "特大暴雨" || s2 == "雷阵雨伴有冰雹" || s2 == "阵雨"))
                {
                    rain.transform.position = new Vector3(591.99f, 15.8f, -1153.45f);//雨 移到窗前
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube07_HRain.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube07_HRain; //改变当前图片记录 
                    lastwea = 7;
                }
                else if (lastwea!=8 && (s2 == "小雪" || s2 == "中雪" || s2 == "大雪" || s2 == "暴雪" || s2 == "雨雪天气" || s2 == "阵雪" || s2 == "暴雨" || s2 == "雨雪天气" || s2 == "雨雪天气" || s2 == "雨雪天气" || s2 == "阵雪"))
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(591.9f,17.4f,-1149.3f);//雪 移到窗前
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube08_Snowy.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube08_Snowy; //改变当前图片记录 
                    lastwea = 8;
                }
                else if (lastwea!=9 && s2 == "冻雨")
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube09_IceRain.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube09_IceRain; //改变当前图片记录
                    lastwea = 9;
                }
                else if (lastwea != 10 && (s2 == "薄雾" || s2 == "雾" || s2 == "霾"))
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube10_Fog.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube10_Fog; //改变当前图片记录  
                    lastwea = 10;
                }
                else if (lastwea != 11 && (s2 == "扬沙" || s2 == "浮尘" || s2 == "沙尘暴" || s2 == "强沙尘暴"))
                {
                    rain.transform.position = new Vector3(596.1f, 15.8f, -1177.7f); //移走 雨
                    snow.transform.position = new Vector3(593.6f, 17.4f, -1159.7f); //移走 雪
                    weather_cube_current.transform.position = new Vector3(592.5f, -1.5f, -1158.5f);//将当前图片移走
                    weather_cube11_DustStorm.transform.position = new Vector3(589.9f, -1.5f, -1141.9f); //将天气对应的图片移到窗前
                    weather_cube_current = weather_cube11_DustStorm; //改变当前图片记录 
                    lastwea = 11;
                }
            }
        }
        catch (JsonSerializationException ex)
        {
            Debug.Log("wrong!");
        }
        ui.SetActive(false);
    }


   //void OnGUI()
   // {
   //     GUI.Label(new Rect(50, 50, 200, 200), "天气：" + s2);
   //     GUI.Label(new Rect(100, 400, 300, 200), "温度：" + s1);

   // }
}
