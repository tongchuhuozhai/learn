package bc.com;

import java.io.Serializable;
import java.util.List;

/**
 * 内容，栏目，历史，收藏，到播放页面必须传递的参数
 * 
 * @author raoaming
 * 
 */
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8762088830172018682L;
	public int playType;
	public int contentPosition;
	public List<Video> videoList;
	public List<Live> liveList;

	public String liveParamUrl;// 直播频道核心url
	public String channelDetailRoot;


	public String loginStatus = Args.STATUS_SUCCESS;

	@Override
	public String toString() {
		return "Player [chooseList=" + null + ", contentPosition="
				+ contentPosition + ", history=" + null + ", liveList="
				+ liveList + ", playType=" + playType + ", videoList="
				+ videoList + "]";
	}

}
