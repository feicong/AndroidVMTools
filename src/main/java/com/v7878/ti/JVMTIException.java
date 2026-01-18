package com.v7878.ti;

// 将 JVMTI 错误码转换为带错误名的运行时异常。
public class JVMTIException extends RuntimeException {
    public JVMTIException(int error) {
        // 通过 JVMTI.GetErrorName 生成可读错误信息。
        super(JVMTI.GetErrorName(error));
    }
}
