package com.atex.plugins.file;

import com.atex.onecms.content.AspectedPolicy;
import com.atex.onecms.content.metadata.MetadataInfo;
import com.polopoly.application.Application;
import com.polopoly.application.IllegalApplicationStateException;
import com.polopoly.cm.client.CMException;
import com.polopoly.cm.client.CMRuntimeException;
import com.polopoly.cm.client.CmClient;
import com.polopoly.metadata.Metadata;
import com.polopoly.metadata.MetadataAware;
import com.polopoly.metadata.util.MetadataUtil;


public class FilePolicy extends AspectedPolicy<FileContentDataBean> implements MetadataAware {

    /**
     * @param cmClient needed for accessing the model domain.
     */
    public FilePolicy(final CmClient cmClient, final Application application) throws IllegalApplicationStateException {
        super(cmClient, application);
    }

    @Override
    public Metadata getMetadata()  {
      try {
        MetadataInfo metadata = (MetadataInfo) getAspect("atex.Metadata");
        if (metadata != null) {
          return metadata.getMetadata();
        }
      } catch (CMException e) {
      }

      return new Metadata();
    }

    @Override
    public void setMetadata(final Metadata metadata) {
      try {
        MetadataInfo metadataInfo= new MetadataInfo();
        metadataInfo.setMetadata(metadata);
        metadataInfo.setTaxonomyIds(MetadataUtil.getTaxonomyIds(this));
        setAspect("atex.Metadata", metadataInfo);
      } catch (CMException e) {
        throw new CMRuntimeException("Unable to set metadata", e);
      }
    }

}
