package mobidever.godutch.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import android.R.integer;

public class ModelPayout implements Serializable {
	//支出表主键ID
	private int m_PayoutID;
	//账本ID外键
	private int m_AccountBookID;
	//账本名称
	private String m_AccountBookName;
	//支出类别ID外键
	private int m_CategoryID;
	//类别名称
	private String m_CategoryName;
	//路径
	private String m_Path;
	//付款方式ID外键
	private int m_PayWayID;
	//消费地点ID外键
	private int m_PlaceID;
	//消费金额
	private BigDecimal m_Amount;
	//消费日期
	private Date m_PayoutDate;
	//计算方式
	private String m_PayoutType;
	//消费人ID外键
	private String m_PayoutUserID;
	//备注
	private String m_Comment;
	//添加日期
	private Date m_CreateDate = new Date();
	//状态 0失效 1启用
	private int m_State = 1;
	/**
	 * 支出表主键ID
	 */
	public int getPayoutID() {
		return m_PayoutID;
	}
	/**
	 * 支出表主键ID
	 */
	public void setPayoutID(int p_PayoutID) {
		this.m_PayoutID = p_PayoutID;
	}
	/**
	 * 账本名称ID外键
	 */
	public int getAccountBookID() {
		return m_AccountBookID;
	}
	/**
	 * 账本ID外键
	 */
	public void setAccountBookID(int p_AccountBookID) {
		this.m_AccountBookID = p_AccountBookID;
	}
	/**
	 * 账本名称
	 */
	public String getAccountBookName() {
		return m_AccountBookName;
	}
	/**
	 * 账本名称
	 */
	public void setAccountBookName(String p_AccountBookName) {
		this.m_AccountBookName = p_AccountBookName;
	}
	/**
	 * 支出类别ID外键
	 */
	public int getCategoryID() {
		return m_CategoryID;
	}
	/**
	 * 支出类别ID外键
	 */
	public void setCategoryID(int p_CategoryID) {
		this.m_CategoryID = p_CategoryID;
	}
	/**
	 * 路径
	 */
	public String getPath() {
		return m_Path;
	}
	/**
	 * 路径
	 */
	public void setPath(String p_Path) {
		this.m_Path = p_Path;
	}
	/**
	 * 账本名称
	 */
	public String getCategoryName() {
		return m_CategoryName;
	}
	/**
	 * 账本名称
	 */
	public void setCategoryName(String p_CategoryName) {
		this.m_CategoryName = p_CategoryName;
	}
	/**
	 * 	付款方式ID外键
	 */
	public int getPayWayID() {
		return m_PayWayID;
	}
	/**
	 * 	付款方式ID外键
	 */
	public void setPayWayID(int p_PayWayID) {
		this.m_PayWayID = p_PayWayID;
	}
	/**
	 * 消费地点ID外键
	 */
	public int getPlaceID() {
		return m_PlaceID;
	}
	/**
	 * 消费地点ID外键
	 */
	public void setPlaceID(int p_PlaceID) {
		this.m_PlaceID = p_PlaceID;
	}
	/**
	 * 消费金额
	 */
	public BigDecimal getAmount() {
		return m_Amount;
	}
	/**
	 * 消费金额
	 */
	public void setAmount(BigDecimal p_Amount) {
		this.m_Amount = p_Amount;
	}
	/**
	 * 消费日期
	 */
	public Date getPayoutDate() {
		return m_PayoutDate;
	}
	/**
	 * 消费日期
	 */
	public void setPayoutDate(Date p_PayoutDate) {
		this.m_PayoutDate = p_PayoutDate;
	}
	/**
	 * 计算方式
	 */
	public String getPayoutType() {
		return m_PayoutType;
	}
	/**
	 * 计算方式
	 */
	public void setPayoutType(String p_PayoutType) {
		this.m_PayoutType = p_PayoutType;
	}
	/**
	 * 消费人ID外键
	 */
	public String getPayoutUserID() {
		return m_PayoutUserID;
	}
	/**
	 * 消费人ID外键
	 */
	public void setPayoutUserID(String p_PayoutUserID) {
		this.m_PayoutUserID = p_PayoutUserID;
	}	
	/**
	 * 备注
	 */
	public String getComment() {
		return m_Comment;
	}
	/**
	 * 备注
	 */
	public void setComment(String p_Comment) {
		this.m_Comment = p_Comment;
	}
	/**
	 * 添加日期
	 */
	public Date getCreateDate() {
		return m_CreateDate;
	}
	/**
	 * 添加日期
	 */
	public void setCreateDate(Date p_CreateDate) {
		this.m_CreateDate = p_CreateDate;
	}
	/**
	 * 状态 0失效 1启用
	 */
	public int getState() {
		return m_State;
	}
	/**
	 * 状态 0失效 1启用
	 */
	public void setState(int p_State) {
		this.m_State = p_State;
	}
}
