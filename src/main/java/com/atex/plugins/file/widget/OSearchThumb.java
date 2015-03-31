package com.atex.plugins.file.widget;

import com.atex.onecms.content.Content;
import com.atex.onecms.content.ContentManager;
import com.atex.onecms.content.ContentResult;
import com.atex.onecms.content.ContentVersionId;
import com.atex.onecms.content.IdUtil;
import com.atex.onecms.content.Subject;
import com.atex.plugins.file.FileContentDataBean;
import com.polopoly.cm.app.search.widget.OSearchThumbBase;
import com.polopoly.cm.client.CMException;
import com.polopoly.cm.client.CmClient;
import com.polopoly.cm.client.CmClientBase;
import com.polopoly.html.CharConv;
import com.polopoly.orchid.OrchidException;
import com.polopoly.orchid.context.Device;
import com.polopoly.orchid.context.OrchidContext;

import com.polopoly.util.StringUtil;

import java.io.IOException;

@SuppressWarnings("serial")
public class OSearchThumb extends OSearchThumbBase {

    private static final int DEFAULT_TEXT_LENGTH = 20;
    private static final int DEFAULT_WIDGET_WIDTH = 115;
    private String name;

    @Override
    public void initSelf(final OrchidContext oc) throws OrchidException {
        super.initSelf(oc);

        CmClient cmClient = (CmClient) oc.getApplication().getApplicationComponent(CmClientBase.DEFAULT_COMPOUND_NAME);
        ContentManager contentManager = cmClient.getContentManager();
        ContentVersionId versionId = cmClient.getContentManager().resolve(IdUtil.fromPolicyContentId(getPolicy().getContentId()), Subject.NOBODY_CALLER);
        ContentResult<FileContentDataBean> result = contentManager
                .get(versionId, null, FileContentDataBean.class, null, Subject.NOBODY_CALLER);
        Content<FileContentDataBean> content = result.getContent();
        
        FileContentDataBean contentData = content.getContentData();

        this.name = contentData.getTitle();
        if (StringUtil.isEmpty(this.name)) {
            try {
                this.name = getPolicy().getContent().getName();
            } catch (CMException e) {
            }
        }
        if (StringUtil.isEmpty(this.name)) {
            this.name = getPolicy().getContentId().getContentId().getContentIdString();
        }
    }

    @Override
    protected int getWidth() {
        return DEFAULT_WIDGET_WIDTH;
    }

    @Override
    protected void renderThumb(final OrchidContext oc) throws IOException, OrchidException {
        Device device = oc.getDevice();

        device.print("<div title='" + CharConv.CC.toHTML(name) + "'>");

        device.print("<p>" + abbreviate(name, DEFAULT_TEXT_LENGTH) + "</p>");
        device.print("</div>");
    }

    @Override
    protected String getCSSClass() {
        return "custom-search-image";
    }

    public static String abbreviate(final String str, final int maxWidth) {
        return str != null && str.length() > maxWidth
                ? str.substring(0, maxWidth - 3) + "..."
                : str;
    }

}
