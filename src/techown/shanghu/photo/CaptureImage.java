package techown.shanghu.photo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import techown.shanghu.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class CaptureImage extends Activity
{
	SurfaceView sView;
	SurfaceHolder surfaceHolder;
	int screenWidth, screenHeight;
	// 定义系统所用的照相机
	Camera camera;
	ImageButton bn;
	//是否在浏览中
	boolean isPreview = false;
	
	int photoWidth, photoHeight;
	int cameraDir = 0;/*0：后置， 1：前置*/
	String photoName;
	
    @Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 设置全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.piccapture);
		WindowManager wm = (WindowManager) getSystemService(
			Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		// 获取屏幕的宽和高
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		System.out.println("screenWidth: "+screenWidth+", screenHeight:"+screenHeight);
		
		initPhotoInfo();
		
		bn = (ImageButton)findViewById(R.id.bn);
		

		// 获取界面中SurfaceView组件
		sView = (SurfaceView) findViewById(R.id.sView);
		// 获得SurfaceView的SurfaceHolder
		surfaceHolder = sView.getHolder();
		// 为surfaceHolder添加一个回调监听器
		surfaceHolder.addCallback(new Callback()
		{
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height)
			{
			}
			public void surfaceCreated(SurfaceHolder holder)
			{
				// 打开摄像头
				initCamera();
			}
			public void surfaceDestroyed(SurfaceHolder holder)
			{
				// 如果camera不为null ,释放摄像头
				if (camera != null)
				{
					if (isPreview)
						camera.stopPreview();
					camera.release();
					camera = null;
				}
			}		
		});
		// 设置该SurfaceView自己不维护缓冲    
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		bn.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				// TODO Auto-generated method stub
        		if (camera != null)
				{
					// 拍照
					camera.takePicture(null, null , myjpegCallback);
					bn.setEnabled(false);
				}
			}
		});
	}
    
    public void initPhotoInfo(){
		Intent i = getIntent();
		photoWidth = i.getIntExtra("photoWdith", 1024);
		photoHeight = i.getIntExtra("photoHeight", 768);
		cameraDir = i.getIntExtra("cameraDir", 0);
    }

	private void initCamera()
	{
		if (!isPreview)
		{
			camera = Camera.open();
		}
		if (camera != null && !isPreview)
		{
			try
			{
				if(camera != null){
					camera.release();
					camera = null;
				}
				if(cameraDir == 0){
					camera = Camera.open();
				}
				else if(cameraDir == 1){
					camera = Camera.open(1);
				}
				
				//camera = Camera.open();//chengyuan add
				Camera.Parameters parameters = camera.getParameters();
				// 设置预览照片的大小
				Size size = parameters.getPreviewSize();
				Log.i("CYQ", "w:" + size.width + "," + "h:" + size.height);
				// 每秒显示4帧
				parameters.setPreviewFrameRate(4);
				// 设置图片格式
				parameters.setPictureFormat(PixelFormat.JPEG);
				// 设置JPG照片的质量
				parameters.set("jpeg-quality", 85);
				//设置照片的大小
				parameters.setPictureSize(photoWidth, photoHeight);
				
				camera.setParameters(parameters);
				
				//通过SurfaceView显示取景画面
				camera.setPreviewDisplay(surfaceHolder);
				// 开始预览
				camera.startPreview();
				// 自动对焦
				camera.autoFocus(null);
			}
			catch (Exception e)
			{
				techown.shanghu.https.Log.Instance().WriteLog("团队CaptureImage:"+e.getMessage());
			}
			isPreview = true;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			// 当用户单击照相键、中央键时执行拍照
			case KeyEvent.KEYCODE_DPAD_CENTER:
			case KeyEvent.KEYCODE_CAMERA:
				if (camera != null && event.getRepeatCount() == 0)
				{
					// 拍照
					camera.takePicture(null, null , myjpegCallback);
					return true;
				}
				break;
		}		
		return super.onKeyDown(keyCode, event);
	}
	
	PictureCallback myjpegCallback = new PictureCallback()
	{
		public void onPictureTaken(byte[] data, Camera camera)
		{
			// 根据拍照所得的数据创建位图
			
			final Bitmap bm = BitmapFactory.decodeByteArray(data
					, 0, data.length);
			// 创建一个位于SD卡上的文件
					
			File file = new File("/sdcard/"
					+"tmpPhoto.jpg");
			FileOutputStream outStream = null;
			try
			{
				// 打开指定文件对应的输出流
				outStream = new FileOutputStream(file);
				// 把位图输出到指定文件中
				bm.compress(CompressFormat.JPEG, 100, outStream);
				outStream.close();
			}
			catch (IOException e)
			{
				techown.shanghu.https.Log.Instance().WriteLog("团队CaptureImage:"+e.getMessage());
			}
			
			//重新浏览
			camera.stopPreview();
//			camera.startPreview();
			isPreview = true;
			CaptureImage.this.finish();
		}
	};
}

