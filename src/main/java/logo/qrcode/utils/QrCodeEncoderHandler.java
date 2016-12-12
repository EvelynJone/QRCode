package logo.qrcode.utils;

import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import logo.qrcode.image.MyMatrixToImageConfig;
import logo.qrcode.image.MyMatrixToImageWriter;
import logo.qrcode.log.LogManager;


public class QrCodeEncoderHandler {

	/**
	 * @param contents
	 *            生成的图片的名称
	 * @param width
	 *            生成的图片的宽度
	 * @param height
	 *            生成的图片的高度
	 * @param imgPath
	 *            生成的图片路径
	 * @param onColor
	 *         qrCode的颜色
	 * @param offColor
	 *         qrCode背景颜色 
	 */
	public void encode(String contents, int width, int height, String imgPath,
			String logoPath,int onColor , int offColor) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L ) ;
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			hints.put(EncodeHintType.MARGIN, 1 );
			// 设置生成二维码的类型
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);
			// 0xFFE30022 字体色,0xFFF4C2C2背景色
			MyMatrixToImageWriter.writeToFile(bitMatrix, "png", imgPath,
					new MyMatrixToImageConfig( onColor , offColor ), logoPath) ;
		} catch (Exception e) {
			LogManager.err(e);
		}
	}

	public static void main(String[] args) {
		String imgPath = "F:\\qrcode\\img.png";
		String logoPath = "F:\\qrcode\\logo.jpg";
		String contents = "http://www.baidu.com/" ;
		int width = 200, height = 200 ; 
		QrCodeEncoderHandler handler = new QrCodeEncoderHandler( ) ; 
		
		handler.encode(contents , width, height , imgPath ,
				logoPath , 0x74A327 ,0xFFFFFFF );  
	}
}


