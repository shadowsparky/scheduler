package ru.shadowsparky.scheduler.carolinescheduler.NotificationsBrain;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import ru.shadowsparky.scheduler.carolinescheduler.R;

public class ScheduleNotification {
    public static final String CHANNEL_ID = "Оповещения от Кабана";
    Context context;
    public ScheduleNotification(Context context) {
        this.context = context;
    }

    public void scheduleNotification(Notification notification, int delay, int ID) {
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, ID);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    public Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Оповещение: ");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_round);
        return builder.build();
    }
}
