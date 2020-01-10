package edu.stanford.bmir.protege.web.client.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import edu.stanford.bmir.protege.web.client.editor.ValueEditor;
import edu.stanford.bmir.protege.web.client.editor.ValueEditorFactory;
import edu.stanford.bmir.protege.web.client.editor.ValueListFlexEditorDirection;
import edu.stanford.bmir.protege.web.client.editor.ValueListFlexEditorImpl;
import edu.stanford.bmir.protege.web.shared.DirtyChangedHandler;
import edu.stanford.bmir.protege.web.shared.form.data.FormControlData;
import edu.stanford.bmir.protege.web.shared.form.field.Repeatability;

import java.util.List;
import java.util.Optional;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 30/03/16
 */
public class FormFieldControlImpl extends Composite implements FormFieldControl {

    interface FormElementEditorImplUiBinder extends UiBinder<HTMLPanel, FormFieldControlImpl> {

    }

    private static FormElementEditorImplUiBinder ourUiBinder = GWT.create(FormElementEditorImplUiBinder.class);



    @UiField
    SimplePanel editorHolder;

    private final HandlerManager handlerManager = new HandlerManager(this);

    private final ValueEditor<List<FormControlData>> delegateEditor;

    public FormFieldControlImpl(ValueEditorFactory<FormControlData> editorFactory, Repeatability repeatability) {
        initWidget(ourUiBinder.createAndBindUi(this));
        if(repeatability == Repeatability.REPEATABLE_HORIZONTALLY || repeatability == Repeatability.REPEATABLE_VERTICALLY) {
            ValueListFlexEditorImpl<FormControlData> delegate = new ValueListFlexEditorImpl<>(editorFactory);
            delegateEditor = new RepeatingEditor(delegate);
            if (repeatability == Repeatability.REPEATABLE_HORIZONTALLY) {
                delegate.setDirection(ValueListFlexEditorDirection.ROW);
            }
            else {
                delegate.setDirection(ValueListFlexEditorDirection.COLUMN);
            }
        }
        else {
            delegateEditor = new NonRepeatingEditor(editorFactory.createEditor());
        }
        editorHolder.setWidget(delegateEditor);
    }

    @Override
    public void requestFocus() {
        
    }

    @Override
    public void setValue(List<FormControlData> object) {
        delegateEditor.setValue(object);
    }

    @Override
    public void clearValue() {
        delegateEditor.clearValue();
    }

    @Override
    public Optional<List<FormControlData>> getValue() {
        return delegateEditor.getValue();
    }

    @Override
    public boolean isDirty() {
        return delegateEditor.isDirty();
    }

    @Override
    public HandlerRegistration addDirtyChangedHandler(DirtyChangedHandler handler) {
        return delegateEditor.addDirtyChangedHandler(handler);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Optional<List<FormControlData>>> handler) {
        return handlerManager.addHandler(ValueChangeEvent.getType(), handler);
    }

    @Override
    public boolean isWellFormed() {
        return delegateEditor.isWellFormed();
    }
}