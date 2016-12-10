package com.qrcode.qrcode;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * Class Name : MyQRCodeImage<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/1016:18<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public class MyQRCodeImage implements QRCodeImage {

    private BufferedImage bufferedImage;

    public MyQRCodeImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    /**
     * 获取颜色
     * @param x
     * @param y
     * @return
     */
    public int getPixel(int x, int y) {
        return bufferedImage.getRGB(x,y);
    }
}
