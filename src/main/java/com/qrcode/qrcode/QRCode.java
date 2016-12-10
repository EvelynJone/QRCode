package com.qrcode.qrcode;

import com.qrcode.util.FileUtil;
import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class Name : QRCode<BR>
 * Descripe : 基于java gui的基础上生成的二维码<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/1015:40<BR>
 * Version: V1.0<BR>
 * <p/>
 */
public class QRCode {
    public static void main(String[] args) throws IOException {
        File file = FileUtil.createNewFile("F:/qrcode/qrcode.png");
        createQRCode(file);
        readQRCode(file);
    }

    public static void createQRCode(File file) throws IOException {
        Qrcode qrcode = new Qrcode();
        qrcode.setQrcodeErrorCorrect('M'); // 纠错等级
        qrcode.setQrcodeEncodeMode('B'); // N:表示汉字，A:表示a~z，B：表示其他字符
        qrcode.setQrcodeVersion(7);

        String qrData = "www.baidu.com";

        int width = getWidthOrHeight(7),height=getWidthOrHeight(7);
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0,0,width,height);
        int pixoff = 2; // 偏移量,如果不加，可能会导致解析出错

        // 如果是汉字，qrData.getBytes("gb2312")
        byte[] d = qrData.getBytes();
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = qrcode.calQrcode(d);

            for (int i = 0; i < s.length; i++) {
                for (int j = 0 ; j < s.length; j++) {
                    if (s[i][j]) {
                        gs.fillRect(i*3+pixoff,j*3+pixoff,3,3);
                    }
                }
            }
        }
        gs.dispose();
        bufferedImage.flush();
        ImageIO.write(bufferedImage,"png",file);
    }

    private static int getWidthOrHeight(int version) {
        return 67 + 12 * (version - 1);
    }

    public static void readQRCode(File file) throws IOException {
        // 读取图片
        BufferedImage bufferedImage = ImageIO.read(file);
        QRCodeDecoder codeDecoder = new QRCodeDecoder();
        String result = new String(codeDecoder.decode
                (new MyQRCodeImage(bufferedImage)),"gb2312");
        System.out.println(result);
    }
}
