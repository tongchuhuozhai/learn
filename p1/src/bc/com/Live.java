package bc.com;

import java.io.Serializable;

public class Live implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5286981351464935329L;
	public String name = "";// 内容名称
	public String startTime = "";// 开始时间
	public String endTime = "";// 结束时间
	public Root root;
	public String paramUrl;// 核心参数

	// 根据时间判断
	public int isCurrentContent; // 已播放：-1；当前播放：1；未播放：-2

	@Override
	public String toString() {
		return "Live [endTime=" + endTime + ", isCurrentContent="
				+ isCurrentContent + ", name=" + name + ", paramUrl="
				+ paramUrl + ", root=" + root + ", startTime=" + startTime
				+ "]";
	}

}
