package mobidever.godutch.database.base;

import java.util.ArrayList;
import java.util.List;

import mobidever.godutch.database.base.SQLiteHelper.SQLiteDataTable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * 数据库操作类
 * @author Administrator
 *
 */
public abstract class SQLiteDALBase implements SQLiteDataTable{
	
	private Context m_Context;
	private SQLiteDatabase m_DataBase;
	
	public SQLiteDALBase(Context p_Context)
	{
		m_Context = p_Context;
	}
	
	protected Context getContext()
	{
		return m_Context;
	}
	
	public SQLiteDatabase getDataBase()
	{
		if(m_DataBase == null)
		{
			m_DataBase = SQLiteHelper.getInstance(m_Context).getWritableDatabase();
			if(m_DataBase == null) Log.i("sjgLog", "m_DataBase");
		}
		
		return m_DataBase;
	}
	
	public void beginTransaction()
	{
		m_DataBase.beginTransaction();
	}
	
	public void setTransactionSuccessful()
	{
		m_DataBase.setTransactionSuccessful();
	}
	
	public void endTransaction()
	{
		m_DataBase.endTransaction();
	}
	
	public int getCount(String p_Condition)
	{
		String _String[] = getTableNameAndPK();
		Cursor _Cursor = execSql("select " + _String[1] + " from " + _String[0] + " where 1=1 " + p_Condition);
		int _Count = _Cursor.getCount();
		_Cursor.close();
		return _Count;
	}
	
	public int getCount(String p_PK,String p_TableName, String p_Condition)
	{
		Cursor _Cursor = execSql("select " + p_PK + " from " + p_TableName + " where 1=1 " + p_Condition);
		int _Count = _Cursor.getCount();
		_Cursor.close();
		return _Count;
	}
	
	protected Boolean delete(String p_TableName, String p_Condition)
	{
		return getDataBase().delete(p_TableName, " 1=1 " + p_Condition, null) >= 0;
	}
	
	protected abstract String[] getTableNameAndPK();
	
	protected List getList(String p_SqlText)
	{
		Cursor _Cursor = execSql(p_SqlText);
		return cursorToList(_Cursor);
	}
	
	protected abstract Object findModel(Cursor p_Cursor);
	
	protected List cursorToList(Cursor p_Cursor)
	{
		List _List = new ArrayList();
		while(p_Cursor.moveToNext())
		{
			Object _Object = findModel(p_Cursor);
			_List.add(_Object);
		}
		p_Cursor.close();
		return _List;
	}
	
	public Cursor execSql(String p_SqlText)
	{
		return getDataBase().rawQuery(p_SqlText, null);
	}
}
