package techown.shanghu.photo;

import java.io.File;

import techown.shanghu.PadUtiltiy;
import techown.shanghu.R;
import techown.shanghu.TuanDuiShangHuActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class PictureDialog5 {
	Dialog dlg;
	
	ImageView preview;
	Button backBtn;
	Button deleteBtn;
	Button rePhotoBtn;
	Context context;
	Bitmap tempbit;
	String temppicname;
	PadUtiltiy mPadUtiltiy;
	//String[] arrayStr;
	
	public PictureDialog5(Context context,Bitmap bit,String picname){
	
		this.context = context;
		this.tempbit = bit;
		this.temppicname=picname;
	}
	
	public void showListDialog() {
		LayoutInflater factory = LayoutInflater.from(context);
		// 得到自定义对话框
		View DialogView = (View)factory.inflate(R.layout.paizhao, null);
		dlg = new Dialog(context, R.style.FullScreenDialog);
		mPadUtiltiy = new PadUtiltiy(context);
		preview = (ImageView)DialogView.findViewById(R.id.preview);
		backBtn = (Button)DialogView.findViewById(R.id.backBtn);
		deleteBtn = (Button)DialogView.findViewById(R.id.deleteBtn);
		rePhotoBtn = (Button)DialogView.findViewById(R.id.rePhotoBtn);
		
		backBtn.setOnClickListener(new backBtnListener());
		deleteBtn.setOnClickListener(new deleteBtnListener());
		rePhotoBtn.setOnClickListener(new rePhotoBtnListener());			
		preview.setImageBitmap(tempbit);
//		preview.setRotation(270);

		// 创建对话框

		dlg.setContentView(DialogView);
		dlg.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关
		dlg.show();
	}
	
	class backBtnListener implements OnClickListener{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			dlg.dismiss();
		}
	}
	
	class deleteBtnListener implements OnClickListener{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Message msg = Message.obtain();
			Bundle b = new Bundle();
			b.putString("photoid",temppicname);
			msg.setData(b);
			msg.what =3;/*执行删除照片的操作*/
			((TakePhotoActivity2)context).handlerphoto.sendMessage(msg);
			dlg.dismiss();
		}
	}
	class rePhotoBtnListener implements OnClickListener{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Message msg = Message.obtain();
			Bundle b = new Bundle();
			b.putString("photoid",temppicname);
			msg.setData(b);
			msg.what = 4;/*执行重拍照片的操作*/
			((TakePhotoActivity2)context).handlerphoto.sendMessage(msg);
			
//			if (TuanDuiShangHuActivity.device == 0) {
//				Intent i=new Intent("android.media.action.IMAGE_CAPTURE");
//				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/mnt/sdcard/tmpPhoto")));
//				i.putExtra("cameraid",0);
//				i.putExtra("width",640);
//				i.putExtra("height",480);
//				((TakePhotoActivity2)context).startActivityForResult(i, 4);
//			} else if (TuanDuiShangHuActivity.device == 1) {
//				Intent i = new Intent();
//	    		i.setClass(context, CaptureImage.class);
//	    		i.putExtra("cameraDir",0);
//				i.putExtra("photoWdith",640);
//				i.putExtra("photoHeight",480);
//				((TakePhotoActivity2)context).startActivityForResult(i, 4);\
				mPadUtiltiy.takeCamera(0, 640, 480, "/mnt/sdcard/tmpPhoto.jpg");
				TakePhotoActivity2.request =4;
//			}
			
			dlg.dismiss();
			
		}
	}

}
