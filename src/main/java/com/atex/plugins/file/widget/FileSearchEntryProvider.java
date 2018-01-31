package com.atex.plugins.file.widget;

import java.util.Optional;

import com.atex.plugins.baseline.widget.SearchEntryProvider;
import com.atex.plugins.file.FileContentDataBean;
import com.atex.plugins.file.FilePolicy;
import com.polopoly.cm.client.CMException;

/**
 * FileSearchEntryProvider
 *
 * @author mnova
 */
public class FileSearchEntryProvider implements SearchEntryProvider<FilePolicy> {

    private final FilePolicy policy;

    public FileSearchEntryProvider(final FilePolicy policy) {
        this.policy = policy;
    }

    @Override
    public FilePolicy getPolicy() {
        return policy;
    }

    @Override
    public long getModifiedDateTime() {
        return policy.getVersionInfo().getCommitted();
    }

    @Override
    public String getName() {
        String name = getBean()
                .map(FileContentDataBean::getTitle)
                .orElse(null);
        if (name == null) {
            try {
                name = policy.getName();
            } catch (CMException e) {
                name = null;
            }
        }
        return name;
    }

    @Override
    public String getLead() {
        return getBean()
                .map(FileContentDataBean::getDescription)
                .orElse(null);
    }

    @Override
    public String getByline() {
        return getBean()
                .map(FileContentDataBean::getByline)
                .orElse(null);
    }

    private Optional<FileContentDataBean> getBean() {
        try {
            return Optional.ofNullable(policy.getContentData());
        } catch (CMException e) {
            return Optional.empty();
        }
    }

}
