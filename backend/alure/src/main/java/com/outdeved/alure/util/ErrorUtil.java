package com.outdeved.alure.util;

public class ErrorUtil {

    public static Throwable getRootException(Throwable throwable) {
        Throwable root = throwable;

        while (root.getCause() != null) {
            root = root.getCause();
        }
        return root;
    }

}
