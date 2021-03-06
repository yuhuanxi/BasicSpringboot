package im.kuka.springboot.common.util;

import java.io.Serializable;

/**
 * 分页
 * <p/>
 * 默认 curPage=1 pageSize=10
 */
public class PagingDto implements Serializable {
    private Integer curPage;
    private Integer pageSize;
    private Long count;

    public PagingDto(Integer curPage, Integer pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;

        init();
    }

    private void init() {
        if ((this.curPage == null) || (this.curPage <= 0)) {
            this.curPage = 1;
        }

        if ((this.pageSize == null) || (this.pageSize <= 0)) {
            this.pageSize = 10;
        }
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getBeginInt() {
        return (this.curPage - 1) * this.pageSize;
    }

    public Integer getPageCount() {
        int mod = ((this.count % this.pageSize) == 0) ? 0 : 1;
        int divide = (int) (this.count / this.pageSize);

        return divide + mod;
    }

    public Long getCount() {
        return count;
    }

    public boolean hasRecords() {
        return (curPage * pageSize) < count;
    }

}
