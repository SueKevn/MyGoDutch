package mobidever.godutch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import android.R.integer;

public class ModelPayout implements Serializable {
	//֧��������ID
	private int m_PayoutID;
	//�˱�ID���
	private int m_AccountBookID;
	//�˱�����
	private String m_AccountBookName;
	//֧�����ID���
	private int m_CategoryID;
	//�������
	private String m_CategoryName;
	//·��
	private String m_Path;
	//���ʽID���
	private int m_PayWayID;
	//���ѵص�ID���
	private int m_PlaceID;
	//���ѽ��
	private BigDecimal m_Amount;
	//��������
	private Date m_PayoutDate;
	//���㷽ʽ
	private String m_PayoutType;
	//������ID���
	private String m_PayoutUserID;
	//��ע
	private String m_Comment;
	//�������
	private Date m_CreateDate = new Date();
	//״̬ 0ʧЧ 1����
	private int m_State = 1;
	/**
	 * ֧��������ID
	 */
	public int getPayoutID() {
		return m_PayoutID;
	}
	/**
	 * ֧��������ID
	 */
	public void setPayoutID(int p_PayoutID) {
		this.m_PayoutID = p_PayoutID;
	}
	/**
	 * �˱�����ID���
	 */
	public int getAccountBookID() {
		return m_AccountBookID;
	}
	/**
	 * �˱�ID���
	 */
	public void setAccountBookID(int p_AccountBookID) {
		this.m_AccountBookID = p_AccountBookID;
	}
	/**
	 * �˱�����
	 */
	public String getAccountBookName() {
		return m_AccountBookName;
	}
	/**
	 * �˱�����
	 */
	public void setAccountBookName(String p_AccountBookName) {
		this.m_AccountBookName = p_AccountBookName;
	}
	/**
	 * ֧�����ID���
	 */
	public int getCategoryID() {
		return m_CategoryID;
	}
	/**
	 * ֧�����ID���
	 */
	public void setCategoryID(int p_CategoryID) {
		this.m_CategoryID = p_CategoryID;
	}
	/**
	 * ·��
	 */
	public String getPath() {
		return m_Path;
	}
	/**
	 * ·��
	 */
	public void setPath(String p_Path) {
		this.m_Path = p_Path;
	}
	/**
	 * �˱�����
	 */
	public String getCategoryName() {
		return m_CategoryName;
	}
	/**
	 * �˱�����
	 */
	public void setCategoryName(String p_CategoryName) {
		this.m_CategoryName = p_CategoryName;
	}
	/**
	 * 	���ʽID���
	 */
	public int getPayWayID() {
		return m_PayWayID;
	}
	/**
	 * 	���ʽID���
	 */
	public void setPayWayID(int p_PayWayID) {
		this.m_PayWayID = p_PayWayID;
	}
	/**
	 * ���ѵص�ID���
	 */
	public int getPlaceID() {
		return m_PlaceID;
	}
	/**
	 * ���ѵص�ID���
	 */
	public void setPlaceID(int p_PlaceID) {
		this.m_PlaceID = p_PlaceID;
	}
	/**
	 * ���ѽ��
	 */
	public BigDecimal getAmount() {
		return m_Amount;
	}
	/**
	 * ���ѽ��
	 */
	public void setAmount(BigDecimal p_Amount) {
		this.m_Amount = p_Amount;
	}
	/**
	 * ��������
	 */
	public Date getPayoutDate() {
		return m_PayoutDate;
	}
	/**
	 * ��������
	 */
	public void setPayoutDate(Date p_PayoutDate) {
		this.m_PayoutDate = p_PayoutDate;
	}
	/**
	 * ���㷽ʽ
	 */
	public String getPayoutType() {
		return m_PayoutType;
	}
	/**
	 * ���㷽ʽ
	 */
	public void setPayoutType(String p_PayoutType) {
		this.m_PayoutType = p_PayoutType;
	}
	/**
	 * ������ID���
	 */
	public String getPayoutUserID() {
		return m_PayoutUserID;
	}
	/**
	 * ������ID���
	 */
	public void setPayoutUserID(String p_PayoutUserID) {
		this.m_PayoutUserID = p_PayoutUserID;
	}	
	/**
	 * ��ע
	 */
	public String getComment() {
		return m_Comment;
	}
	/**
	 * ��ע
	 */
	public void setComment(String p_Comment) {
		this.m_Comment = p_Comment;
	}
	/**
	 * �������
	 */
	public Date getCreateDate() {
		return m_CreateDate;
	}
	/**
	 * �������
	 */
	public void setCreateDate(Date p_CreateDate) {
		this.m_CreateDate = p_CreateDate;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public int getState() {
		return m_State;
	}
	/**
	 * ״̬ 0ʧЧ 1����
	 */
	public void setState(int p_State) {
		this.m_State = p_State;
	}
}
