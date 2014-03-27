package mobidever.godutch.activity;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.activity.controls.SlideMenuItem;
import mobidever.godutch.activity.controls.SlideMenuView.OnSlideMenuListener;
import mobidever.godutch.adapter.AdapterAppGrid;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
/**
 * 主Activity
 * @author Administrator
 *
 */
public class ActivityMain extends ActivityFrame implements OnSlideMenuListener{

	private GridView gvAppGrid;
	
	private AdapterAppGrid mAdapterAppGrid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppendMainBody(R.layout.main_body);
		
		InitVariable();
		InitView();
		InitListeners();
		BindData();
		CreateSlideMenu(R.array.SlideMenuActivityMain);
	}   
	
	//初始化变量
	public void InitVariable(){
		mAdapterAppGrid = new AdapterAppGrid(this);
	}
	
	//初始化视图
	public void InitView(){
		gvAppGrid = (GridView)findViewById(R.id.gvAppGrid);
	}
	
	//初始化监听器
	public void InitListeners(){
		gvAppGrid.setOnItemClickListener(new onAppGridItemClickListener());
	}
	
	private class onAppGridItemClickListener implements OnItemClickListener
    {
		@Override
		public void onItemClick(AdapterView p_Adapter, View p_View, int p_Position,long arg3) {
			String _MenuName = (String)p_Adapter.getAdapter().getItem(p_Position);
			if(_MenuName.equals(getString(R.string.appGridTextUserManage)))
			{
				OpenActivity(ActivityUser.class);
				return;
			}
//			if(_MenuName.equals(getString(R.string.appGridTextAccountBookManage)))
//			{
//				OpenActivity(ActivityAccountBook.class);
//				return;
//			}
//			if(_MenuName.equals(getString(R.string.appGridTextCategoryManage)))
//			{
//				OpenActivity(ActivityCategory.class);
//				return;
//			}
//			if(_MenuName.equals(getString(R.string.appGridTextPayoutAdd)))
//			{
//				OpenActivity(ActivityPayoutAddOrEdit.class);
//				return;
//			}
//			if(_MenuName.equals(getString(R.string.appGridTextPayoutManage)))
//			{
//				OpenActivity(ActivityPayout.class);
//				return;
//			}
//			if(_MenuName.equals(getString(R.string.appGridTextStatisticsManage)))
//			{
//				OpenActivity(ActivityStatistics.class);
//				return;
//			}
		}
	}
	
	//绑定数据
	public void BindData(){
		gvAppGrid.setAdapter(mAdapterAppGrid);
	}

	@Override
	public void onSildeMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
		Toast.makeText(this, pSlideMenuItem.getTitle(), 1).show();
	}
}
