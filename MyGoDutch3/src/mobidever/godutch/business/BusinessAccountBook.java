package mobidever.godutch.business;

import java.text.SimpleDateFormat;
import java.util.List;

import mobidever.godutch.business.base.BusinessBase;
import mobidever.godutch.database.sqlitedal.SQLiteDALAccountBook;
import mobidever.godutch.model.ModelAccountBook;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

public class BusinessAccountBook extends BusinessBase {

	private SQLiteDALAccountBook m_SqLiteDALAccountBook;

	public BusinessAccountBook(Context p_Context) {
		super(p_Context);
		m_SqLiteDALAccountBook = new SQLiteDALAccountBook(p_Context);
	}

	public Boolean insertAccountBook(ModelAccountBook p_Info) {
		m_SqLiteDALAccountBook.beginTransaction();
		try {
			Boolean _Result = m_SqLiteDALAccountBook.insertAccountBook(p_Info);
			Boolean _Result2 = true;
			if (p_Info.getIsDefault() == 1 && _Result) {
				_Result2 = setIsDefault(p_Info.getAccountBookID());
			}

			if (_Result && _Result2) {
				m_SqLiteDALAccountBook.setTransactionSuccessful();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			m_SqLiteDALAccountBook.endTransaction();
		}
	}

	public Boolean DeleteAccountBookByAccountBookID(int p_AccountBookID) {
		m_SqLiteDALAccountBook.beginTransaction();
		try {
			String _Condition = " And AccountBookID = " + p_AccountBookID;
			Boolean _Result = m_SqLiteDALAccountBook
					.deleteAccountBook(_Condition);
			Boolean _Result2 = true;
			if (_Result) {
				BusinessPayout _BusinessPayout = new BusinessPayout(
						getContext());
				_Result2 = _BusinessPayout
						.DeletePayoutByAccountBookID(p_AccountBookID);
			}

			if (_Result && _Result2) {
				m_SqLiteDALAccountBook.setTransactionSuccessful();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			m_SqLiteDALAccountBook.endTransaction();
		}
	}

	public Boolean updateAccountBookByAccountBookID(ModelAccountBook p_Info) {
		m_SqLiteDALAccountBook.beginTransaction();
		try {
			String _Condition = " AccountBookID = " + p_Info.getAccountBookID();
			Boolean _Result = m_SqLiteDALAccountBook.updateAccountBook(
					_Condition, p_Info);
			Boolean _Result2 = true;
			if (p_Info.getIsDefault() == 1 && _Result) {
				_Result2 = setIsDefault(p_Info.getAccountBookID());
			}

			if (_Result && _Result2) {
				m_SqLiteDALAccountBook.setTransactionSuccessful();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			m_SqLiteDALAccountBook.endTransaction();
		}
	}

	public List<ModelAccountBook> getAccountBook(String p_Condition) {
		return m_SqLiteDALAccountBook.getAccountBook(p_Condition);
	}

	public boolean IsExistAccountBookByAccountBookName(
			String p_AccountBookName, Integer p_AccountBookID) {
		String _Condition = " And AccountBookName = '" + p_AccountBookName
				+ "'";
		if (p_AccountBookID != null) {
			_Condition += " And AccountBookID <> " + p_AccountBookID;
		}
		List _List = m_SqLiteDALAccountBook.getAccountBook(_Condition);
		if (_List.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public ModelAccountBook getDefaultModelAccountBook() {
		List _List = m_SqLiteDALAccountBook
				.getAccountBook(" And IsDefault = 1");
		if (_List.size() == 1) {
			return (ModelAccountBook) _List.get(0);
		} else {
			return null;
		}
	}

	public int getCount() {
		return m_SqLiteDALAccountBook.getCount("");
	}

	public Boolean setIsDefault(int p_ID) {
		String _Condition = " IsDefault = 1";
		ContentValues _ContentValues = new ContentValues();
		_ContentValues.put("IsDefault", 0);
		Boolean _Result = m_SqLiteDALAccountBook.updateAccountBook(_Condition,
				_ContentValues);

		_Condition = " AccountBookID = " + p_ID;
		_ContentValues.clear();
		_ContentValues.put("IsDefault", 1);
		Boolean _Result2 = m_SqLiteDALAccountBook.updateAccountBook(_Condition,
				_ContentValues);

		if (_Result && _Result2) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getAccountBookNameByAccountId(int p_BookId) {
		String _ConditionString = "And AccountBookID = " + String.valueOf(p_BookId);
		List<ModelAccountBook> _AccountBooks = m_SqLiteDALAccountBook.getAccountBook(_ConditionString);
		return _AccountBooks.get(0).getAccountBookName();
	}
}
