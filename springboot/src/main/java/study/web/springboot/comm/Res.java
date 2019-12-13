package study.web.springboot.comm;

public class Res<T> {

    private Integer status;
    private String msg;
    private T data;

    public Res() {}

    public Res(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Res success(String msg) {
        return new Res(200, msg, null);
    }

    public static <T> Res success(String msg, T data) {
        if (data != null) {
            return new Res(200, msg, data);
        } else {
            return success(msg);
        }
    }

    public static <T> Res error(String msg) {
        return new Res(400, msg, null);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
