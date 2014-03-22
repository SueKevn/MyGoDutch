package mobidever.godutch.activity;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.activity.controls.SlideMenuItem;
import mobidever.godutch.activity.controls.SlideMenuView.OnSlideMenuListener;
import mobidever.godutch.adapter.AdapterUser;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
/**
 * ��Activity
 * @author Administrator
 *
 */
public class ActivityUser extends ActivityFrame implements OnSlideMenuListener{

	private ListView lvUserList;
	
	private AdapterUser mAdapterUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppendMainBody(R.layout.user);
		
		InitVariable();
		InitView();
		InitListeners();
		BindData();
		CreateSlideMenu(R.array.SlideMenuUser);
	}
	
	//��ʼ������
	public void InitVariable(){
		mAdapterUser = new AdapterUser(this);
	}
	
	//��ʼ����ͼ
	public void InitView(){
		lvUserList = (ListView)findViewById(R.id.lvUserList);
	}
	
	//��ʼ��������		
	public void InitListeners(){
		
	}
	
	//������
	public void BindData(){
		lvUserList.setAdapter(mAdapterUser);
	}

	@Override
	public void onSildeMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
		Toast.makeText(this, pSlideMenuItem.getTitle(), 1).show();
	}
}
