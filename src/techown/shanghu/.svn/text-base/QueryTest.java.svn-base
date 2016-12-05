package techown.shanghu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class QueryTest extends SQLiteOpenHelper {

	

	public QueryTest(Context context) {
		super(context, null, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	public Cursor querytest(String sql,String[] args){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cr=db.rawQuery(sql, args);
		return cr;
	}
}
