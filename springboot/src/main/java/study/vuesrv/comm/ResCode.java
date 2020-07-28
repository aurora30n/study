package study.vuesrv.comm;

public enum ResCode {
    success(200, "成功"),
    fail(400, "失败");

    private Integer code;
    private String name;

    ResCode(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
