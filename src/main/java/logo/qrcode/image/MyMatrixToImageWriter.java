package logo.qrcode.image;


import com.google.zxing.common.BitMatrix;
import logo.qrcode.utils.Helper;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public final class MyMatrixToImageWriter {

  private static final MyMatrixToImageConfig DEFAULT_CONFIG = new MyMatrixToImageConfig();

  private MyMatrixToImageWriter() {}

  /**
   * Renders a {@link BitMatrix} as an image, where "false" bits are rendered
   * as white, and "true" bits are rendered as black.
   */
  public static BufferedImage toBufferedImage(BitMatrix matrix) {
    return toBufferedImage(matrix, DEFAULT_CONFIG);
  }

  /**
   * As {@link #toBufferedImage(BitMatrix)}, but allows customization of the output.
   */
  public static BufferedImage toBufferedImage(BitMatrix matrix, MyMatrixToImageConfig config) {
    int width = matrix.getWidth();
    int height = matrix.getHeight();
    BufferedImage image = new BufferedImage(width, height, config.getBufferedImageColorModel());
    int onColor = config.getPixelOnColor();
    int offColor = config.getPixelOffColor();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
      }
    }
    return image;
  }

  /**
   * Writes a {@link BitMatrix} to a file.
   *
   * @see #toBufferedImage(BitMatrix)
   */
  public static void writeToFile(BitMatrix matrix, String format, String imgPath,String logoPath) throws IOException {
    writeToFile(matrix, format, imgPath, DEFAULT_CONFIG,logoPath);
  }

  /**
   * As {@link #writeToFile(BitMatrix, String, String,MyMatrixToImageConfig,String)}, but allows customization of the output.
   */
  public static void writeToFile(BitMatrix matrix, String format, String imgPath, MyMatrixToImageConfig config,String logoPath) 
      throws IOException {  
    BufferedImage image = toBufferedImage(matrix, config);
    
    if(!Helper.isNull(logoPath)){
    //添加logo
    	overlapImage(image,imgPath,logoPath);
    }else{
    	 ImageIO.write(image, "png", new File(imgPath));  
    }
  }
  
  /**
   * 二维码添加自定义logo
   */
  public static void overlapImage(BufferedImage image,String imgPath, String logoPath){
	  try { 
          BufferedImage logo = ImageIO.read(new File(logoPath)); 
          Graphics2D g = image.createGraphics(); 
	      int width=image.getWidth()/5; 
	      int height=image.getHeight()/5;
	      int x=(image.getWidth()-width)/2;
          int y=(image.getHeight()-height)/2;
          g.drawImage(logo, x, y, width, height, null); 
          g.dispose(); 
          ImageIO.write(image, "png", new File(imgPath)); 
        } catch (Exception e) { 
          e.printStackTrace(); 
        } 
  }

  /**
   * Writes a {@link BitMatrix} to a stream.
   *
   * @see #toBufferedImage(BitMatrix)
   */
  public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
    writeToStream(matrix, format, stream, DEFAULT_CONFIG);
  }

  /**
   * As {@link #writeToStream(BitMatrix, String, OutputStream)}, but allows customization of the output.
   */
  public static void writeToStream(BitMatrix matrix, String format, OutputStream stream, MyMatrixToImageConfig config) 
      throws IOException {  
    BufferedImage image = toBufferedImage(matrix, config);
    if (!ImageIO.write(image, format, stream)) {
      throw new IOException("Could not write an image of format " + format);
    }
  }

}