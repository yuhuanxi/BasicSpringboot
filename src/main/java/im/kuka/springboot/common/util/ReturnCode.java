/**
 *
 */
package im.kuka.springboot.common.util;

/**
 * 请求响应状态码
 */
public enum ReturnCode {

    SUCCESS(200, "操作正确"),
    /**
     *
     */
    DATA_NOT_FOUND(520, "数据不存在"),

    /**
     *
     */
    FAIL(574, "内部异常"), PARAMER_NOT_INVALID(501, "参数不正确");

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
