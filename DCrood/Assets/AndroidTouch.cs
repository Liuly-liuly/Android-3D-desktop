using UnityEngine;
using System.Collections;
using System;
using System.Diagnostics;
[RequireComponent (typeof (Rigidbody))]
public class AndroidTouch : MonoBehaviour {
    float speed = 1f;
    public Rigidbody rb;
    // 记录手指触屏的位置
    Vector2 m_screenpos = new Vector2();

 
	// Use this for initialization
	void Start () {
        rb = gameObject.GetComponent<Rigidbody>();
        // 允许多点触控
        Input.multiTouchEnabled = true;
	}
	
	// Update is called once per frame
	void Update () {
      

#if !UNITY_EDITOR && ( UNITY_IOS || UNITY_ANDROID )

       MobileInput(); 
#else
       DesktopInput();
#endif


    }

    // 桌面系统鼠标操作
    void DesktopInput()
    {
        // 记录鼠标左键的移动距离
        float mx = Input.GetAxis("Mouse X");
        float my = Input.GetAxis("Mouse Y");


        if (  mx!= 0 || my !=0 )
        {
            //松开鼠标左键 
            if (Input.GetMouseButton(0))
            {
               // Camera.main.
                transform.Translate(new Vector3(mx * Time.deltaTime * 20f, my * Time.deltaTime * 20f, 0));
            }
        }
    }



    // 移动平台触屏操作
    void MobileInput()
    { 
        if (Input.touchCount <= 0)
            return;

        // 1个手指触摸屏幕
        if (Input.touchCount == 1)
        {
            
            if (Input.touches[0].phase == TouchPhase.Began)
            {
                // 记录手指触屏的位置
                m_screenpos = Input.touches[0].position;
                
            }
            // 手指移动
            else if (Input.touches[0].phase == TouchPhase.Moved)
            {
                Vector2 posend = Input.touches[0].position;
                if (m_screenpos.x > posend.x)//左移
                 //Camera.main.
                transform.Rotate(0, 80 * Time.deltaTime * speed, 0, Space.Self);
                // 移动摄像机
                // Camera.main.transform.Translate(new Vector3(Input.touches[0].deltaPosition.x * Time.deltaTime, Input.touches[0].deltaPosition.y * Time.deltaTime, 0));
                else   //右移
                    transform.Rotate(0, -80 * Time.deltaTime * speed, 0, Space.Self);
            }

            // 手指离开屏幕 判断移动方向
            if (Input.touches[0].phase == TouchPhase.Ended && 
                Input.touches[0].phase != TouchPhase.Canceled)
            {

                Vector2 pos = Input.touches[0].position;

                // 手指水平移动
                if (Mathf.Abs(m_screenpos.x - pos.x) > Mathf.Abs(m_screenpos.y - pos.y))
                {
                    if (m_screenpos.x > pos.x){
                        //手指向左划动
                     //   Camera.main.
                        transform.Rotate(0, 80 * Time.deltaTime * speed, 0, Space.Self);
                    }
                    else{

                        //手指向右划动
                       // Camera.main.
                        transform.Rotate(0, -80 * Time.deltaTime * speed, 0, Space.Self);
                    }
                }
                else   // 手指垂直移动
                {
                    if (m_screenpos.y > pos.y){
                        //手指向下划动
                       // transform.Rotate(0, 0, -80 * Time.deltaTime * speed, Space.Self);
                    }
                    else{
                        //手指向上划动
                       // transform.Rotate(0, 0, 80 * Time.deltaTime * speed, Space.Self);
                    }
                   
                }

            }

        }
        else if ( Input.touchCount >1 )
        {
            // 记录两个手指的位置
            Vector2 finger1 = new Vector2();
            Vector2 finger2 = new Vector2();

            // 记录两个手指的移动
            Vector2 mov1 = new Vector2();
            Vector2 mov2 = new Vector2();
      
            for (int i=0; i<2; i++ )
            {
                Touch touch = Input.touches[i];

                if (touch.phase == TouchPhase.Ended )
                    break;

                if ( touch.phase == TouchPhase.Moved )
                {
             
                    float mov = 0;
                    if (i == 0)
                    {
                        finger1 = touch.position;
                        mov1 = touch.deltaPosition;
                       
                    }
                    else
                    {
                        finger2 = touch.position;
                        mov2 = touch.deltaPosition;

                        if (finger1.x > finger2.x)
                       {
                           mov = mov1.x;
                       }
                       else
                       {
                           mov = mov2.x;
                       }

                       if (finger1.y > finger2.y)
                       {
                           mov+= mov1.y;
                       }
                       else
                       {
                           mov+= mov2.y;
                       }

                       // Camera.main.transform.Translate(0, 0, mov * Time.deltaTime);
                    }
                   
                   
                }
            }
        }

 
    }
}
