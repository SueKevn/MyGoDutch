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
 * 主Activity
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
	
	//初始化变量
	public void InitVariable(){
		mAdapterUser = new AdapterUser(this);
	}
	
	//初始化视图
	public void InitView(){
		lvUserList = (ListView)findViewById(R.id.lvUserList);
	}
	
	//初始化监听器		
	public void InitListeners(){
		
	}
	
	//绑定数据
	public void BindData(){
		lvUserList.setAdapter(mAdapterUser);
	}

	@Override
	public void onSildeMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
		Toast.makeText(this, pSlideMenuItem.getTitle(), 1).show();
	}
}
