package org.grails.plugins.imagegallery

import javax.media.jai.JAI
import com.sun.media.jai.codec.FileSeekableStream
import javax.media.jai.InterpolationNearest
import java.awt.image.renderable.ParameterBlock
import javax.media.jai.RenderedOp
import com.sun.media.jai.codec.ByteArraySeekableStream

class ImageUtil {

    private RenderedOp image = null;
    private RenderedOp result = null;

    /* Removes the accelaration lib exception */
    static { System.setProperty("com.sun.media.jai.disableMediaLib", "true"); }

    /**
     * Should a thumbnail be created only if it will be smaller in size than
     * the current image?
     */
    boolean decreaseOnly = true;

    /**
     * Returns the height for the currently loaded image
     *
     * @return height of the currently loaded image
     */
    public getHeight() {
        return image.getHeight()
    }

    /**
     * Returns the width for the currently loaded image
     *
     * @return width of the currently loaded image
     */
    public getWidth() {
        return image.getWidth()
    }

    public void thumbnail(float edgeLength) {
        if (height < edgeLength && width < edgeLength && decreaseOnly) {
            result = image
        }
        else {
            boolean tall = (height > width);
            float modifier = edgeLength / (float) (tall ? height : width);
            ParameterBlock params = new ParameterBlock();
            params.addSource(image);
            params.add(modifier);//x scale factor
            params.add(modifier);//y scale factor
            params.add(0.0F);//x translate
            params.add(0.0F);//y translate
            params.add(new InterpolationNearest());//interpolation method
            result = JAI.create("scale", params);
        }
    }

    /**
     * Loads an image from a file.
     *
     * @param file path to the file from which the image should be loaded
     */
    public void load(String file) {
        FileSeekableStream fss = new FileSeekableStream(file);
        image = JAI.create("stream", fss);
    }

    /**
     * Loads an image from a byte array.
     *
     * @param bytes array to be used for image initialization
     */
    public void load(byte[] bytes) {
        ByteArraySeekableStream byteStream = new ByteArraySeekableStream(bytes);
        image = JAI.create("stream", byteStream);
    }

    /**
     * Returns the resulting image as a byte array.
     *
     * @param type file type for the image
     * @see <a href="http://java.sun.com/products/java-media/jai/iio.html">Possible JAI encodings</a>
     */
    public byte[] getBytes(String type) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        JAI.create("encode", result, bos, type, null);
        return bos.toByteArray()
    }

}
