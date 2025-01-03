package com.swagLabs.utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ImageUtils 
{
	/**
     * Converts a BufferedImage to a Base64-encoded string.
     *
     * @param image The BufferedImage to convert
     * @return The Base64-encoded string representing the image
     * @throws IOException if there's an error during image processing
     */
    public static String toBase64(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);  // Writing the image to the byte array in PNG format
        byte[] imageBytes = baos.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);  // Convert to Base64 string
    }
}
