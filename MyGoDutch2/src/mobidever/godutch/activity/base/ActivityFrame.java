package mobidever.godutch.activity.base;

import mobidever.godutch.R;
import mobidever.godutch.activity.controls.SlideMenuView;
import mobidever.godutch.activity.controls.SlideMenuItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
/**
 * ��װActivity���
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
	
	protected void SlideMenuToggle() {
		mSlideMenuView.Toggle();
	}
	
	protected void CreateMenu(Menu p_Menu)
	{
		int _GroupID = 0;
		int _Order = 0;
		int[] p_ItemID = {1,2};
		
		for(int i=0;i<p_ItemID.length;i++)
		{
			switch(p_ItemID[i])
			{
			case 1:
				p_Menu.add(_GroupID, p_ItemID[i], _Order, R.string.MenuTextEdit);
				break;
			case 2:
				p_Menu.add(_GroupID, p_ItemID[i], _Order, R.string.MenuTextDelete);
				break;
			default:
				break;
			}
		}
	}
	
}
