package edu.stanford.bmir.protege.web.client.form;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-04-27
 */
public interface FormSelectorView extends IsWidget {

    @Nonnull
    AcceptsOneWidget addFormSelectorItem();

    void clear();
}
