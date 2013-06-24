package de.effectivetrainings;

import org.apache.wicket.request.resource.DynamicImageResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author <a href=mailto:martin@effectivetrainings.de">Martin Dilger</a>
 * @since: 24.06.13
 */
public class VoodooKittenResource extends DynamicImageResource
{
	@Override
	protected byte[] getImageData(Attributes attributes)
	{
        int width = attributes.getParameters().get("width").toInt();
        int height = attributes.getParameters().get("height").toInt();
		try
		{
			BufferedImage dynamicKitten = ImageIO
					.read(new URL(String.format("http://www.placekitten.com/%s/%s",width,height)));
			dynamicKitten.getGraphics().fillOval(100, 120, 30, 30);
			dynamicKitten.getGraphics().fillOval(130, 110, 30, 30);

			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ImageIO.write(dynamicKitten, "png", bout);
			return bout.toByteArray();
		}
		catch (IOException e)
		{
			// ignore
		}
		throw new RuntimeException("Narf..");
	}
}
