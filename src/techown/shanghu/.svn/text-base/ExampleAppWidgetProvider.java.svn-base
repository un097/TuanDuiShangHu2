package techown.shanghu;
/*
 * ´óÍ¼±ê
 */
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		 final int N = appWidgetIds.length;

		  for (int i=0; i<N; i++) {
	            int appWidgetId = appWidgetIds[i];

	            Intent intent = new Intent(context, TuanDuiShangHuActivity.class);
	            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

	            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.myappwidget);
	            views.setOnClickPendingIntent(R.id.imageView1, pendingIntent);

	            // Tell the AppWidgetManager to perform an update on the current app widget
	            appWidgetManager.updateAppWidget(appWidgetId, views);
		  }
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}
}

