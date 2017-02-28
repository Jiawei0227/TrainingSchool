package nju.wjw.util;

/**
 * Created by Jerry Wang on 2017/2/15.
 * 消息返回类
 */
public class ResultMsg {

    private int state;

    private Object info;

    public int getState() {
        return state;
    }

    public Object getInfo() {
        return info;
    }

    public ResultMsg(int state, Object info) {
        this.state = state;
        this.info = info;
    }
}
