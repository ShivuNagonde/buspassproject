package com.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootBusPassWithMysqlAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBusPassWithMysqlAppApplication.class, args);

		// String inputFilePath = "C:\\Users\\Shivakumar\\Pictures\\hh.png";
		/*
		 * ITesseract tesseract = new Tesseract(); tesseract.setDatapath(
		 * "E:\\myprojets\\spring-boot-bus-pass-with-mysql-app\\tessdata\\"); String
		 * fullText; try { fullText = tesseract.doOCR(new
		 * File("C:\\Users\\Shivakumar\\Pictures\\s.png.png"));
		 * System.out.println(fullText); } catch (TesseractException e) {
		 * System.out.println("Exception : "+ e.getMessage()); }
		 */

		/*
		 * boolean saveImage(RenderedImage bufferedImage, String formatName, File
		 * localOutputFile, int dpi) throws IOException { boolean success;
		 * 
		 * if (formatName.equals(BlankPageMapRequest.ImageFormat.PNG.toString())) {
		 * ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
		 * 
		 * ImageWriteParam writeParam = writer.getDefaultWriteParam();
		 * ImageTypeSpecifier typeSpecifier =
		 * ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
		 * 
		 * IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier,
		 * writeParam);
		 * 
		 * final String pngMetadataFormatName = "javax_imageio_png_1.0";
		 * 
		 * // Convert dpi (dots per inch) to dots per meter final double metersToInches
		 * = 39.3701; int dotsPerMeter = (int) Math.round(dpi * metersToInches);
		 * 
		 * IIOMetadataNode pHYs_node = new IIOMetadataNode("pHYs");
		 * pHYs_node.setAttribute("pixelsPerUnitXAxis", Integer.toString(dotsPerMeter));
		 * pHYs_node.setAttribute("pixelsPerUnitYAxis", Integer.toString(dotsPerMeter));
		 * pHYs_node.setAttribute("unitSpecifier", "meter");
		 * 
		 * IIOMetadataNode root = new IIOMetadataNode(pngMetadataFormatName);
		 * root.appendChild(pHYs_node);
		 * 
		 * metadata.mergeTree(pngMetadataFormatName, root);
		 * 
		 * writer.setOutput(ImageIO.createImageOutputStream(localOutputFile));
		 * writer.write(metadata, new IIOImage(bufferedImage, null, metadata),
		 * writeParam); writer.dispose();
		 * 
		 * success = true; } else { success = ImageIO.write(bufferedImage, formatName,
		 * localOutputFile); }
		 * 
		 * return success;
		 * 
		 * }
		 */
	}
}
