package com.example.myapplication.BroadcastReciver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.Firebase.FbDao;
import com.example.myapplication.Model.Game;
import com.example.myapplication.R;

import java.util.Date;

public class ThongBao extends BroadcastReceiver {
    //lớp tiếp nhận thông báo
    @Override
    public void onReceive(Context context, Intent intent) {
        Game game = (Game) intent.getSerializableExtra("game");
        String time = intent.getStringExtra("time");
        Bitmap bitmap;
        if(intent.getAction().equals("MyAction")){
            if (String.valueOf(game.getImgGame())==null){
                bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.loading);
            }else {
                bitmap = BitmapFactory.decodeResource(context.getResources(),game.getImgGame());
            }
            Bitmap imgApp = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo2);
            Notification notification = new NotificationCompat.Builder(context, ChannelTB.CHANNEL_ID) // khai báo compat
                    .setLargeIcon(imgApp)
                    .setContentTitle("Hết giờ rùi : "+time+"")
                    .setContentText("Hy vọng bạn thích trò chơi của chúng tôi !")
                    .setSmallIcon(R.drawable.logo2)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                    .build();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(getTimeLocal(), notification);
        }
    }

    private int getTimeLocal() {
        return (int) new Date().getTime();
    }
}
