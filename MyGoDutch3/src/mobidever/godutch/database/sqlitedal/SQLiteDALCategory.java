package mobidever.godutch.database.sqlitedal;

import java.util.Date;
import java.util.List;

import mobidever.godutch.R;
import mobidever.godutch.database.base.SQLiteDALBase;
import mobidever.godutch.model.ModelCategory;
import mobidever.godutch.utility.DateTools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteDALCategory extends SQLiteDALBase {

	public SQLiteDALCategory(Context p_Context) {
		super(p_Context);
	}

	public Boolean insertCategory(ModelCategory p_Info) {
		ContentValues _ContentValues = CreatParms(p_Info);
		Long p_NewID = getDataBase().insert("Category", null, _ContentValues);
		p_Info.setCategoryID(p_NewID.intValue());
		return p_NewID > 0;
	}
	
	public Boolean deleteCategory(String p_Condition)
	{
		return delete(getTableNameAndPK()[0], p_Condition);
	}
	
	public Boolean updateCategory(String p_Condition, ModelCategory p_Info)
	{
		ContentValues _ContentValues = CreatParms(p_Info);
		return getDataBase().update("Category", _ContentValues, p_Condition, null) > 0;
	}
	
	public Boolean updateCategory(String p_Condition,ContentValues p_ContentValues)
	{
		return getDataBase().update("Category", p_ContentValues, p_Condition, null) > 0;
	}
	
	public List<ModelCategory> getCategory(String p_Condition)
	{
		String _SqlText = "Select * From Category Where  1=1 " + p_Condition;
		return getList(_SqlText);
	}
	
	protected ModelCategory findModel(Cursor p_Cursor)
	{
		ModelCategory _ModelCategory = new ModelCategory();
		_ModelCategory.setCategoryID(p_Cursor.getInt(p_Cursor.getColumnIndex("CategoryID")));
		_ModelCategory.setCategoryName(p_Cursor.getString(p_Cursor.getColumnIndex("CategoryName")));
		_ModelCategory.setTypeFlag(p_Cursor.getString(p_Cursor.getColumnIndex("TypeFlag")));
		_ModelCategory.setParentID(p_Cursor.getInt(p_Cursor.getColumnIndex("ParentID")));
		_ModelCategory.setPath(p_Cursor.getString(p_Cursor.getColumnIndex("Path")));
		Date _CreateDate = DateTools.getDate(p_Cursor.getString(p_Cursor.getColumnIndex("CreateDate")), "yyyy-MM-dd HH:mm:ss");	
		_ModelCategory.setCreateDate(_CreateDate);
		_ModelCategory.setState((p_Cursor.getInt(p_Cursor.getColumnIndex("State"))));
		
		return _ModelCategory;
	}
	
	private void initDefaultData(SQLiteDatabase p_DataBase)
	{
		ModelCategory _ModelCategory = new ModelCategory();
		
		_ModelCategory.setTypeFlag(getContext().getString((R.string.PayoutTypeFlag)));
		_ModelCategory.setPath("");
		_ModelCategory.setParentID(0);
		String _InitDefaultCategoryNameArr[] = getContext().getResources().getStringArray(R.array.InitDefaultCategoryName);
		for(int i=0;i<_InitDefaultCategoryNameArr.length;i++)
		{
			_ModelCategory.setCategoryName(_InitDefaultCategoryNameArr[i]);
			
			ContentValues _ContentValues = CreatParms(_ModelCategory);
			Long _NewID = p_DataBase.insert("Category", null, _ContentValues);
			
			_ModelCategory.setPath(_NewID.intValue() + ".");
			_ContentValues = CreatParms(_ModelCategory);
			p_DataBase.update("Category", _ContentValues, " CategoryID = " + _NewID.intValue(), null);
		}
	}

	@Override
	public void onCreate(SQLiteDatabase p_DataBase) {
		StringBuilder s_CreateTableScript = new StringBuilder();
		
		s_CreateTableScript.append("		Create  TABLE Category(");
		s_CreateTableScript.append("				[CategoryID] integer PRIMARY KEY AUTOINCREMENT NOT NULL");
		s_CreateTableScript.append("				,[CategoryName] varchar(20) NOT NULL");
		s_CreateTableScript.append("				,[TypeFlag] varchar(20) NOT NULL");
		s_CreateTableScript.append("				,[ParentID] integer NOT NULL");
		s_CreateTableScript.append("				,[Path] text NOT NULL");
		s_CreateTableScript.append("				,[CreateDate] datetime NOT NULL");
		s_CreateTableScript.append("				,[State] integer NOT NULL");
		s_CreateTableScript.append("				)");
		
		p_DataBase.execSQL(s_CreateTableScript.toString());
		initDefaultData(p_DataBase);
	}

	@Override
	public void onUpgrade(SQLiteDatabase p_DataBase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getTableNameAndPK() {
		return new String[]{"Category","CategoryID"};
	}
	
	public ContentValues CreatParms(ModelCategory p_Info) {
		ContentValues _ContentValues = new ContentValues();
		_ContentValues.put("CategoryName", p_Info.getCategoryName());
		_ContentValues.put("TypeFlag", p_Info.getTypeFlag());
		_ContentValues.put("ParentID", p_Info.getParentID());
		_ContentValues.put("Path", p_Info.getPath());
		_ContentValues.put("CreateDate",DateTools.getFormatDateTime(p_Info.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
		_ContentValues.put("State",p_Info.getState());
		return _ContentValues;
	}

}
