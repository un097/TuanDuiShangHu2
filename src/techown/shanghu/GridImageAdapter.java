package techown.shanghu;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;


public class GridImageAdapter extends BaseAdapter{
	private Context mContext;
	private String[] textstr;
	public GridImageAdapter(Context c,String[]str) {
		mContext = c;
		textstr=str;
	}
	public int getCount() {
		return textstr.length;
	}
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		
		FrameLayout ll=new FrameLayout(mContext);
			
		//初始化ImageView
		TextView  tv=new TextView(mContext);
		tv.setText(textstr[position]);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(30);
		tv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.myselector_etdialog));
		ll.addView(tv);//添加到LinearLayout中						
		return ll;
		
	}
}
