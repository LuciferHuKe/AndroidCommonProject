package com.lucifer.common.model;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by lucifer on 16/8/26.
 *
 * EventBus传递的消息类型
 */
public class EventMessage implements Serializable {

    private String action = "";  // 消息类型

    private Bundle data;  // 消息数据

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Bundle getData() {
        return data;
    }

    public void setData(Bundle data) {
        this.data = data;
    }
}
