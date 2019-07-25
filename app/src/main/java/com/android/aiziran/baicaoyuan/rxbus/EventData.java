package com.android.aiziran.baicaoyuan.rxbus;

public class EventData {
    /**
     * count 取值
     *  0 表示通知homeFragment改变选中的城市
     */
    private EventTag tag;
    private Object object;

    public EventData(EventTag tag, Object object){
        this.tag = tag;
        this.object = object;
    }
    public EventData(EventTag count){
        this.tag = count;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public EventTag getTag() {
        return tag;
    }

    public void setTag(EventTag tag) {
        this.tag = tag;
    }
}
