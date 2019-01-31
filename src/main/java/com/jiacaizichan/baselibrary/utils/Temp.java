package com.jiacaizichan.baselibrary.utils;

/**
 * author: LiHao
 * 联系方式: 1104642415
 * time: 15:21
 * decs:描述
 */
public class Temp {
//状态栏沉寂式实现
    //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
//                Window window = this.getWindow();
//                View decorView = window.getDecorView();
//                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
//                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//                decorView.setSystemUiVisibility(option);
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(Color.TRANSPARENT);
//
//                //导航栏颜色也可以正常设置
////                window.setNavigationBarColor(Color.TRANSPARENT);
//            } else {
//                Window window = this.getWindow();
//                WindowManager.LayoutParams attributes = window.getAttributes();
//                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
//                attributes.flags |= flagTranslucentStatus;
////                attributes.flags |= flagTranslucentNavigation;
//                window.setAttributes(attributes);
//            }
//        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明导航栏
////            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }


}
