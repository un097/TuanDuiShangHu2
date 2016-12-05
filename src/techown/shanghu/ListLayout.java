package techown.shanghu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListLayout {
	private Context context;
	private List<String> strList;

	public ListLayout(Context context,List<String> strList) {
		this.context = context;
		this.strList=strList;
		// TODO Auto-generated constructor stub
	}

	public LinearLayout getLayout(List<OnClickListener>  cameraListener) {
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		for (int i = 0; i < strList.size(); i++) {
			View view = LayoutInflater.from(context).inflate(R.layout.shoplist,
					null);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, 58);
			//params.setMargins(0, 5, 0, 0);
			view.setLayoutParams(params);
			TextView testView=(TextView) view.findViewById(R.id.testView);
			testView.setText(strList.get(i));
			
			testView.setOnClickListener(cameraListener.get(i));
			
			
			
			linearLayout.addView(view);
		}

		return linearLayout;
	}


}
