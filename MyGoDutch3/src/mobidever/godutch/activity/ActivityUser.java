package mobidever.godutch.activity;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.activity.controls.SlideMenuItem;
import mobidever.godutch.activity.controls.SlideMenuView.OnSlideMenuListener;
import mobidever.godutch.adapter.AdapterUser;
import mobidever.godutch.business.BusinessUser;
import mobidever.godutch.model.ModelUser;
import mobidever.godutch.utility.RegexTools;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 主Activity
 * 
 * @author Administrator
 * 
 */
public class ActivityUser extends ActivityFrame implements OnSlideMenuListener {

	private ListView lvUserList;

	private AdapterUser mAdapterUser;

	private BusinessUser mBusinessUser;

	private ModelUser mSelectModlUser;
	
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

	// 初始化变量
	public void InitVariable() {
		mBusinessUser = new BusinessUser(this);
	}

	// 初始化视图
	public void InitView() {
		lvUserList = (ListView) findViewById(R.id.lvUserList);
	}

	// 初始化监听器
	public void InitListeners() {
		registerForContextMenu(lvUserList);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo) menuInfo;
    	ListAdapter _ListAdapter = lvUserList.getAdapter();
    	
    	mSelectModlUser = (ModelUser)_ListAdapter.getItem(_AdapterContextMenuInfo.position);
    	
    	menu.setHeaderIcon(R.drawable.user_small_icon);
    	menu.setHeaderTitle(mSelectModlUser.getUserName());
    	
    	CreateMenu(menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 1:
			ShowUserAddOrEditDialog(mSelectModlUser);
			break;
		case 2:
			delete();
			break;
		default:
			break;
		}

		return super.onContextItemSelected(item);
	}
	
	// 绑定数据
	public void BindData() {
		mAdapterUser = new AdapterUser(this);
		lvUserList.setAdapter(mAdapterUser);
		setTitle();
	}

	private void setTitle() {
		int _Count = mAdapterUser.getCount();
		String _Titel = getString(R.string.ActivityTitleUser, new Object[]{_Count});
		setTopBarTitle(_Titel);
	}
	
	@Override
	public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
//		Toast.makeText(this, pSlideMenuItem.getTitle(), 1).show();
		SlideMenuToggle();
		if(pSlideMenuItem.getItemID() == 0){
			
			ShowUserAddOrEditDialog(null);
		}
	}

	private void ShowUserAddOrEditDialog(ModelUser pModelUser) {
		View _View = GetLayoutInflater().inflate(R.layout.user_add_or_edit,
				null);

		EditText _etUserName = (EditText) _View.findViewById(R.id.etUserName);

		if (pModelUser != null) {
			_etUserName.setText(pModelUser.getUserName());
		}

		String _Title;

		if (pModelUser == null) {
			_Title = getString(R.string.DialogTitleUser,
					new Object[] { getString(R.string.TitleAdd) });
		} else {
			_Title = getString(R.string.DialogTitleUser,
					new Object[] { getString(R.string.TitleEdit) });
		}

		AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
		_Builder.setTitle(_Title)
				.setView(_View)
				.setIcon(R.drawable.user_big_icon)
				.setNeutralButton(
						R.string.ButtonTextSave,
						new onAddOrEditUserListener(pModelUser, _etUserName,
								true))
				.setNegativeButton(R.string.ButtonTextCancel,
						new onAddOrEditUserListener(null, null, false)).show();
	}

	private class onAddOrEditUserListener implements
			DialogInterface.OnClickListener {

		private ModelUser mModelUser;
		private EditText etUserName;
		private boolean mIsSaveButton;

		public onAddOrEditUserListener(ModelUser pModelUser,
				EditText petUserName, Boolean pIsSaveButton) {
			mModelUser = pModelUser;
			etUserName = petUserName;
			mIsSaveButton = pIsSaveButton;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (mIsSaveButton == false) {
				SetAlertDialogIsClose(dialog, true);
				return;
			}

			if (mModelUser == null) {
				mModelUser = new ModelUser();
			}

			String _UserName = etUserName.getText().toString().trim();

			boolean _CheckResult = RegexTools.IsChineseEnglishNum(_UserName);

			if (!_CheckResult) {
				Toast.makeText(
						getApplicationContext(),
						getString(R.string.CheckDataTextChineseEnglishNum,
								new Object[] { etUserName.getHint() }),
						SHOW_TIME).show();
				SetAlertDialogIsClose(dialog, false);
				return;
			} else {
				SetAlertDialogIsClose(dialog, true);
			}

			_CheckResult = mBusinessUser.IsExistUserByUserName(_UserName,
					mModelUser.getUserID());

			if (_CheckResult) {
				Toast.makeText(getApplicationContext(),
						getString(R.string.CheckDataTextUserExist), SHOW_TIME)
						.show();

				SetAlertDialogIsClose(dialog, false);
				return;
			} else {
				SetAlertDialogIsClose(dialog, true);
			}

			mModelUser.setUserName(etUserName.getText().toString());
			
			boolean _Result = false;
			
			if (mModelUser.getUserID() == 0) {
				_Result = mBusinessUser.insertUser(mModelUser);
			}
			else {
				_Result = mBusinessUser.updateUserByUserID(mModelUser);
			}
			
			if (_Result) {
				BindData();
			}
			else {
				Toast.makeText(ActivityUser.this, getString(R.string.TipsAddFail), 1).show();
			}
			
			
		}

	}

	private void delete() {
		String _Message = getString(R.string.DialogMessageUserDelete,new Object[]{mSelectModlUser.getUserName()});
		ShowAlertDialog(R.string.DialogTitleDelete,_Message,new OnDeleteClickListener());
	}
	
	private class OnDeleteClickListener implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			boolean _Result = mBusinessUser.HideUserByUserID(mSelectModlUser.getUserID());
			
			if (_Result == true) {
				BindData();
			}
		}
	}
}
