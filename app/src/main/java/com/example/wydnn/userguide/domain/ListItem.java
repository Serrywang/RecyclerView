package com.example.wydnn.userguide.domain;

public class ListItem {

    /**
     * _id : 57c83792421aa97cada9b79d
     * created_at : 2016-09-01T22:13:38.420Z
     * icon : http://ww2.sinaimg.cn/large/610dc034gw1f9sg2pq9ufj202s02s0sj.jpg
     * id : qdaily
     * title : 好奇心日报
     */

    private String _id;
    private String created_at;
    private String icon;
    private String id;
    private String title;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
