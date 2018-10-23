package edu.stanford.bmir.protege.web.client.viz;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.gwt.user.client.ui.IsWidget;
import edu.stanford.bmir.protege.web.shared.entity.OWLEntityData;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 20 Oct 2018
 */
public interface LargeGraphMessageView extends IsWidget {

    interface DisplayGraphHandler {
        void handleDisplayGraph();
    }

    void setDisplayMessage(@Nonnull OWLEntityData entity,
                           int nodeCount,
                           int edgeCount,
                           @Nonnull Multiset<OWLEntityData> edgeMultiset);

    void setDisplayGraphHandler(@Nonnull DisplayGraphHandler handler);
}