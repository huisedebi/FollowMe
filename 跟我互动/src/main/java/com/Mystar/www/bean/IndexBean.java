package com.Mystar.www.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/21.
 */

public class IndexBean {
    private List<Advs> advs;
    private List<Menu> menu;
    private List<Data> data;
    private Page page;
    private String img;
    private String imgUrl;
    private String info;
    private int status;

    public class Data {
        private String title;
        private String toName;
        private String sponsorNum;
        private String sponsorPrice;
        private String day;
        private String num;
        private String share;
        private String headImg;
        private String nickName;
        private String collectNum;
        private String zanNum;
        private String commentsNum;
        private String isClick;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getToName() {
            return toName;
        }

        public void setToName(String toName) {
            this.toName = toName;
        }

        public String getSponsorNum() {
            return sponsorNum;
        }

        public void setSponsorNum(String sponsorNum) {
            this.sponsorNum = sponsorNum;
        }

        public String getSponsorPrice() {
            return sponsorPrice;
        }

        public void setSponsorPrice(String sponsorPrice) {
            this.sponsorPrice = sponsorPrice;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(String collectNum) {
            this.collectNum = collectNum;
        }

        public String getZanNum() {
            return zanNum;
        }

        public void setZanNum(String zanNum) {
            this.zanNum = zanNum;
        }

        public String getCommentsNum() {
            return commentsNum;
        }

        public void setCommentsNum(String commentsNum) {
            this.commentsNum = commentsNum;
        }

        public String getIsClick() {
            return isClick;
        }

        public void setIsClick(String isClick) {
            this.isClick = isClick;
        }
    }


    public List<Advs> getAdvs() {
        return advs;
    }

    public void setAdvs(List<Advs> advs) {
        this.advs = advs;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
