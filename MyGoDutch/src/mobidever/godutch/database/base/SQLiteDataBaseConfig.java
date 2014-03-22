package mobidever.godutch.database.base;

import java.util.ArrayList;

import mobidever.godutch.R;

import android.content.Context;
/**
 * 数据库基本配置：数据库名、数据库版本、类单例
 * @author Administrator
 *
 */
public class SQLiteDataBaseConfig {
	//数据库名
	private static final String DATABASE_NAME = "GoDutchDataBase";
	//版本
	private static final int VERSION = 1;
	//单例
	private static SQLiteDataBaseConfig INSTANCE; 
	//上下文
	private static Context mContext;
	
	private SQLiteDataBaseConfig(){
		
	}
	
	public static SQLiteDataBaseConfig getInstance(Context pContext){
		if(INSTANCE == null){
			INSTANCE = new SQLiteDataBaseConfig();
			mContext = pContext;
		}
		return INSTANCE;
	}
	
	public String getDataBaseName(){
		return DATABASE_NAME;
	}
	
	public int getVersion(){
		return VERSION;
	}

	/**获取数据库表 */
	public ArrayList<String> getTables(){
		ArrayList<String> _ArrayList =  new ArrayList<String>();
		
		String[] _SQLiteDALClassName = mContext.getResources()
				.getStringArray(R.array.SQLiteDALClassName);
		String _PackagePath = mContext.getPackageName() + ".database.sqlitedal.";
		for (int i = 0; i < _SQLiteDALClassName.length; i++) {
			_ArrayList.add(_PackagePath + _SQLiteDALClassName[i]);
		}
		
		return _ArrayList;
	}
	
}
