package com.lagou.func1;

public class AgeException extends Exception {

    static final long serialVersionUID = -3387516993124229948L; // 序列化的版本号  与序列化操作有关系

    public AgeException() {
    }

    public AgeException(String message) {
        super(message);
    }
}

