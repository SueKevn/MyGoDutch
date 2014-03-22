package mobidever.godutch.database.base;

import java.util.ArrayList;

import mobidever.godutch.R;

import android.content.Context;
/**
 * ���ݿ�������ã����ݿ��������ݿ�汾���൥��
 * @author Administrator
 *
 */
public class SQLiteDataBaseConfig {
	//���ݿ���
	private static final String DATABASE_NAME = "GoDutchDataBase";
	//�汾
	private static final int VERSION = 1;
	//����
	private static SQLiteDataBaseConfig INSTANCE; 
	//������
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

	/**��ȡ���ݿ�� */
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
