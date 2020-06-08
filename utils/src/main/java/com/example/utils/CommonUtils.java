package com.example.utils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.hb.dialog.dialog.ConfirmDialog;
import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.v2.Notification;
import com.kongzue.dialog.v2.TipDialog;
import com.lcw.library.imagepicker.ImagePicker;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.kongzue.dialog.v2.DialogSettings.STYLE_MATERIAL;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;
import static com.kongzue.dialog.v2.Notification.TYPE_NORMAL;


/**
 * 大神之路，代码封装，大大减少你的开发时间
 * 通用工具类封装
 */
public class CommonUtils {

    /**
     * 向后台带参数的Post请求
     * @关键字 key
     * @传递的参数 text
     * @请求地址 url
     */
    public static void PostHttpValue(final String key, final String text, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行Http向后端 post请求
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put(key, text);
                client.post(url,params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                    }

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                    }
                });
            }
        }).start();
    }

    /**
     * 向后台带参数的Post请求
     * @关键字 key
     * @传递的参数 text
     * @请求地址 url
     */
    public static void PostHttpValue2(final String key, final String text, final String key2, final String text2, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行Http向后端 post请求
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put(key, text);
                params.put(key2, text2);
                client.post(url,params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                    }

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                    }
                });
            }
        }).start();
    }

    /**
     * 向后台无参数的Post请求
     * @请求地址 url
     */
    public static void PostHttpNoValue(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //进行Http向后端 post请求
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                client.post(url,params,new AsyncHttpResponseHandler(){
                    @Override
                    public void onFailure(Throwable error, String content) {
                        super.onFailure(error, content);
                    }

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                    }
                });
            }
        }).start();
    }

    /**
     * 存储信息
     * @存储名 Mode
     * @关键字 key
     * @需要存储的值 text
     */
    public static void SaveInfo(Context context,String Mode,String key,String text){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        preferences.edit().putString(key, text).apply();
    }

    /**
     * 获取存储信息
     * @存储名 Mode
     * @关键字 key
     * @return
     */
    public static String SharedInfo(Context context,String Mode, String key){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        String text = preferences.getString(key,null);
        return text;
    }

    /**
     * 清除保存的存储信息
     * @存储名 Mode
     */
    public static void ClearInfo(Context context,String Mode){
        SharedPreferences preferences = context.getSharedPreferences(Mode, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }

    /**
     * @上下文 context
     * @需要弹出的语句 text
     */
    public static void DialogShow(Context context,String text){
        DialogSettings.style = STYLE_MATERIAL;  //对话框为IOS风格
        DialogSettings.tip_theme = THEME_LIGHT;  //设置提示框主题为亮色主题
        DialogSettings.use_blur = true;
        TipDialog.show(context, text, TipDialog.SHOW_TIME_LONG, TipDialog.TYPE_WARNING);
    }

    /**
     *
     * @上下文 context
     * @消息 message
     * @要跳转到的class页面 cls
     */
    public static void SelectDialogShow(final Context context, String message, final Class<?> cls){
        ConfirmDialog confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.ic_launcher).setMsg(message);
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
                //点击确定跳转到指定的页面
                IntentToPage(context,cls);//挑转到页面
            }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }

    /**
     * @上下文 context
     * @消息 message
     * @点击确定消息 messageshow
     */
    public static void SelectDialogMessageShow(final Context context, String message, final String messageshow){
        ConfirmDialog confirmDialog = new ConfirmDialog(context);
        confirmDialog.setLogoImg(R.mipmap.ic_launcher).setMsg(message);
        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
            @Override
            public void ok() {
                //点击确定弹出消息
                DialogShow(context,messageshow);
            }

            @Override
            public void cancel() {
            }
        });
        confirmDialog.show();
    }

    /**
     *
     * @上下文 context
     * @要跳转到的class页面 cls
     */
    public static void IntentToPage(Context context,Class<?> cls){
        context.startActivity(new Intent(context, cls));//挑转到指定class页面
    }

    /**
     * 全屏
     * @param activity
     */
    public static void QuanPing(Activity activity){
        //沉浸式
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }

    /**
     * 3秒后销毁页面
     * @上下文 context
     * @要跳转到的class cls
     */
    public static void YanChi(final Activity activity){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               activity.finish();
            }
        },3000);
    }

    /**
     * 3秒后跳转
     * @上下文 context
     * @要跳转到的class cls
     */
    public static void YanChi3s(final Context context, final Activity activity, final Class<?> cls){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentToPage(context,cls);
                activity.finish();
            }
        },3000);
    }

    /**
     *
     * @上下文 context
     * @单词 text
     */
    public static void Fayin(Context context,String text){
        String voiceurl =  "http://media.shanbay.com/audio/us/";//发音地址
        final MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse( voiceurl+text+".mp3"));
        mediaPlayer.start();//开始播放
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();//释放
            }
        });
    }

    /**
     * 仿微信消息通知
     * @param context
     */
    public static void FangWeixinDialog(Context context,String text){
        Notification.show(context, 1, R.mipmap.ic_launcher, context.getString(R.string.app_name), text, Notification.SHOW_TIME_LONG,TYPE_NORMAL );
    }

    /**
     * 关键文字颜色变化
     * @全文 text
     * @关键字 keyword
     * @return
     */
    public SpannableString MatcherSearchText(Context context ,String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new TextAppearanceSpan(context,R.color.actionsheet_red), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//new ForegroundColorSpan(color)
        }
        return ss;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String CurrentTime(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        return time;
    }

    /**
     * 图片上传post请求
     * @param imagePath
     * @param url
     * @return
     */
    public static String doPost(String imagePath,String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        String result = "error";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.addFormDataPart("image", imagePath,
        RequestBody.create(MediaType.parse("image/png"), new File(imagePath)));
        RequestBody requestBody = builder.build();
        Request.Builder reqBuilder = new Request.Builder();
        Request request = reqBuilder.url(url).post(requestBody).build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
//            Log.d(TAG, "响应码 " + response.code());
            if (response.isSuccessful()) {
                String resultValue = response.body().string();
//                Log.d(TAG, "响应体 " + resultValue);
                return resultValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 选择相册中的图片
     * @param activity
     * @param requestCode
     */
    public static void SelectImage(Activity activity,int requestCode){
        ImagePicker.getInstance()
                .setTitle("选择图片")//设置标题
                .showCamera(true)//设置是否显示拍照按钮
                .showImage(true)//设置是否展示图片
                .showVideo(false)//设置是否展示视频
                .setSingleType(true)//设置图片视频不能同时选择
                .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
//                        .setImagePaths(mImageList)//保存上一次选择图片的状态，如果不需要可以忽略
                .setImageLoader(new GlideLoader())//设置自定义图片加载器
                .start(activity, requestCode);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
    }

    /**
     * 解决ScrollView中ListView只显示一行的问题
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * OKHttp异步Get请求
     * @请求地址 url
     * @回调 callback
     */
    public static void OkHttpGet(String url,Callback callback){
        // 异步GET请求
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * OkHttpPost请求
     * @参数关键字 key
     * @参数值 value
     * @请求地址 url
     * @回调函数 callback
     */
    public static void OKHttpPost(String key,String value,String url,Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add(key, value)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 保持不息屏
     * @param activity
     */
    public static void KeepScreenOn(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 去除状态栏
     * @param activity
     */
    public static void HideStatusBar(Activity activity) {
        ImmersionBar.with(activity).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();
    }

    /**
     * 设置状态栏颜色
     * @param activity
     * @颜色值 color
     */
    public static void SetSystemBarColor(Activity activity,int color) {
        ImmersionBar.with(activity).statusBarColor(color);
    }

    /**
     * 全屏
     * @param activity
     */
    public static void SetFullScreen(Activity activity) {
        ImmersionBar.with(activity).init();
    }

    /**
     * 再按一次退出提醒
     * @上下文 context
     */
    public static void QuitShow(Activity context){
        long waitTime = 2000;
        long touchTime = 0;
        long currentTime = System.currentTimeMillis();
        if((currentTime-touchTime)>=waitTime) {
            //让Toast的显示时间和等待时间相同
            Toast.makeText(context, "再按一次退出", (int)waitTime).show();
            touchTime = currentTime;
        }else {
            context.finish();
        }
    }

}
