package com.zc.degou.network.socket;

/**
 * Created by lucifer on 17/1/9.
 *
 * @deprecated socket请求回调接口
 */

public interface SocketCallbackInterface {

    public abstract void requestSuccess();

    public abstract void requestFail();

    public abstract void requestFinish();

}
