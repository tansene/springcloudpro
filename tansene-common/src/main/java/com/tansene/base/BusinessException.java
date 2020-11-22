package com.tansene.base;

/**
 * @author tansene
 * @since on 2020/11/22.
 */
public class BusinessException extends Exception{
    /**
     * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
     */
    private static final long serialVersionUID = 3455708526465670030L;

    public BusinessException(String msg){
        super(msg);
    }
}