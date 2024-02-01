package com.yigitkula.stockmanagement.productservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode{
    OK(1000),
    ERROR(1001),
    SUCCES(1002),
    PRODUCT_NOT_CREATED(1500),
    PRODUCT_SUCCESFULLY_CREATED(1501),
    PRODUCT_NOT_FOUND(1502),
    PRODUCT_SUCCESFULLY_UPDATED(1503),
    PRODUCT_ALREADY_DELETED(1504),
    PRODUCT_SUCCESFULLY_DELETED(1505);
    private final int value;

    FriendlyMessageCodes(int value){this.value = value;}
    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
