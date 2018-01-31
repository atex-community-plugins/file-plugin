package com.atex.plugins.file.widget;

import com.atex.plugins.baseline.widget.OAbstractSearchThumb;
import com.atex.plugins.baseline.widget.SearchEntryProvider;
import com.atex.plugins.file.FilePolicy;

@SuppressWarnings("serial")
public class OSearchThumb extends OAbstractSearchThumb {

    private static final int DEFAULT_WIDGET_WIDTH = 115;

    @Override
    public SearchEntryProvider getSearchEntryProvider() {
        return new FileSearchEntryProvider((FilePolicy) getPolicy());
    }

    @Override
    protected int getWidth() {
        return DEFAULT_WIDGET_WIDTH;
    }

    @Override
    protected String getCSSClass() {
        return "custom-search-image";
    }

}
