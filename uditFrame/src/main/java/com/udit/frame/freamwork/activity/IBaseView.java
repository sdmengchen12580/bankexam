package com.udit.frame.freamwork.activity;


public interface IBaseView
{
    /**
     * 显示dialog
     * @param content
     */
    void showProgressDialog(String content);

    /**
     * 取消dailog
     */
    void dismissProgressDialog();

    /**
     * 显示Toast
     * @param message
     */
    void showShortToast(String message);

    /**
     * 显示LongToast
     * @param message
     */
    void showLongToast(String message);
}
