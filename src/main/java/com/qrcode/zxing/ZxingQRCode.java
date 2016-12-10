package com.qrcode.zxing;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qrcode.exception.FormateNotIncorrectException;
import com.qrcode.util.FileUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Class Name : ZxingQRCode<BR>
 * Descripe : TODO(这里用一句话描述这个类的作用)<BR>
 * Create by : zhaoxl<BR>
 * DATE: 2016/12/1014:18<BR>
 * Version: V1.0<BR>
 * <p/>
 * copyright 轻重府.
 */
public class ZxingQRCode {

    public static void main(String[] args) throws IOException {
        File file =  FileUtil.createNewFile("F:/qrcode/img.png");
        try {
            createQRCodeToPath(file.toPath(),"爱鹿晗",300,300,null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        readQRCode(file);
    }

    /**
     *  create QRCode to toFile
     * @param toFile the destFile
     * @param content   the text of QRContent
     * @param width the width of QRCode
     * @param height the height of QRCode
     * @param format the format of QRCode
     * @throws IOException
     * @throws WriterException
     */
    public static void createQRCodeToFile(File toFile,String content,int width,int height,String format) throws IOException,WriterException{
        format = checkFormat(format);
        BitMatrix bitMatrix = toBitMatrix(content,width,height);
        MatrixToImageWriter.writeToFile(bitMatrix,format,toFile);
    }

    /**
     *  create QRCode to path
     * @param path  the destPath of QRCode
     * @param content   the text of QRContent
     * @param width the width of QRCode
     * @param height the height of QRCode
     * @param format the format of QRCode
     * @throws IOException
     * @throws WriterException
     */
    public static void createQRCodeToPath(Path path,String content,int width,int height,String format) throws IOException,WriterException{
        format = checkFormat(format);
        BitMatrix bitMatrix = toBitMatrix(content,width,height);
        MatrixToImageWriter.writeToPath(bitMatrix,format,path);
    }

    /**
     *  create QRCode to outputStream
     * @param outputStream the destOutputStream of QRCode
     * @param content   the text of QRContent
     * @param width the width of QRCode
     * @param height the height of QRCode
     * @param format the format of QRCode
     * @throws IOException
     * @throws WriterException
     */
    public static void createQRCodeToStream(OutputStream outputStream,String content,int width,int height,String format) throws IOException,WriterException{
        format = checkFormat(format);
        BitMatrix bitMatrix = toBitMatrix(content,width,height);
        MatrixToImageWriter.writeToStream(bitMatrix,format,outputStream);
    }

    /**
     *  use content,width,height to generate BitMatrix
     * @param content
     * @param width
     * @param height
     * @return
     * @throws WriterException
     */
    private static BitMatrix toBitMatrix(String content,int width,int height) throws WriterException {
        //二维码的参数
        HashMap hits = new HashMap();
        hits.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 编码
        hits.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  // 容错等级
        hits.put(EncodeHintType.MARGIN, 2); // 图片边距
        // 生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hits);
        return bitMatrix;
    }

    /**
     *  check the format for correctness.
     *  If the format value is right then not change anything;
     *  If the format value is empty then the assignment is png.
     *  If the format value is not empty and the format is incorrect then throw the FormateNotIncorrectException
     * @param format
     * @return
     */
    private static String checkFormat(String format) {
        if (null != format && !format.equals("") && !( format.equals("png") || format.equals("jpg") || format.equals("jpge"))) {
            throw new FormateNotIncorrectException();
        }
        if (null == format || format.equals("")) {
            format = "png";
        }
        return format;
    }


    /**
     * 解析二维码内容
     * @param file
     */
    public static void readQRCode(File file) {
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            // 拿到Image图片
            BufferedImage image = ImageIO.read(file);
            HashMap hits = new HashMap();
            hits.put(EncodeHintType.CHARACTER_SET,"utf-8");
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            // 解析二维码
            Result result = formatReader.decode(binaryBitmap,hits);

            System.out.println("解析结果： " + result.toString());
            System.out.println("二维码类型：" + result.getBarcodeFormat());
            System.out.println("二维码文本内容： " + result.getText());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
