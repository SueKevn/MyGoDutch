package mobidever.godutch.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mobidever.godutch.R;
import mobidever.godutch.business.base.BusinessBase;
import mobidever.godutch.database.sqlitedal.SQLiteDALCategory;
import mobidever.godutch.model.ModelCategory;
import mobidever.godutch.model.ModelCategoryTotal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

public class BusinessCategory extends BusinessBase {
	
	private SQLiteDALCategory m_SqLiteDALCategory;
	private final String  TYPE_FLAG = " And TypeFlag= '" + getString(R.string.PayoutTypeFlag) + "'";
	
	public BusinessCategory(Context p_Context)
	{
		super(p_Context);
		m_SqLiteDALCategory = new SQLiteDALCategory(p_Context);
	}
	
	public Boolean InsertCategory(ModelCategory p_Info)
	{
		m_SqLiteDALCategory.beginTransaction();
		try {
			Boolean _Result = m_SqLiteDALCategory.insertCategory(p_Info);
			Boolean _Result2 = true;

			ModelCategory _ParentModelCategory = getModelCategoryByCategoryID(p_Info.getParentID());
			String _Path;
			if(_ParentModelCategory != null)
			{
				_Path = _ParentModelCategory.getPath() + p_Info.getCategoryID() + ".";
			}
			else {
				_Path = p_Info.getCategoryID() + ".";
			}
			
			p_Info.setPath(_Path);
			_Result2 = UpdateCategoryInsertTypeByCategoryID(p_Info);
			
			if(_Result && _Result2)
			{
				m_SqLiteDALCategory.setTransactionSuccessful();
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			m_SqLiteDALCategory.endTransaction();
		}
	}
	
	public Boolean DeleteCategoryByCategoryID(int p_CategoryID)
	{
		String _Condition = " CategoryID = " + p_CategoryID;
		Boolean _Result = m_SqLiteDALCategory.deleteCategory(_Condition);
		return _Result;
	}
	
	public Boolean deleteCategoryByPath(String p_Path) throws Exception
	{
		/*int _Count = m_SqLiteDALCategory.getCount("PayoutID", "v_Payout", " And Path Like '" + p_Path + "%'");
		
		if(_Count != 0)
		{
			throw new Exception(getString(R.string.ErrorMessageExistPayout));
		}
		
		String _Condition = " And Path Like '" + p_Path + "%'";
		Boolean _Result = m_SqLiteDALCategory.DeleteCategory(_Condition);
		return _Result;*/
		
		return true;
	}
	
	public Boolean UpdateCategoryInsertTypeByCategoryID(ModelCategory p_Info)
	{
		String _Condition = " CategoryID = " + p_Info.getCategoryID();
		Boolean _Result = m_SqLiteDALCategory.updateCategory(_Condition, p_Info);		
		
		if(_Result)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean UpdateCategoryByCategoryID(ModelCategory p_Info)
	{
		m_SqLiteDALCategory.beginTransaction();
		try {
			String _Condition = " CategoryID = " + p_Info.getCategoryID();
			Boolean _Result = m_SqLiteDALCategory.updateCategory(_Condition, p_Info);
			Boolean _Result2 = true;
			

			ModelCategory _ParentModelCategory = getModelCategoryByCategoryID(p_Info.getParentID());
			String _Path;
			if(_ParentModelCategory != null)
			{
				_Path = _ParentModelCategory.getPath() + p_Info.getCategoryID() + ".";
			}
			else {
				_Path = p_Info.getCategoryID() + ".";
			}
			
			p_Info.setPath(_Path);
			_Result2 = UpdateCategoryInsertTypeByCategoryID(p_Info);
			
			if(_Result && _Result2)
			{
				m_SqLiteDALCategory.setTransactionSuccessful();
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			m_SqLiteDALCategory.endTransaction();
		}
	}
	
	public Boolean HideCategoryByByPath(String p_Path)
	{
		String _Condition = " Path Like '" + p_Path + "%'";
		ContentValues _ContentValues = new ContentValues();
		_ContentValues.put("State",0);
		Boolean _Result = m_SqLiteDALCategory.updateCategory(_Condition, _ContentValues);

		if(_Result)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<ModelCategory> getCategory(String p_Condition)
	{
		return m_SqLiteDALCategory.getCategory(p_Condition);
	}
	
	public List<ModelCategory> getNotHideCategory()
	{
		return m_SqLiteDALCategory.getCategory(TYPE_FLAG + " And State = 1");
	}
	
	public int getNotHideCount()
	{
		return m_SqLiteDALCategory.getCount(TYPE_FLAG + " And State = 1");
	}
	
	public int getNotHideCountByParentID(int p_CategoryID)
	{
		return m_SqLiteDALCategory.getCount(TYPE_FLAG + " And ParentID = " + p_CategoryID + " And State = 1");
	}
	
	public List<ModelCategory> getNotHideRootCategory()
	{
		return m_SqLiteDALCategory.getCategory(TYPE_FLAG + " And ParentID = 0 And State = 1");
	}
	
	public List<ModelCategory> getNotHideCategoryListByParentID(int p_ParentID)
	{
		return m_SqLiteDALCategory.getCategory(TYPE_FLAG + " And ParentID = " + p_ParentID + " And State = 1");
	}
	
	public ModelCategory getModelCategoryByParentID(int p_ParentID)
	{
		List _List = m_SqLiteDALCategory.getCategory(TYPE_FLAG + " And ParentID = " + p_ParentID);
		if(_List.size() == 1)
		{
			return (ModelCategory)_List.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public ModelCategory getModelCategoryByCategoryID(int p_CategoryID)
	{
		List _List = m_SqLiteDALCategory.getCategory(TYPE_FLAG + " And CategoryID = " + p_CategoryID);
		if(_List.size() == 1)
		{
			return (ModelCategory)_List.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public ArrayAdapter getRootCategoryArrayAdapter()
	{
		List _List = getNotHideRootCategory();
		_List.add(0,"--«Î—°‘Ò--");
		ArrayAdapter _ArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, _List);
		_ArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return _ArrayAdapter;
	}
	
	public ArrayAdapter getAllCategoryArrayAdapter()
	{
		/*List _List = getNotHideCategory();
		String _Name[] = new String[_List.size()];
		for (int i = 0; i < _List.size(); i++) {
			_Name[i] = ((ModelCategory)_List.get(i)).getCategoryName();
		}
		ArrayAdapter _ArrayAdapter = new ArrayAdapter(getContext(), R.layout.common_auto_complete, _List);
		return _ArrayAdapter;*/
		
		return null;
	}
	
	public List<ModelCategoryTotal> CategoryTotalByRootCategory()
	{
		String _Condition = TYPE_FLAG + " And ParentID = 0 And State = 1";
		List<ModelCategoryTotal> _ModelCategoryTotalList = CategoryTotal(_Condition);
		
		return _ModelCategoryTotalList;
	}
	
	public List<ModelCategoryTotal> CategoryTotalByParentID(int p_ParentID)
	{
		String _Condition = TYPE_FLAG + " And ParentID = " + p_ParentID;
		List<ModelCategoryTotal> _ModelCategoryTotalList = CategoryTotal(_Condition);
		
		return _ModelCategoryTotalList;
	}
	
	public List<ModelCategoryTotal> CategoryTotal(String p_Condition)
	{
		String _Condition = p_Condition;
		Cursor _Cursor = m_SqLiteDALCategory.execSql("Select Count(PayoutID) As Count, Sum(Amount) As SumAmount, CategoryName From v_Payout Where 1=1 " + _Condition + " Group By CategoryName");
		List<ModelCategoryTotal> _ModelCategoryTotalList = new ArrayList<ModelCategoryTotal>();
		while (_Cursor.moveToNext()) {
			ModelCategoryTotal _ModelCategoryTotal = new ModelCategoryTotal();
			_ModelCategoryTotal.Count = _Cursor.getString(_Cursor.getColumnIndex("Count"));
			_ModelCategoryTotal.SumAmount = _Cursor.getString(_Cursor.getColumnIndex("SumAmount"));
			_ModelCategoryTotal.CategoryName = _Cursor.getString(_Cursor.getColumnIndex("CategoryName"));
			_ModelCategoryTotalList.add(_ModelCategoryTotal);
		}
		
		return _ModelCategoryTotalList;
	}
}
