package com.jiacaizichan.baselibrary.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.jaeger.library.StatusBarUtil;
import com.jiacaizichan.baselibrary.R;
import com.jiacaizichan.baselibrary.utils.ActivityLink;
import com.jiacaizichan.baselibrary.utils.SharedPreferencesUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public Unbinder bind;

    public Toolbar mToolBar;
    public FrameLayout mFlContent;
    private TextView mTvTitle;
    private TextView mTvDecs;
    public SpinKitView myProgressBar;
    public Context context;
    private RelativeLayout mRv;
    public String sessionId;
    public boolean isLogin;

    //tollBar左边icon的点击事件
    public OnClickListener onClickListener;
    public OnClickListenerRight onClickListenerRight;
    public Toast toast;
    public MaterialDialog dialog;

    public boolean isLogin() {
        return isLogin;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setOrientation();
        super.onCreate(savedInstanceState);
        ActivityLink.addActivity(this);
//        ActivityLink.getInstence().addActivity(this);
        setContentView(R.layout.activity_base);
        initView();
        initConfig();
        init(savedInstanceState);
    }

    public void setOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void hindProgressBar(){
        mRv.setVisibility(View.GONE);
//        if (dialog!=null){
//            dialog.dismiss();
//        }
    }

    public void showProgressBar(){

//        dialog.show();


        if (mRv.getVisibility()!=View.GONE){
            return;
        }
        mRv.setVisibility(View.VISIBLE);
        mRv.setFocusable(true);
        mRv.setClickable(true);
        mRv.setOnClickListener(this);
    }

    /**
     * 初始化配置baseActivity的配置
     */
    private void initConfig() {
        sessionId = (String) SharedPreferencesUtil.getParam(context,"sessionId","");
        isLogin = (boolean) SharedPreferencesUtil.getParam(context,"isLogin",false);
    }

    /**
     * 显示Toast
     * @param content
     */

    protected void showToast(String content){
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public void setToolBarDecs(String value, Drawable drawable, final OnClickListenerRight onClickListener){
        this.onClickListenerRight = onClickListener;
        mTvDecs.setVisibility(View.VISIBLE);
        mTvDecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListenerRight!=null){
                    onClickListenerRight.onClick();
                }
            }
        });
        mTvDecs.setText(value);
        mTvDecs.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
    }


    public void setToolBarDecs(String value, Drawable drawable,String color, final OnClickListenerRight onClickListener){
        this.onClickListenerRight = onClickListener;
        mTvDecs.setVisibility(View.VISIBLE);
        mTvDecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListenerRight!=null){
                    onClickListenerRight.onClick();
                }
            }
        });
        mTvDecs.setText(value);
        mTvDecs.setTextColor(Color.parseColor(color));
        mTvDecs.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
    }


    /**
     * 初始化baseActivity的控件和
     */
    private void initView() {
//        dialog = new MaterialDialog.Builder(this)
//                .customView(R.layout.layout_logding,false)
//                .build();

        context = getApplicationContext();
        mTvDecs = findViewById(R.id.base_ac_toolbar_tv_decs);
        mToolBar = findViewById(R.id.base_ac_toolbar);
        mRv = findViewById(R.id.base_ac_rl_pb);
        mFlContent = findViewById(R.id.base_ac_fl_content);
        mTvTitle = findViewById(R.id.base_ac_toolbar_tv_title);

//        myProgressBar = findViewById(R.id.base_ac_pb);
//        ThreeBounce threeBounce = new ThreeBounce();
//        threeBounce.setColor(Color.parseColor("#2BDCC5"));
//        myProgressBar.setIndeterminateDrawable(threeBounce);

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //获取虚拟键的高度
//        navigation = getNavigationBarHeight();
//        navigation = screenUtils.getNavigationBarHeight();

        //获取状态栏的高度
//        int statusBarHeight = screenUtils.getStatusBarHeight();

//        getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0,statusBarHeight,0,navigation);

        LayoutInflater.from(this).inflate(getContentView(), mFlContent);
        bind = ButterKnife.bind(this);
        hindProgressBar();

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            StatusBarUtil.setColorNoTranslucent(BaseActivity.this, Color.parseColor("#ffffff"));
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }else {
            StatusBarUtil.setColorNoTranslucent(BaseActivity.this, Color.parseColor("#2BDCC5"));
            mToolBar.setBackgroundColor(Color.parseColor("#2BDCC5"));
        }
    }

    public void setToolbarBg(String color){
        mToolBar.setBackgroundColor(Color.parseColor(color));
    }

    public abstract void init(Bundle savedInstanceState);

    protected abstract int getContentView();

    protected void setLeftIcon(int icon,OnClickListener onClickListener){
        mToolBar.setNavigationIcon(icon);
        this.onClickListener = onClickListener;
    }

    public void setToolBarTitle(String title){
        mTvTitle.setText(title);
    }

    public void setToolBarTitle(String title,String color,float size){
        mTvTitle.setText(title);
        mTvTitle.setTextColor(Color.parseColor(color));
        mTvTitle.setTextSize(size);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityLink.removeActivity(this);
        bind.unbind();
    }

    /**
     * 显示toolbar
     */
    public void showToolBar(){
        mToolBar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏toolbar
     */
    public void hindToolBar(){
        mToolBar.setVisibility(View.GONE);
    }

    /**
     * 获取虚拟减半的高度
     * @return
     */
    private int getNavigationBarHeight() {
        boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if (!hasBackKey&&!hasMenuKey){
            Resources resources = getResources();
            int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /**
     * 既可以自定义返回按键的时间--为null时默认执行finish操作
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            if (onClickListener!=null){
                onClickListener.onClick();
            }else{
                finish();
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    public interface OnClickListener{
        void onClick();
    }

    public interface OnClickListenerRight{
        void onClick();
    }

}
