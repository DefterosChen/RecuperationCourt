package com.kyy.recuperationcourt.common.model.entity.message.inter;

import java.util.List;

public class NewsTypeData {


    /**
     * code : 0
     * msg : null
     * data : {"records":[{"id":"1","pid":"6","level":2,"name":"热点","description":"热点","icon":"icon","type":1,"alias":"hot","systemId":1,"orders":0,"createTime":null},{"id":"2","pid":"5","level":2,"name":"美妆","description":"美妆","icon":"","type":1,"alias":"chinaeconomic","systemId":1,"orders":1489590768989,"createTime":null},{"id":"3","pid":"5","level":2,"name":"食疗","description":"食疗","icon":"","type":1,"alias":"japaneconomic","systemId":1,"orders":1491636586316,"createTime":null},{"id":"4","pid":null,"level":1,"name":"母婴","description":"母婴","icon":"","type":1,"alias":"people","systemId":2,"orders":1491636586317,"createTime":null},{"id":"5","pid":null,"level":1,"name":"养生","description":"养生","icon":"","type":1,"alias":"economic","systemId":1,"orders":1489590733919,"createTime":null},{"id":"6","pid":null,"level":1,"name":"生活","description":"生活","icon":null,"type":1,"alias":"technic","systemId":3,"orders":1491636586318,"createTime":null}],"total":6,"size":100,"current":1,"orders":[],"searchCount":true,"pages":1}
     * ok : true
     */

    private int code;
    private String msg;
    private DataBean data;
    private boolean ok;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public static class DataBean {
        /**
         * records : [{"id":"1","pid":"6","level":2,"name":"热点","description":"热点","icon":"icon","type":1,"alias":"hot","systemId":1,"orders":0,"createTime":null},{"id":"2","pid":"5","level":2,"name":"美妆","description":"美妆","icon":"","type":1,"alias":"chinaeconomic","systemId":1,"orders":1489590768989,"createTime":null},{"id":"3","pid":"5","level":2,"name":"食疗","description":"食疗","icon":"","type":1,"alias":"japaneconomic","systemId":1,"orders":1491636586316,"createTime":null},{"id":"4","pid":null,"level":1,"name":"母婴","description":"母婴","icon":"","type":1,"alias":"people","systemId":2,"orders":1491636586317,"createTime":null},{"id":"5","pid":null,"level":1,"name":"养生","description":"养生","icon":"","type":1,"alias":"economic","systemId":1,"orders":1489590733919,"createTime":null},{"id":"6","pid":null,"level":1,"name":"生活","description":"生活","icon":null,"type":1,"alias":"technic","systemId":3,"orders":1491636586318,"createTime":null}]
         * total : 6
         * size : 100
         * current : 1
         * orders : []
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;
        private List<?> orders;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public List<?> getOrders() {
            return orders;
        }

        public void setOrders(List<?> orders) {
            this.orders = orders;
        }

        public static class RecordsBean {
            /**
             * id : 1
             * pid : 6
             * level : 2
             * name : 热点
             * description : 热点
             * icon : icon
             * type : 1
             * alias : hot
             * systemId : 1
             * orders : 0
             * createTime : null
             */

            private String id;
            private String pid;
            private int level;
            private String name;
            private String description;
            private String icon;
            private int type;
            private String alias;
            private int systemId;
            private long orders;
            private String createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
            }

            public int getSystemId() {
                return systemId;
            }

            public void setSystemId(int systemId) {
                this.systemId = systemId;
            }

            public long getOrders() {
                return orders;
            }

            public void setOrders(long orders) {
                this.orders = orders;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
