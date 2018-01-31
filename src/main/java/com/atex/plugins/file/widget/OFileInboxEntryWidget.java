package com.atex.plugins.file.widget;

import com.atex.plugins.baseline.widget.ContentEntryProvider;
import com.atex.plugins.baseline.widget.OInboxItemBaseWidget;
import com.atex.plugins.file.FilePolicy;

/**
 * OFileInboxEntryWidget
 *
 * @author mnova
 */
public class OFileInboxEntryWidget extends OInboxItemBaseWidget {

    @Override
    public ContentEntryProvider getContentEntryProvider() {
        return new FileSearchEntryProvider((FilePolicy) getPolicy());
    }

}
