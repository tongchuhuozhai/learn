package bc.com;

import java.io.Serializable;

public class Root implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3279651955212110287L;
	// Video Root
	public String baseUrl;// 基本根Url
	public String playRoot;// 播放 + paramUrl
	public String detailRoot;// 详情 + paramUrl
	public String downRoot;// 下载 + paramUrl
	public String recomRoot;// 推荐 + recomUrl
	public String voteRoot;// 投票 + voteUrl
	public String favRoot;// 收藏 + paramUrl

	// Live Root
	// public String baseUrl;//基本根Url
	// public String playRoot;//播放 + paramUrl
	public String appointRoot;// 预约 + paramUrl
	// public String favRoot;收藏 + paramUrl

	// Multi Root
	// public String baseUrl;//基本根Url
	public String channelRoot;// 进入二级栏目，直播播放历史需要用到
	public String videoRoot;// 点播进入二级栏目的url片段
	public String liveRoot;// 直播进入二级栏目的url片段
	// public String favRoot;//收藏url片段

	// Recommend Root
	public String sectionRoot;// 点击推荐按钮，进入一级栏目
	// public String detailRoot;//点击推荐进入详情
	// public String playRoot;//点击推荐直接播放

	public String orderRoot;
	public String cancelOrderRoot;

	@Override
	public String toString() {
		return "Root [appointRoot=" + appointRoot + ", baseUrl=" + baseUrl
				+ ", cancelOrderRoot=" + cancelOrderRoot + ", detailRoot="
				+ detailRoot + ", downRoot=" + downRoot + ", favRoot="
				+ favRoot + ", liveRoot=" + liveRoot + ", orderRoot="
				+ orderRoot + ", playRoot=" + playRoot + ", recomRoot="
				+ recomRoot + ", sectionRoot=" + sectionRoot + ", videoRoot="
				+ videoRoot + ", voteRoot=" + voteRoot + "]";
	}

}
