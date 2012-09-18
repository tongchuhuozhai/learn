package bc.com;

import java.io.Serializable;

//内容列表元素，详情页xml
public class Video implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9067778430381413250L;
	public String name;// 内容名称
	public String nodeName;// 用于首页推荐的栏目名称
	public String image;// 图片
	public String desc;// 内容详情
	public String size;// 内容大小
	public String hits;// 推荐度。播放次数
	public Root root;// 二次根
	public String paramUrl;// 核心参数
	public String voteUrl;// 投票url片段 + 投票选项
	public String publishTime;

	public long currentPlayPosition = -1;
	public int videoOrMusic;

//	@Override
//	public String toString() {
////		return "Video [currentPlayPosition=" + currentPlayPosition + ", desc="
////				+ desc + ", hits=" + hits + ", image=" + image + ", name="
////				+ name + ", nodeName=" + nodeName + ", paramUrl=" + paramUrl
////				+ ", publishTime=" + publishTime + ", size=" + size
////				+ ", videoOrMusic=" + videoOrMusic + ", vote=" + null
////				+ ", voteUrl=" + voteUrl + "]";
//	}

}
