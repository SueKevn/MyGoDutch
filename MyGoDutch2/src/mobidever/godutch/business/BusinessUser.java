package mobidever.godutch.business;

import java.util.ArrayList;
import java.util.List;

import mobidever.godutch.business.base.BusinessBase;
import mobidever.godutch.database.sqlitedal.SQLiteDALUser;
import mobidever.godutch.model.ModelUser;
import android.content.ContentValues;
import android.content.Context;
/**
 * 用户业务层
 * @author Administrator
 *
 */
public class BusinessUser extends BusinessBase{

	private SQLiteDALUser mSQLiteDALUser;
	
	public BusinessUser(Context pContext) {
		super(pContext);
		mSQLiteDALUser = new SQLiteDALUser(pContext);
	}

	public boolean insertUser(ModelUser pInfo) {
		boolean _Result = mSQLiteDALUser.insertUser(pInfo);
		return _Result;
	}
	
	public boolean deleteUserByUserID(int pUserID){
		String _Condition = " and userid = " + pUserID;
		boolean _Result = mSQLiteDALUser.deleteUser(_Condition);
		return _Result;
	}
	
	public boolean updateUserByUserID(ModelUser pModelUser){
		String _Condition = " userid = " + pModelUser.getUserID();
		boolean _Result = mSQLiteDALUser.updateUser(_Condition, pModelUser);
		return _Result;
	}
	
	//获取没有被隐藏的用户
	public List<ModelUser> getNotHideUser(){
		return mSQLiteDALUser.getUser(" and state = 1 ");
	}
	
	public List<ModelUser> getUser(String pCondition){
		return mSQLiteDALUser.getUser(pCondition);
	}
	
	public ModelUser getModelUserByUserID(int pUserID){
		List<ModelUser> _List = mSQLiteDALUser.getUser(" and userid = " + 
									pUserID);
		if(_List.size() == 1){
			return _List.get(0);
		}else{
			return null;
		}
	}
	
	public List<ModelUser> getUserListUserID(String[] pUserID){
		List<ModelUser> _List = new ArrayList<ModelUser>();
		
		for (int i = 0; i < pUserID.length; i++) {
			_List.add(getModelUserByUserID(Integer.valueOf(pUserID[i])));
		}
		
		return _List;
	}
	
	public boolean IsExistUserByUserName(String pUserName,Integer pUserID)
	{
		String _Condition = " and username = '" + pUserName + "'";
		if(pUserID != null)
		{
			_Condition += " and userid <> " + pUserID;
		}
		List _List = mSQLiteDALUser.getUser(_Condition);
		if (_List.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean HideUserByUserID(int p_UserID)
	{
		String _Condition = " userid = " + p_UserID;
		ContentValues _ContentValues = new ContentValues();
		_ContentValues.put("state",0);
		Boolean _Result = mSQLiteDALUser.updateUser(_Condition, _ContentValues);

		if(_Result)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
}
