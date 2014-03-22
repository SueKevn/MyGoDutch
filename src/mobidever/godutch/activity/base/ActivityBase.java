package mobidever.godutch.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
/**
 * 封装Activity基础操作：打印信息、打开Activity
 * @author Administrator
 *
 */
public class ActivityBase extends Activity {
	
	//打印消息
	protected void ShowMsg(String pMsg){
		Toast.makeText(this, pMsg, 1).show();
	}
	//打开activity
	protected void OpenActivity(Class<?> pClass) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pClass);
		
		startActivity(_Intent);
	}
}
