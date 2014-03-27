package mobidever.godutch.activity.controls;
/**
 * 下拉菜单条目类
 * @author Administrator
 *
 */
public class SlideMenuItem {
	private int mItemID;
	
	private String mTitle;

	public SlideMenuItem(int pItemID, String pTitle) {
		this.mItemID = pItemID;
		this.mTitle = pTitle;
	}

	public int getItemID() {
		return mItemID;
	}

	public void setItemID(int pItemID) {
		this.mItemID = pItemID;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String pTitle) {
		this.mTitle = pTitle;
	}
}
