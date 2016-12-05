package techown.shanghu;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpinnerAdapter2 extends BaseAdapter {
	private Context mContext;
	private Object[]numstr;
	public SpinnerAdapter2(Context c,Object[]str) {
		mContext = c;		
		numstr=str;
	}
	public int getCount() {
		return numstr.length;
	}
	public Object getItem(int position) {
		return position;
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LinearLayout ll=new LinearLayout(mContext);
		ll.setGravity(Gravity.CENTER);
		TextView tv=new TextView(mContext);
		tv.setGravity(Gravity.CENTER);
		tv.setText(numstr[position].toString());//设置内容	
		tv.setHeight(50);
		tv.setTextSize(mContext.getResources().getDimension(R.dimen.btnTextSizebody));//设置字体大小;
		tv.setTextColor(mContext.getResources().getColor(R.color.black));//设置字体颜色
		ll.addView(tv);		
		return ll;	
	}
}
