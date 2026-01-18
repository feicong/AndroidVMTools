package com.v7878.vmtools;

import com.v7878.unsafe.invoke.EmulatedStackFrame;
import com.v7878.unsafe.invoke.Transformers.AbstractTransformer;

import java.lang.invoke.MethodHandle;

// MethodHandle Hook 的变换回调，允许在模拟栈帧中改写调用行为。
public interface HookTransformer {
    void transform(MethodHandle original, EmulatedStackFrame stack) throws Throwable;
}

final class HookTransformerImpl extends AbstractTransformer {
    private final MethodHandle original;
    private final HookTransformer transformer;

    HookTransformerImpl(MethodHandle original, HookTransformer transformer) {
        this.original = original;
        this.transformer = transformer;
    }

    @Override
    protected void transform(MethodHandle thiz, EmulatedStackFrame stack) throws Throwable {
        // 保证栈帧类型与原方法一致，再交给用户实现处理。
        stack.type(original.type());
        transformer.transform(original, stack);
    }
}
