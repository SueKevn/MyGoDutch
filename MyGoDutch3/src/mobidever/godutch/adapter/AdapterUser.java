package mobidever.godutch.adapter;

import java.util.List;

import mobidever.godutch.R;
import mobidever.godutch.adapter.base.AdapterBase;
import mobidever.godutch.business.BusinessUser;
import mobidever.godutch.model.ModelUser;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * �����˵�������
 * 
 * @author Administrator
 * 
 */
public class AdapterUser extends AdapterBase {

	private class Holder {
		ImageView ivUserIcon;
		TextView tvUserName;
	}

	public AdapterUser(Context pContext) {
		super(pContext,null);
		BusinessUser _BusinessUser = new BusinessUser(pContext);
		List _List = _BusinessUser.getNotHideUser();
		if(_List == null) Log.i("sjgLog", "_List");
		SetList(_List);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder _Holder;

		if (convertView == null) {
			convertView = GetLayoutInflater().inflate(
					R.layout.user_list_item, null);

			_Holder = new Holder();
			_Holder.ivUserIcon = (ImageView)convertView
					.findViewById(R.id.ivUserIcon);
			_Holder.tvUserName = (TextView) convertView
					.findViewById(R.id.tvUserName);
			convertView.setTag(_Holder);

		} else {
			_Holder = (Holder) convertView.getTag();
		}

		ModelUser _Info = (ModelUser) GetList().get(position);

		_Holder.ivUserIcon.setImageResource(R.drawable.user_big_icon);
		_Holder.tvUserName.setText(_Info.getUserName());

		return convertView;
	}

}
