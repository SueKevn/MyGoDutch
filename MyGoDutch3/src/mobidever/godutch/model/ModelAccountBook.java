package mobidever.godutch.model;

import java.util.Date;

public class ModelAccountBook {
	//�˱�������ID
	private int mAccountBookID;
	//�˱�����
	private String mAccountBookName;
	//�������
	private Date mCreateDate = new Date();
	//״̬ 0ʧЧ 1����
	private int mState = 1;
	//�Ƿ�Ĭ�˱� 0��1��
	private int mIsDefault;
	/**
	 * �˱�������ID
	 */
	public int getAccountBookID() {
		return mAccountBookID;
	}
	/**
	 * �˱�������ID
	 */
	public void setAccountBookID(int pAccountBookID) {
		this.mAccountBookID = pAccountBookID;
	}
	/**
	 * �˱�����
	 */
	public String getAccountBookName() {
		return mAccountBookName;
	}
	/**
	 * �˱�����
	 */
	public void setAccountBookName(String pAccountBookName) {
		this.mAccountBookName = pAccountBookName;
	}
	/**
	 * �������
	 */
	public Date getCreateDate() {
		return mCreateDate;
	}
	/**
	 * �������
	 */
	public void setCreateDate(Date pCreateDate) {
		this.mCreateDate = pCreateDate;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public int getState() {
		return mState;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public void setState(int pState) {
		this.mState = pState;
	}
	/**
	 * �Ƿ�Ĭ�˱� 0��1��
	 */
	public int getIsDefault() {
		return mIsDefault;
	}
	/**
	 * �Ƿ�Ĭ�˱� 0��1��
	 */
	public void setIsDefault(int pIsDefault) {
		this.mIsDefault = pIsDefault;
	}
}
