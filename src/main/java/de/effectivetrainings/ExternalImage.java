package de.effectivetrainings;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * @author <a href=mailto:martin@effectivetrainings.de">Martin Dilger</a>
 * @since: 24.06.13
 */
public class ExternalImage extends WebMarkupContainer {
    public ExternalImage(String id, IModel<String> model) {
        super(id, model);
        add(AttributeAppender.replace("src", model));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        checkComponentTag(tag,"img");
    }
}
