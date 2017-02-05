package com.study.study;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/2/5.
 */

public class MyService extends Service{

    TimerTask timerTask;
    Timer timer;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!getForeground().equals("com.study.study")) {
                    Intent intent = new Intent(MyService.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 500);
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        timerTask.cancel();
    }

    public String getForeground() {

        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        //获取到当前所有进程
        List<ActivityManager.RunningAppProcessInfo> processInfoList = activityManager.getRunningAppProcesses();

        if (processInfoList ==null || processInfoList.isEmpty()) {
            return "";
        }

        //遍历进程列表，找到第一个前台进程
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return processInfo.processName;
            }
        }
        return "";
    }
}
