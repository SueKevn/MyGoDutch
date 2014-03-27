package mobidever.godutch.business;

import java.util.List;

import mobidever.godutch.R;
import mobidever.godutch.business.base.BusinessBase;
import mobidever.godutch.database.sqlitedal.SQLiteDALPayout;
import mobidever.godutch.model.ModelPayout;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;

public class BusinessPayout extends BusinessBase {
	
	private SQLiteDALPayout m_SqLiteDALPayout;
	
	public BusinessPayout(Context p_Context)
	{
		super(p_Context);
		m_SqLiteDALPayout = new SQLiteDALPayout(p_Context);
	}
	
	public Boolean InsertPayout(ModelPayout p_Info)
	{
		Boolean _Result = m_SqLiteDALPayout.insertPayout(p_Info);
		
		if(_Result)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean DeletePayoutByPayoutID(int p_PayoutID)
	{
		String _Condition = " And PayoutID = " + p_PayoutID;
		Boolean _Result = m_SqLiteDALPayout.deletePayout(_Condition);
		return _Result;
	}
	
	public Boolean DeletePayoutByAccountBookID(int p_AccountBookID)
	{
		String _Condition = " And AccountBookID = " + p_AccountBookID;
		Boolean _Result = m_SqLiteDALPayout.deletePayout(_Condition);
		return _Result;
	}
	
	public Boolean UpdatePayoutByPayoutID(ModelPayout p_Info)
	{
		String _Condition = " PayoutID = " + p_Info.getPayoutID();
		Boolean _Result = m_SqLiteDALPayout.updatePayout(_Condition, p_Info);
		
		if(_Result)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<ModelPayout> getPayout(String p_Condition)
	{
		return m_SqLiteDALPayout.getPayout(p_Condition);
	}
	
	public int getCount()
	{
		return m_SqLiteDALPayout.getCount("");
	}

	public List<ModelPayout> getPayoutByAccountBookID(int p_AccountBookID)
	{
		String _Condition = " And AccountBookID = " + p_AccountBookID + " Order By PayoutDate DESC,PayoutID DESC";
		return m_SqLiteDALPayout.getPayout(_Condition);
	}
	
	public String getPayoutTotalMessage(String p_PayoutDate,int p_AccountBookID)
	{
		String _Total[] = getPayoutTotalByPayoutDate(p_PayoutDate,p_AccountBookID);
		return getContext().getString(R.string.TextViewTextPayoutTotal, new Object[]{_Total[0],_Total[1]});
	}
	
	private String[] getPayoutTotalByPayoutDate(String p_PayoutDate,int p_AccountBookID)
	{
		String _Condition = " And PayoutDate = '" + p_PayoutDate + "' And AccountBookID = " + p_AccountBookID;
		return getPayoutTotal(_Condition);
	}
	
	public String[] getPayoutTotalByAccountBookID(int p_AccountBookID)
	{
		String _Condition = " And AccountBookID = " + p_AccountBookID;
		return getPayoutTotal(_Condition);
	}
	
	private String[] getPayoutTotal(String p_Condition)
	{
		String _SqlText = "Select ifnull(Sum(Amount),0) As SumAmount,Count(Amount) As Count From Payout Where 1=1 " + p_Condition;
		String _Total[] = new String[2];
		Cursor _Cursor = m_SqLiteDALPayout.execSql(_SqlText);
		if(_Cursor.getCount() == 1)
		{
			while(_Cursor.moveToNext())
			{
				_Total[0] = _Cursor.getString(_Cursor.getColumnIndex("Count"));
				_Total[1] = _Cursor.getString(_Cursor.getColumnIndex("SumAmount"));
			}
		}
		return _Total;
	}
	
	public List<ModelPayout> getPayoutOrderByPayoutUserID(String p_Condition)
	{
		p_Condition += " Order By PayoutUserID";
		List<ModelPayout> _List = getPayout(p_Condition);
		if(_List.size() > 0)
		{
			return _List;
		}
		
		return null;
	}
	
	public String[] getPayoutDateAndAmountTotal(String p_Condition)
	{
		String _SqlText = "Select Min(PayoutDate) As MinPayoutDate,Max(PayoutDate) As MaxPayoutDate,Sum(Amount) As Amount From Payout Where 1=1 " + p_Condition;
		String _PayoutTotal[] = new String[3];
		Cursor _Cursor = m_SqLiteDALPayout.execSql(_SqlText);
		if(_Cursor.getCount() == 1)
		{
			while(_Cursor.moveToNext())
			{
				_PayoutTotal[0] = _Cursor.getString(_Cursor.getColumnIndex("MinPayoutDate"));
				_PayoutTotal[1] = _Cursor.getString(_Cursor.getColumnIndex("MaxPayoutDate"));
				_PayoutTotal[2] = _Cursor.getString(_Cursor.getColumnIndex("Amount"));
			}
		}
		return _PayoutTotal;
	}
}
