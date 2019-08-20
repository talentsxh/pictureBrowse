package com.sxh.demo.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @author ClamLaw
 * @time 2019/3/12  11:14
 * @desc ${TODD}
 */
public class MenuBean {


    /**
     * msg : 返回成功
     * data : [{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca060a523062.png","name":"服装饰品","cate_id":[{"name":"女装","id":"30"},{"name":"男装","id":"31"},{"name":"家居服","id":"32"},{"name":"钟表","id":"33"},{"name":"女装系列","id":"93"},{"name":"男装系列","id":"94"},{"name":"内衣系列","id":"95"},{"name":"服饰配件","id":"96"},{"name":"宝珠首饰","id":"97"},{"name":"钟表","id":"98"}],"id":"24"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca060ddb73af.png","name":"食品饮料","cate_id":[{"name":"酒类","id":"55"},{"name":"营养品","id":"64"},{"name":"饮料/冲调","id":"65"},{"name":"休闲食品","id":"66"},{"name":"粮油调味","id":"67"},{"name":"地方特产","id":"68"},{"name":"进口食品","id":"69"},{"name":"生鲜食品","id":"70"},{"name":"茗茶","id":"71"}],"id":"54"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca060e613329.png","name":"电器数码","cate_id":[{"name":"厨房电器","id":"72"},{"name":"生活电器","id":"73"},{"name":"智能设备","id":"74"},{"name":"大家电","id":"75"},{"name":"数码配件","id":"76"}],"id":"56"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca060ed60ec8.png","name":"美妆个护","cate_id":[{"name":"洗发护发","id":"77"},{"name":"身体护肤","id":"78"},{"name":"女性护理","id":"79"},{"name":"面部护理","id":"80"},{"name":"香水彩妆","id":"81"}],"id":"57"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca060fd7dcc5.png","name":"精选商品","cate_id":[],"id":"105"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca06107a91b3.png","name":"鞋帽箱包","cate_id":[{"name":"功能箱包","id":"83"},{"name":"精品男包","id":"99"}],"id":"59"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca06112af1cd.png","name":"家居家装","cate_id":[{"name":"居家日用","id":"84"},{"name":"五金","id":"85"},{"name":"洗护清洁","id":"86"},{"name":"餐饮厨具","id":"87"},{"name":"家纺","id":"88"},{"name":"杯壶餐具","id":"100"}],"id":"60"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca06119cdf41.png","name":"汽车用品","cate_id":[{"name":"汽车工具","id":"89"},{"name":"车载用品","id":"101"}],"id":"61"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca0618085ff1.png","name":"母婴玩具","cate_id":[{"name":"尿裤湿巾","id":"90"},{"name":"母婴用品","id":"91"},{"name":"母婴乐器","id":"92"}],"id":"62"},{"img":"http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca06126af633.png","name":"生活旅行","cate_id":[{"name":"户外用品","id":"102"}],"id":"63"}]
     * error_code : 0
     */
    private String msg;
    private List<DataEntity> data;
    private int error_code;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public int getError_code() {
        return error_code;
    }

    public static class DataEntity implements Serializable {
        /**
         * img : http://mjbb.oss-cn-shenzhen.aliyuncs.com/public/attachment/201903/31/14/5ca060a523062.png
         * name : 服装饰品
         * cate_id : [{"name":"女装","id":"30"},{"name":"男装","id":"31"},{"name":"家居服","id":"32"},{"name":"钟表","id":"33"},{"name":"女装系列","id":"93"},{"name":"男装系列","id":"94"},{"name":"内衣系列","id":"95"},{"name":"服饰配件","id":"96"},{"name":"宝珠首饰","id":"97"},{"name":"钟表","id":"98"}]
         * id : 24
         */
        private String img;
        private String name;
        private List<CateIdEntity> cate_id;
        private List<String> mPics;
        private int width;
        private int height;
        private String id;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public List<String> getPics() {
            return mPics;
        }

        public void setPics(List<String> pics) {
            mPics = pics;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCate_id(List<CateIdEntity> cate_id) {
            this.cate_id = cate_id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public String getName() {
            return name;
        }

        public List<CateIdEntity> getCate_id() {
            return cate_id;
        }

        public String getId() {
            return id;
        }

        public static class CateIdEntity implements Serializable {
            /**
             * name : 女装
             * id : 30
             */
            private String name;
            private String id;

            public void setName(String name) {
                this.name = name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public String getId() {
                return id;
            }
        }
    }
}
