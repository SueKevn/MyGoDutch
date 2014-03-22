package mobidever.godutch.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
/**
 * ��װActivity������������ӡ��Ϣ����Activity
 * @author Administrator
 *
 */
public class ActivityBase extends Activity {
	
	//��ӡ��Ϣ
	protected void ShowMsg(String pMsg){
		Toast.makeText(this, pMsg, 1).show();
	}
	//��activity
	protected void OpenActivity(Class<?> pClass) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pClass);
		
		startActivity(_Intent);
	}
}
