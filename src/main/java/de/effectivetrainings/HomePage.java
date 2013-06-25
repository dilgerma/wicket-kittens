package de.effectivetrainings;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
        add(staticKitten());
        add(staticResourceKitten());
        add(staticExternalKitten());
        add(dynamicKitten());
        add(superDynamicKitten());
        add(fallbackDynamicKitten());
    }

    private Component fallbackDynamicKitten(){
        return new Image("fallbackDynamicKitten", new FallbackVoodooKittenResource(HomePage.class,"fallbackKitten.jpg","jpg"));
    }

    private Component superDynamicKitten(){
        return new ContextImage("superDynamicKitten","/voodoo/270/299");
    }

    private Component dynamicKitten() {
        return new Image("dynamicKitten", new DynamicImageResource() {
            @Override
            protected byte[] getImageData(Attributes attributes) {
                try {
                    BufferedImage dynamicKitten = ImageIO.read(new URL("http://www.placekitten.com/300/400"));
                    dynamicKitten.setRGB(255,255,255);
                    dynamicKitten.getGraphics().fillOval(100,120,30,30);
                    dynamicKitten.getGraphics().fillOval(130,110,30,30);

                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    ImageIO.write(dynamicKitten, "png", bout);
                    return bout.toByteArray();
                } catch (IOException e) {
                   //ignore
                }
                throw new RuntimeException("Narf..");
            }
        });
    }

    private Component staticExternalKitten() {
        return new ExternalImage("staticExternalKitten", Model.of("http://www.placekitten.com/300/300"));
    }

    private Component staticKitten() {
        return new Image("staticKitten", new Model<String>());
    }

    private Component staticResourceKitten() {
        return new ContextImage("staticResourceKitten", "/images/kitten2.jpg");
    }
}
