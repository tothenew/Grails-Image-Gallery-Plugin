package org.grails.plugins.imagegallery

import javax.media.jai.JAI
import com.sun.media.jai.codec.FileSeekableStream
import javax.media.jai.InterpolationNearest
import java.awt.image.renderable.ParameterBlock
import javax.media.jai.RenderedOp
import com.sun.media.jai.codec.ByteArraySeekableStream
import javax.media.jai.InterpolationBilinear
import javax.media.jai.InterpolationBicubic
import javax.media.jai.InterpolationBicubic2
import java.awt.RenderingHints

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



  /**
	 * This method creates a thumbnail of the maxWidth and maxHeight it takes as a parameter
	 *
	 * Example : Calling the method thumnailSpecial(640, 480, 1, 1)
	 * will never produce images larger than 640 on the width, and never larger than 480 on the height and use
	 * InterpolationBilinear(8) and scale
	 *
	 * @param maxWidth
	 * The maximum width the thumbnail is allowed to have
	 *
	 * @param maxHeigth
	 * The maximum height the thumbnail is allowed to have
	 *
	 * @param interPolationType
	 * Is for you to choose what interpolation you wish to use
	 * 1 : InterpolationBilinear(8) // Produces good image quality with smaller image size(byte) then the other two
	 * 2 : InterpolationBicubic(8)  // Supposed to produce better than above, but also larger size(byte)
	 * 3 : InterpolationBicubic2(8) // Supposed to produce the best of the three, but also largest size(byte)
	 *
	 * @param renderingType
	 * Too choose the rendering type
	 * 1: Uses scale // Better on larger thumbnails
	 * 2: Uses SubsampleAverage  // Produces clearer images when it comes to really small thumbnail e.g 80x60
	 */
	public void thumbnailSpecial(float maxWidth, float maxHeight, int interPolationType, int renderingType) {
		if (height <= maxHeight && width <= maxWidth) {
			/* Don't change, keep it as it is, even though one might loose out on the compression included below (not sure)*/
			result = image
		}
		else {
			boolean tall = (height * (maxWidth / maxHeight) > width);
			float modifier = maxWidth / (float) (tall ? (height * (maxWidth / maxHeight)) : width);
			ParameterBlock params = new ParameterBlock();
			params.addSource(image);

			/* Had to do this because of that the different rendering options require either float or double */
			switch (renderingType) {
				case 1: params.add(modifier);//x scale factor
					params.add(modifier);//y scale factor
					break;
				case 2: params.add((double) modifier);//x scale factor
					params.add((double) modifier);//y scale factor
					break;
				default:
					params.add(modifier);//x scale factor
					params.add(modifier);//y scale factor
					break;
			}

			params.add(0.0F);//x translate
			params.add(0.0F);//y translate
			switch (interPolationType) {
				case 1: params.add(new InterpolationBilinear(8)); break; // Produces good image quality with smaller image size(byte) then the other two
				case 2: params.add(new InterpolationBicubic(8)); break;  // Supposed to produce better than above, but also larger size(byte)
				case 3: params.add(new InterpolationBicubic2(8)); break; // Supposed to produce the best of the two, but also largest size(byte)
				default: params.add(new InterpolationBilinear(8)); break;
			}

			switch (renderingType) {
				case 1: result = JAI.create("scale", params); break;
				case 2:
					RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					result = JAI.create("SubsampleAverage", params, qualityHints); break;
				default: result = JAI.create("scale", params); break;
			}
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
