package mobidever.godutch.activity.base;

import java.lang.reflect.Field;

import mobidever.godutch.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;
/**
 * 封装Activity基础操作：打印信息、打开Activity
 * @author Administrator
 *
 */
public class ActivityBase extends Activity {
	
	protected static final int SHOW_TIME = 1;
	private ProgressDialog m_ProgressDialog;
	//打印消息
	protected void ShowMsg(String pMsg){
		Toast.makeText(this, pMsg, 1).show();
	}
	/**
	 * 显示一个Toast信息
	 * @param p_Msg 要显示的信息ID
	 */
	protected void ShowMsg(int p_ResID) {
		Toast.makeText(this, p_ResID, SHOW_TIME).show();
	}
	
	//打开activity
	protected void OpenActivity(Class<?> pClass) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pClass);
		
		startActivity(_Intent);
	}
	//获取一个Layout
	protected LayoutInflater GetLayoutInflater(){
		LayoutInflater _LayoutInflater = LayoutInflater.from(this);
		return _LayoutInflater;
	}
	
	protected AlertDialog ShowAlertDialog(int p_TitelResID,String p_Message,DialogInterface.OnClickListener p_ClickListener)
	{
		String _Title = getResources().getString(p_TitelResID);
		return ShowAlertDialog(_Title, p_Message, p_ClickListener);
	}
	
	protected AlertDialog ShowAlertDialog(String p_Title,String p_Message,DialogInterface.OnClickListener p_ClickListener)
	{		
		return new AlertDialog.Builder(this)
		.setTitle(p_Title)
		.setMessage(p_Message)
		.setPositiveButton(R.string.ButtonTextYes, p_ClickListener)
		.setNegativeButton(R.string.ButtonTextNo, null)
		.show();
	}
	
	public void SetAlertDialogIsClose(DialogInterface pDialog,Boolean pIsClose)
	{
		try {
			Field _Field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
			_Field.setAccessible(true);
		    _Field.set(pDialog, pIsClose);
		} catch (Exception e) {
		}
	}
	
	protected void ShowProgressDialog(int p_TitleResID,int p_MessageResID) {
		m_ProgressDialog = new ProgressDialog(this);
		m_ProgressDialog.setTitle(getString(p_TitleResID));
		m_ProgressDialog.setMessage(getString(p_MessageResID));
		m_ProgressDialog.show();
	}

	protected void DismissProgressDialog() {
		if(m_ProgressDialog != null)
		{
			m_ProgressDialog.dismiss();
		}
	}
}
