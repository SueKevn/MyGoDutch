package mobidever.godutch.activity;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.activity.controls.SlideMenuItem;
import mobidever.godutch.activity.controls.SlideMenuView.OnSlideMenuListener;
import mobidever.godutch.adapter.AdapterAccountBook;
import mobidever.godutch.business.BusinessAccountBook;
import mobidever.godutch.model.ModelAccountBook;
import mobidever.godutch.utility.RegexTools;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityAccountBook extends ActivityFrame implements OnSlideMenuListener {
	
	private ListView lvAccountBookList;
	
	private AdapterAccountBook mAdapterAccountBook;
	private BusinessAccountBook mBusinessAccountBook;
	private ModelAccountBook mSelectModlAccountBook;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppendMainBody(R.layout.account_book);
        initVariable();
        initView();
        initListeners();
        bindData();
        CreateSlideMenu(R.array.SlideMenuAccountBook);
    }
    
    public void initVariable()
    {
    	mBusinessAccountBook = new BusinessAccountBook(this);
    }
    
    public void initView()
    {
    	lvAccountBookList = (ListView) findViewById(R.id.lvAccountBookList);
    }
    
    public void initListeners()
    {
    	registerForContextMenu(lvAccountBookList);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	AdapterContextMenuInfo _AdapterContextMenuInfo = (AdapterContextMenuInfo) menuInfo;
    	ListAdapter _ListAdapter = lvAccountBookList.getAdapter();
    	
    	mSelectModlAccountBook = (ModelAccountBook)_ListAdapter.getItem(_AdapterContextMenuInfo.position);
    	
    	menu.setHeaderIcon(R.drawable.account_book_small_icon);
    	menu.setHeaderTitle(mSelectModlAccountBook.getAccountBookName());
    	
    	CreateMenu(menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {

    	switch (item.getItemId()) {
		case 1:
			showAccountBookAddOrEditDialog(mSelectModlAccountBook);
			break;
		case 2:
			delete();
			break;
		default:
			break;
		}

    	return super.onContextItemSelected(item);
    }
    
    public void bindData()
    {
    	mAdapterAccountBook = new AdapterAccountBook(this);
    	lvAccountBookList.setAdapter(mAdapterAccountBook);
    	setTitle();
    }

	@Override
	public void onSlideMenuItemClick(View pView, SlideMenuItem pSlideMenuItem) {
		SlideMenuToggle();
		if (pSlideMenuItem.getItemID() == 0) {
			showAccountBookAddOrEditDialog(null);
		}
	}
	
	private void setTitle() {
		int _Count = mAdapterAccountBook.getCount();
		String _Titel = getString(R.string.ActivityTitleAccountBook, new Object[]{_Count});
		setTopBarTitle(_Titel);
	}
	
	private void showAccountBookAddOrEditDialog(ModelAccountBook pModelAccountBook)
	{
		View _View = GetLayoutInflater().inflate(R.layout.account_book_add_or_edit, null);
		
		EditText _etAccountBookName = (EditText) _View.findViewById(R.id.etAccountBookName);
		CheckBox _chkIsDefault = (CheckBox)_View.findViewById(R.id.chkIsDefault);
		
		if (pModelAccountBook != null) {
			_etAccountBookName.setText(pModelAccountBook.getAccountBookName());
		}
		
		String _Title;
		
		if(pModelAccountBook == null)
		{
			_Title = getString(R.string.DialogTitleAccountBook,new Object[]{getString(R.string.TitleAdd)});
		}
		else {
			_Title = getString(R.string.DialogTitleAccountBook,new Object[]{getString(R.string.TitleEdit)});
		}
		
		AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
		_Builder.setTitle(_Title)
				.setView(_View)
				.setIcon(R.drawable.account_book_big_icon)
				.setNeutralButton(getString(R.string.ButtonTextSave), new OnAddOrEditAccountBookListener(pModelAccountBook,_etAccountBookName,_chkIsDefault,true))
				.setNegativeButton(getString(R.string.ButtonTextCancel), new OnAddOrEditAccountBookListener(null,null,null,false))
				.show();
	}
	
	private class OnAddOrEditAccountBookListener implements DialogInterface.OnClickListener
	{
		private ModelAccountBook mModelAccountBook;
		private EditText etAccountBookName;
		private boolean mIsSaveButton;
		private CheckBox chkIsDefault;
		
		public OnAddOrEditAccountBookListener(ModelAccountBook pModelAccountBook,EditText petAccountBookName,CheckBox pchkIsDefault,Boolean pIsSaveButton)
		{
			mModelAccountBook = pModelAccountBook;
			etAccountBookName = petAccountBookName;
			mIsSaveButton = pIsSaveButton;
			chkIsDefault = pchkIsDefault;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			if(mIsSaveButton == false)
			{
				SetAlertDialogIsClose(dialog, true);
				return;
			}
			
			if (mModelAccountBook == null) {
				mModelAccountBook = new ModelAccountBook();
			}
			
			String _AccountBookName = etAccountBookName.getText().toString().trim();
			
			boolean _CheckResult = RegexTools.IsChineseEnglishNum(_AccountBookName);
			
			if (!_CheckResult) {
				Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextChineseEnglishNum,new Object[]{etAccountBookName.getHint()}), SHOW_TIME).show();
				SetAlertDialogIsClose(dialog, false);
				return;
			}
			else {
				SetAlertDialogIsClose(dialog, true);
			}
			
			_CheckResult = mBusinessAccountBook.IsExistAccountBookByAccountBookName(_AccountBookName, mModelAccountBook.getAccountBookID());
			
			if (_CheckResult) {
				Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextAccountBookExist), SHOW_TIME).show();
				
				SetAlertDialogIsClose(dialog, false);
				return;
			}
			else {
				SetAlertDialogIsClose(dialog, true);
			}
			
			mModelAccountBook.setAccountBookName(etAccountBookName.getText().toString());
			
			mModelAccountBook.setAccountBookName(String.valueOf(etAccountBookName.getText().toString().trim()));
			if(chkIsDefault.isChecked())
			{
				mModelAccountBook.setIsDefault(1);
			}
			else {
				mModelAccountBook.setIsDefault(0);
			}
			
			if(mModelAccountBook.getAccountBookID() > 0)
			{
				mModelAccountBook.setIsDefault(1);
			}
			
			boolean _Result = false;
			
			if (mModelAccountBook.getAccountBookID() == 0) {
				_Result = mBusinessAccountBook.insertAccountBook(mModelAccountBook);
			}
			else {
				_Result = mBusinessAccountBook.updateAccountBookByAccountBookID(mModelAccountBook);
			}
			
			if (_Result) {
				bindData();
			}
			else {
				Toast.makeText(ActivityAccountBook.this, getString(R.string.TipsAddFail), 1).show();
			}
		}
		
	}

	private void delete() {
		String _Message = getString(R.string.DialogMessageAccountBookDelete,new Object[]{mSelectModlAccountBook.getAccountBookName()});
		ShowAlertDialog(R.string.DialogTitleDelete,_Message,new onDeleteClickListener());
	}
	
	private class onDeleteClickListener implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			boolean _Result = mBusinessAccountBook.DeleteAccountBookByAccountBookID(mSelectModlAccountBook.getAccountBookID());
			
			if (_Result == true) {
				bindData();
			}
		}
	}
}