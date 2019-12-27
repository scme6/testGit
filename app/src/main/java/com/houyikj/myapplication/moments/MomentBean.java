package com.houyikj.myapplication.moments;

import java.io.Serializable;
import java.util.List;

public class MomentBean {

    private List<ResultBean> result;
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * name : 张三
         * head : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1600745829,4038611101&fm=26&gp=0.jpg
         * content : 深蓝的天空中挂着一轮金黄的圆月，下面是海边的沙地，都种着一望无际的碧绿的西瓜。其间①有一个十一二岁的少年，项带银圈，手捏一柄钢叉，向一匹猹②尽力地刺去。那猹却将身一扭，反从他的胯下逃走了。
         * image : ["https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575210059990&di=c1ff37576a85e8201b0a22e0c77a54d3&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180803%2F20%2F1533300063-nYdLCbfxBX.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575210086544&di=636e8b5b6b16662f50d04d60aecac9a1&imgtype=jpg&src=http%3A%2F%2Fimg3.imgtn.bdimg.com%2Fit%2Fu%3D1792698123%2C1670942784%26fm%3D214%26gp%3D0.jpg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575210114976&di=751d1d3c991c4d691b84b2d8170accbf&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201410%2F11%2F20141011212702_QWc3r.thumb.700_0.jpeg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575210129499&di=b5742c5a0eebf8f95aa6c65a0ac456f0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201410%2F11%2F20141011212615_E4ks8.thumb.700_0.jpeg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575210150267&di=56030bb0b21c8201f813a791afe9299c&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201606%2F29%2F20160629135102_cfwJv.thumb.700_0.jpeg","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575210164066&di=5d320101a3fcfec0f035883e65bd4c03&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201410%2F21%2F20141021212911_YYVkZ.jpeg"]
         * time : 10分钟前
         * det : 评论
         */

        private String name;
        private String head;
        private String content;
        private String time;
        private String det;
        private List<String> image;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDet() {
            return det;
        }

        public void setDet(String det) {
            this.det = det;
        }

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }
    }
}
