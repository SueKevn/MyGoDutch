package mobidever.godutch.database.base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import mobidever.godutch.utility.Reflection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/***
 * 基本数据库操作
 * @author Administrator
 *
 */
public class SQLiteHelper extends SQLiteOpenHelper{

	private static SQLiteDataBaseConfig SQLITE_DATABASE_CONFIG;
	
	private Context mContext;
	
	private static SQLiteHelper INSTANCE;
	
	private Reflection mReflection;
	
	//数据库表接口
	public interface SQLiteDataTable
	{
		public void onCreate(SQLiteDatabase p_DataBase);
		public void onUpgrade(SQLiteDatabase p_DataBase);
	}
	
	public SQLiteHelper(Context pContext){
		super(pContext, SQLITE_DATABASE_CONFIG.getDataBaseName(), null, SQLITE_DATABASE_CONFIG.getVersion());
		if(pContext== null) Log.i("sjglog", "pContext");
		if(SQLITE_DATABASE_CONFIG.getDataBaseName()== null) Log.i("sjglog", "SQLITE_DATABASE_CONFIG.getDataBaseName()");
		mContext = pContext;
	}
	
	public static SQLiteHelper getInstance(Context pContext){
		if(INSTANCE == null){
			SQLITE_DATABASE_CONFIG = SQLiteDataBaseConfig.getInstance(pContext);
			INSTANCE = new SQLiteHelper(pContext);
		}
		return INSTANCE;
	}
	
	@Override
	public void onCreate(SQLiteDatabase pDataBase) {
		ArrayList<String> _ArrayList = SQLITE_DATABASE_CONFIG.getTables();
		mReflection = new Reflection();
		for (int i = 0; i < _ArrayList.size(); i++) {
			try {
				SQLiteDataTable _SQLiteDataTable = (SQLiteDataTable)mReflection.newInstance(
						_ArrayList.get(i),
						new Object[]{mContext},
						new Class[]{Context.class});
				_SQLiteDataTable.onCreate(pDataBase);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
