package cn.edu.xmu.vantel.core.model;

import lombok.Data;

@Data
public class Response<T> {
    private int errno;
    private String errmsg;
    private T data;

    public Response(int errno, String errmsg, T data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }

    public Response(int errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
    }
}
