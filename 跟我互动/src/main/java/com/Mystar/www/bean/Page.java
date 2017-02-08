package com.Mystar.www.bean;

/**
 * Created by Administrator on 2016/12/21.
 */

public class Page {
    private int page;
    private String pageTotal;
    private String pageSize;
    private String dataTotal;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(String pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getDataTotal() {
        return dataTotal;
    }

    public void setDataTotal(String dataTotal) {
        this.dataTotal = dataTotal;
    }
}
