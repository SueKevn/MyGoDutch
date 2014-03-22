package mobidever.godutch.database.sqlitedal;

import java.util.Date;
import java.util.List;

import mobidever.godutch.R;
import mobidever.godutch.database.base.SQLiteDALBase;
import mobidever.godutch.model.ModelUser;
import mobidever.godutch.utility.DateTools;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteDALUser extends SQLiteDALBase {

	public SQLiteDALUser(Context p_Context) {
		super(p_Context);
	}

	@Override
	public void onCreate(SQLiteDatabase pDataBase) {
		StringBuilder s_CreateTableScript = new StringBuilder();

		s_CreateTableScript.append("		create  table user(");
		s_CreateTableScript
				.append("				userid integer PRIMARY KEY AUTOINCREMENT NOT NULL");
		s_CreateTableScript.append("				,username varchar(10) NOT NULL");
		s_CreateTableScript.append("				,createdate datetime NOT NULL");
		s_CreateTableScript.append("				,state integer NOT NULL");
		s_CreateTableScript.append("				)");

		pDataBase.execSQL(s_CreateTableScript.toString());
		//初始化数据
		initDefaultData(pDataBase);
	}

	@Override
	public void onUpgrade(SQLiteDatabase p_DataBase) {

	}

	public ContentValues createParms(ModelUser p_Info) {
		ContentValues _ContentValues = new ContentValues();
		_ContentValues.put("username", p_Info.getUserName());
		_ContentValues.put("createdate", DateTools.getFormatDateTime(
				p_Info.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
		_ContentValues.put("state", p_Info.getState());
		return _ContentValues;
	}
	
	// 插入数据
	public boolean insertUser(ModelUser pModelUser) {
		ContentValues _ContentValues = createParms(pModelUser);
		long _NewID = getDataBase().insert(getTableNameAndPK()[0], null,
				_ContentValues);
		pModelUser.setUserID((int) _NewID);

		return _NewID > 0;
	}

	// 删除用户
	public boolean deleteUser(String pCondition) {
		return delete(getTableNameAndPK()[0], pCondition);
	}
	
	//更新用户
	public boolean updateUser(String pCondition, ModelUser pModelUser) {
		ContentValues _ContentValues = createParms(pModelUser);
		return getDataBase().update(getTableNameAndPK()[0], _ContentValues,
				pCondition, null) > 0;
	}
	
	public Boolean updateUser(String p_Condition,ContentValues pContentValues)
	{
		return getDataBase().update("user", pContentValues, p_Condition, null) > 0;
	}
	
	//查询用户
	public List<ModelUser> getUser(String pCondition){
		String _SqlText = "select * from user where 1=1 " + pCondition;
//		if(getList(_SqlText) == null) Log.i("sjgLog", "sqltext....");
		return getList(_SqlText);
	}

	@Override
	protected String[] getTableNameAndPK() {
		return new String[] { "user", "userid" };
	}
	
	//获取model对象
	@Override
	protected Object findModel(Cursor pCursor) {
		ModelUser _ModelUser = new ModelUser();
		_ModelUser.setUserID(pCursor.getInt(pCursor.getColumnIndex("userid")));
		_ModelUser.setUserName(pCursor.getString(pCursor.getColumnIndex("username")));
		Date _CreateDate = DateTools.getDate(pCursor.getString(pCursor.getColumnIndex("createdate")), "yyyy-MM-dd HH:mm:ss");	
		_ModelUser.setCreateDate(_CreateDate);
		_ModelUser.setState((pCursor.getInt(pCursor.getColumnIndex("state"))));
		
		return _ModelUser;
	}

	private void initDefaultData(SQLiteDatabase pDatabase){
		ModelUser _ModelUser = new ModelUser();
		String _UserName[] = getContext().getResources().getStringArray(R.array.InitDefaultUserName);
		for (int i = 0; i < _UserName.length; i++) {
			_ModelUser.setUserName(_UserName[i]);
			ContentValues _ContentValues = createParms(_ModelUser);
			
			pDatabase.insert(getTableNameAndPK()[0], null, _ContentValues);
		}
				
	}

}
