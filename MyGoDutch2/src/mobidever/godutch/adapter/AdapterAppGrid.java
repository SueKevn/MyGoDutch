package mobidever.godutch.adapter;

import mobidever.godutch.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * ��ʽ������
 * @author Administrator
 *
 */
public class AdapterAppGrid extends BaseAdapter {

	private class Holder
	{
		ImageView ivIcon;
		TextView tvName;
	}
	
	private Integer[] mImageInteger ={
		R.drawable.grid_payout,
		R.drawable.grid_bill,
		R.drawable.grid_report,
		R.drawable.grid_account_book,
		R.drawable.grid_category,
		R.drawable.grid_user
	};

	private String[] mImageString = new String[6];

	private Context mContext;
	
	public AdapterAppGrid(Context pContext){
		mContext = pContext;
		mImageString[0] = pContext.getString(R.string.appGridTextPayoutAdd);
		mImageString[1] = pContext.getString(R.string.appGridTextPayoutManage);
		mImageString[2] = pContext.getString(R.string.appGridTextStatisticsManage);
		mImageString[3] = pContext.getString(R.string.appGridTextCategoryManage);
		mImageString[4] = pContext.getString(R.string.appGridTextPayoutAdd);
		mImageString[5] = pContext.getString(R.string.appGridTextUserManage);
		
	}
	
	@Override
	public int getCount() {
		return mImageString.length;
	}

	@Override
	public Object getItem(int position) {
		return mImageString[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder _Holder;
		
		if(convertView == null){
			LayoutInflater _LayoutInflater = LayoutInflater.from(mContext);
			convertView = _LayoutInflater.inflate(R.layout.main_body_item, null);
			
			_Holder = new Holder();
			_Holder.ivIcon = (ImageView)convertView.findViewById(R.id.ivIcon);
			_Holder.tvName = (TextView)convertView.findViewById(R.id.tvName);
			convertView.setTag(_Holder);
			
		}else{
			_Holder = (Holder)convertView.getTag();
		}
		
		_Holder.ivIcon.setImageResource(mImageInteger[position]);
		LinearLayout.LayoutParams _LayoutParams = new LinearLayout.LayoutParams(50, 50);
		_Holder.ivIcon.setLayoutParams(_LayoutParams);
		_Holder.ivIcon.setScaleType(ImageView.ScaleType.FIT_XY);
		
		_Holder.tvName.setText(mImageString[position]);
		
		return convertView;
	}

}
