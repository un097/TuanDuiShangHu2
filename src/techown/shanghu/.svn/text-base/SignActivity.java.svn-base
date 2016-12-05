package techown.shanghu;
import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class SignActivity extends Activity {
	private  final float LENGTH_THRESHOLD = 1.0f;//最小长度
	private Gesture mGesture;
	private Button btn_sign_modify;
	private Button btn_sign_sure;
	private GestureOverlayView overlay;
	private String name,signPath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawable(null);//设置默认背景为空
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_sign);
		name=this.getIntent().getExtras().getString("name");
		signPath=this.getIntent().getExtras().getString("signPath");
		try {
			overlay = (GestureOverlayView) findViewById(R.id.gestures_overlay);
			overlay.setGestureStrokeWidth(11); //设置签名笔迹的粗细
			mGesture = null;
			btn_sign_sure = (Button) findViewById(R.id.btn_sign_sure);
			btn_sign_sure.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					addGesture();
				}

			});
			btn_sign_modify = (Button) findViewById(R.id.btn_sign_modify);
			btn_sign_modify.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					overlay.setFadeOffset(100);//设置间隔时间
					overlay.clear(true);
					mGesture = null;
					overlay.setFadeOffset(3600000);
				}

			});
			overlay.setFadeOffset(3600000);
			overlay.addOnGestureListener(new GesturesProcessor());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (mGesture != null) {

			outState.putParcelable("gesture", mGesture);
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		mGesture = savedInstanceState.getParcelable("gesture");

		if (mGesture != null) {
			final GestureOverlayView overlay = (GestureOverlayView) findViewById(R.id.gestures_overlay);
			overlay.post(new Runnable() {
				public void run() {
					overlay.setGesture(mGesture);
				}
			});
		}
	}

	/**
	 * 增加手势的按钮
	 * 
	 * @param v
	 */
	public void addGesture() {
		Intent intent=new Intent();
		if (mGesture != null) {
			GestureLibrary store =  GestureLibraries.fromFile(signPath+"/"+name); // 注1
			store.addGesture(name, mGesture);
			store.save();
			intent.putExtra("name", name);
			setResult(RESULT_OK,intent);
		} else {
			setResult(RESULT_CANCELED,intent);
		}

		finish();
	}

	/**
	 * 取消手势
	 * 
	 * @param v
	 */
	public void cancelGesture(View v) {
		setResult(RESULT_CANCELED);
		finish();
	}

	/**
	 * 手势监听着
	 * 
	 * @author Administrator
	 * 
	 */
	private class GesturesProcessor implements
			GestureOverlayView.OnGestureListener {
		public void onGestureStarted(GestureOverlayView overlay,
				MotionEvent event) {
			mGesture = null;
		}

		public void onGesture(GestureOverlayView overlay, MotionEvent event) {
		}

		public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
			// 获取在GestureOverlayView手势
			mGesture = overlay.getGesture(); // 注2
			if (mGesture.getLength() < LENGTH_THRESHOLD) {
				overlay.clear(false);
			}
		}

		public void onGestureCancelled(GestureOverlayView overlay,
				MotionEvent event) {
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			finish();
			break;

		}
		return super.onKeyDown(keyCode, event);
	}

}
