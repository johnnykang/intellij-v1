/*(c) Copyright 2008, VersionOne, Inc. All rights reserved. (c)*/
package com.versionone.integration.idea.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.versionone.common.sdk.PrimaryWorkitem;
import com.versionone.common.sdk.EntityType;
import com.versionone.common.sdk.DataLayerException;
import com.versionone.integration.idea.TasksComponent;
import org.apache.log4j.Logger;

import javax.swing.Icon;

/**
 * Creates in-memory Defect. New entity will be persisted when user triggers Save action.
 */
public class AddDefectAction extends AbstractAction {

    private static final Logger LOG = Logger.getLogger(AddDefectAction.class);

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project ideaProject = resolveProject(e);
        if (ideaProject != null && dataLayer.isConnected()) {
            TasksComponent tc = resolveTasksComponent(ideaProject);
            PrimaryWorkitem newItem = null;
            try {
                newItem = dataLayer.createNewPrimaryWorkitem(EntityType.Defect);
            } catch (DataLayerException ex) {
                LOG.warn("Failed to create new " + EntityType.Defect.name(), ex);
                Icon icon = Messages.getErrorIcon();
                Messages.showMessageDialog("Failed to create new " + EntityType.Defect.name(), "Error", icon);
            }

            tc.update();
            tc.selectNode(newItem);
        }
    }

    @Override
    public void update(AnActionEvent event) {
        Presentation presentation = event.getPresentation();
        presentation.setEnabled(dataLayer.isConnected() && getSettings().isEnabled);
    }
}
