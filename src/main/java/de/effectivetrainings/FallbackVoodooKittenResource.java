package de.effectivetrainings;

import org.apache.wicket.request.resource.DynamicImageResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author <a href=mailto:martin@effectivetrainings.de">Martin Dilger</a>
 * @since: 25.06.13
 */
public class FallbackVoodooKittenResource extends DynamicImageResource
{

	private Class<?> scope;
	private String fallbackFileName;
	private String fileSuffix;

	public FallbackVoodooKittenResource(Class<?> scope, String fallbackImageFilename, String suffix)
	{
		this.scope = scope;
		this.fallbackFileName = fallbackImageFilename;
		this.fileSuffix = suffix;
	}

	@Override
	protected byte[] getImageData(Attributes attributes)
	{
		try
		{

            return loadImage(HomePage.class,"brokenKitten.jpg","jpg");
		}
		catch (Exception e)
		{
            return loadImage(scope,fallbackFileName,fileSuffix);
        }

	}

    private byte[] loadImage(Class<?> scope, String filename, String suffix) {
        try
        {
            final BufferedImage loadedImage = ImageIO.read(
                scope.getResourceAsStream(filename));
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ImageIO.write(loadedImage, suffix, bout);
            return bout.toByteArray();
        }
        catch (IOException ioe)
        {
            return new byte[0];
        }
    }
}
