package mobidever.godutch.activity.controls;

import java.util.ArrayList;
import java.util.List;

import mobidever.godutch.R;
import mobidever.godutch.adapter.AdapterSlideMenu;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
/**
 * 菜单视图
 * @author Administrator
 *
 */
public class SlideMenuView {

	private Activity mActivity;

	private List mMenuList;
	
	private boolean mIsClosed;
	
	private RelativeLayout layBottomBox;
	
	private OnSlideMenuListener mSlideMenuListener;
	
	public interface OnSlideMenuListener{
		public abstract void onSildeMenuItemClick(View pView,SlideMenuItem pSlideMenuItem);
	}
	
	public SlideMenuView(Activity pActivity) {
		mActivity = pActivity;
		mSlideMenuListener = (OnSlideMenuListener) pActivity;
		InitVariable();
		InitView();
		InitListeners();
	}

	// 初始化变量
	public void InitVariable() {
		mMenuList = new ArrayList();
		mIsClosed = true;
	}

	// 初始化视图
	public void InitView() {
		layBottomBox = (RelativeLayout)mActivity.findViewById(R.id.IncludeBottom);
		
	}

	// 初始化监听器
	public void InitListeners() {
		layBottomBox.setOnClickListener(new OnSlideMenuClick());
		layBottomBox.setFocusableInTouchMode(true);
		layBottomBox.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP){
					Toggle();
				}
				
				return false;
			}
		});
	}

	public void Open() {
		RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT);
		_LayoutParams.addRule(RelativeLayout.BELOW,R.id.IncludeTitle);
		
		layBottomBox.setLayoutParams(_LayoutParams);
		
		mIsClosed = false;
	}

	public void Close() {
		RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,68);
		_LayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		layBottomBox.setLayoutParams(_LayoutParams);
		
		mIsClosed = true;
	}

	//开关
	public void Toggle() {
		if(mIsClosed){
			Open();
		}else{
			Close();
		}
	}

	public void Add(SlideMenuItem pSliderMenuItem) {
		mMenuList.add(pSliderMenuItem);
	}

	public void BindList() {
		AdapterSlideMenu _AdapterSlideMenu = new AdapterSlideMenu(mActivity, mMenuList);
		
		ListView _ListView = (ListView)mActivity.findViewById(R.id.lvSlideList);
		_ListView.setAdapter(_AdapterSlideMenu);
		_ListView.setOnItemClickListener(new OnSlideMenuItemClick());
		
	}

	public void OnSlideMenuClick() {

	}

	private class OnSlideMenuClick implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Toggle();
		}
	}
	
	private class OnSlideMenuItemClick implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> pAdapterView, View pView, int pPosition,
				long arg3) {
			SlideMenuItem _SlideMenuItem = (SlideMenuItem) pAdapterView.getItemAtPosition(pPosition);
			mSlideMenuListener.onSildeMenuItemClick(pView, _SlideMenuItem);
		}
		
	}
	
}
