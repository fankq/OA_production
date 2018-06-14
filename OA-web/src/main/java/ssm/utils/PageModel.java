package ssm.utils;

/**
 * Created by fankq on 2018/4/22.
 */
public class PageModel {
    /**
     *分页总条数
     */
    private int recordCount;
    /**
     * 当前页数
     */
    private int pageIndex;
    /**
     * 每页总条数
     */
    private int pageSize = HrmConstants.PAGE_DEFAULT_SIZE;
    /**
     * 总页数
     */
    private int totalSize;

    public int getRecordCount(){
        this.recordCount = this.recordCount<=0?0:this.recordCount;
        return this.recordCount;
    }

    public void setRecordCount(int count){
        this.recordCount = count;
    }

    public void setPageIndex(int page){
        this.pageIndex = page;
    }

    public int getPageIndex(){
        this.pageIndex = this.pageIndex<=0?1:this.pageIndex;
        this.pageIndex = this.pageIndex>=this.getTotalSize()?this.getTotalSize():this.pageIndex;
        return this.pageIndex;
    }

    public int getTotalSize() {
        if(this.recordCount<=0){
            this.totalSize = 0;
        }else{
            this.totalSize = (this.getRecordCount()-1)/this.getPageSize()+1;
        }
        return totalSize;
    }

    public int getPageSize() {
        this.pageSize = this.pageSize<=HrmConstants.PAGE_DEFAULT_SIZE?HrmConstants.PAGE_DEFAULT_SIZE:this.pageSize;
        return pageSize;
    }

    public int getFirstLimitParam(){
        return (this.getPageIndex()-1)*this.getPageSize();
    }
}
