package mobidever.godutch.activity.base;

import mobidever.godutch.R;
import mobidever.godutch.activity.controls.SlideMenuView;
import mobidever.godutch.activity.controls.SlideMenuItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
/**
 * ·â×°Activity¿ò¼Ü
 * @author Administrator
 *
 */
public class ActivityFrame extends ActivityBase {

	private SlideMenuView mSlideMenuView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
	}

	protected void AppendMainBody(int pResID) {
		LinearLayout _MainBody = (LinearLayout)findViewById(R.id.layMainBody);
		
		View _View = LayoutInflater.from(this).inflate(pResID, null);
		RelativeLayout.LayoutParams _LayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT);
		_MainBody.addView(_View, _LayoutParams);
	}
	
	protected void CreateSlideMenu(int pResID) {
		mSlideMenuView = new SlideMenuView(this);
		String[] _MenuItemArray = getResources().getStringArray(pResID);
		
		for(int i = 0; i < _MenuItemArray.length;i++ ){
			SlideMenuItem _Item = new SlideMenuItem(i, _MenuItemArray[i]);
			
			mSlideMenuView.Add(_Item);
		}
		mSlideMenuView.BindList();
	}
}
