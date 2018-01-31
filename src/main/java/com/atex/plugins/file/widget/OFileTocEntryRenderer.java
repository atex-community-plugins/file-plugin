package com.atex.plugins.file.widget;

import java.io.IOException;

import com.atex.onecms.content.Content;
import com.atex.onecms.content.ContentManager;
import com.atex.onecms.content.ContentResult;
import com.atex.onecms.content.ContentVersionId;
import com.atex.onecms.content.IdUtil;
import com.atex.onecms.content.Subject;
import com.atex.plugins.baseline.widget.OContentListEntryBasePolicyWidget;
import com.atex.plugins.file.FileContentDataBean;
import com.polopoly.cm.client.CmClient;
import com.polopoly.cm.client.CmClientBase;
import com.polopoly.orchid.OrchidException;
import com.polopoly.orchid.context.Device;
import com.polopoly.orchid.context.OrchidContext;

public class OFileTocEntryRenderer extends OContentListEntryBasePolicyWidget {

    @Override
    public void initSelf(final OrchidContext oc) throws OrchidException {
        super.initSelf(oc);
        CmClient cmClient = (CmClient) oc.getApplication().getApplicationComponent(CmClientBase.DEFAULT_COMPOUND_NAME);

        ContentManager contentManager = cmClient.getContentManager();
        ContentVersionId versionId = cmClient.getContentManager().resolve(IdUtil.fromPolicyContentId(getPolicy().getContentId()), Subject.NOBODY_CALLER);
        ContentResult<FileContentDataBean> result = contentManager
                .get(versionId, null, FileContentDataBean.class, null, Subject.NOBODY_CALLER);
        Content<FileContentDataBean> content = result.getContent();
    }

    @Override
    protected void renderEntryHeader(final Device device, final OrchidContext oc) throws OrchidException, IOException {
        device.println("<div class='toolbox-right'>");
        renderToolbox(device, oc);
        device.println("</div>");
    }

    @Override
    protected void renderEntryBody(final Device device, final OrchidContext oc) throws OrchidException, IOException {
        device.println("<div class='clearfix'>");

        contentLink.render(oc);
        device.println("</div>");
    }

    @Override
    protected void renderEntryFooter(final Device device, final OrchidContext oc)
        throws OrchidException, IOException {
        // Just empty
    }

}
