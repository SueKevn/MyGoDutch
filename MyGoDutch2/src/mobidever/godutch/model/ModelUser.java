package mobidever.godutch.model;

import java.util.Date;

/**
 * �û���ϢModel
 * 
 * @author Administrator
 * 
 */
public class ModelUser {
	// �û�������ID
	private int mUserID;
	// �û�����
	private String mUserName;
	// �������
	private Date mCreateDate = new Date();
	// ״̬0ʧЧ��1����
	private int mState = 1;

	public int getUserID() {
		return mUserID;
	}

	public void setUserID(int pUserID) {
		this.mUserID = pUserID;
	}

	public String getUserName() {
		return mUserName;
	}

	public void setUserName(String pUserName) {
		this.mUserName = pUserName;
	}

	public Date getCreateDate() {
		return mCreateDate;
	}

	public void setCreateDate(Date pCreateDat) {
		this.mCreateDate = pCreateDat;
	}

	public int getState() {
		return mState;
	}

	public void setState(int pState) {
		this.mState = pState;
	}

}
