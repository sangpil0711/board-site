package kr.co.ymtech.bm.controller.dto;

import java.util.List;

import kr.co.ymtech.bm.repository.vo.SystemVO;

public class SystemPageDTO {
	
	/** boardList : 게시글 정보 */
    private List<SystemVO> systemList;
    /** totalCount : 총 게시글 수 */
    private Integer totalCount;

    public List<SystemVO> getSystemList() {
        return systemList;
    }

    public void setSystemList(List<SystemVO> systemList) {
        this.systemList = systemList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("SystemPageDTO [systemList=");
      builder.append(systemList);
      builder.append(", totalCount=");
      builder.append(totalCount);
      builder.append("]");
      return builder.toString();
   }
    
}