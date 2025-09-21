package com.mindvoice.psych.common.api;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;       // 状态码
    private String message; // 提示信息
    private T data;         // 返回数据

    // 构造方法私有化，避免外部随意 new
    private ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功返回（带数据）
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    // 成功返回（自定义消息）
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(200, message, data);
    }

    // 成功返回（无数据）
    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(200, "success", null);
    }

    // 失败返回
    public static <T> ApiResponse<T> fail(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    // 默认错误
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(500, message, null);
    }
}
