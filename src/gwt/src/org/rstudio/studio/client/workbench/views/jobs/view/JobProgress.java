/*
 * JobProgress.java
 *
 * Copyright (C) 2009-18 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.workbench.views.jobs.view;

import org.rstudio.core.client.StringUtil;
import org.rstudio.studio.client.workbench.views.jobs.JobProgressPresenter;
import org.rstudio.studio.client.workbench.views.jobs.model.GlobalJobProgress;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class JobProgress extends Composite
                         implements JobProgressPresenter.Display
{

   private static JobProgressUiBinder uiBinder = GWT.create(JobProgressUiBinder.class);

   interface JobProgressUiBinder extends UiBinder<Widget, JobProgress>
   {
   }

   public JobProgress()
   {
      initWidget(uiBinder.createAndBindUi(this));
   }
   
   @Override
   public void showProgress(GlobalJobProgress progress)
   {
      name_.setText(progress.name());
      progress_.setText(progress.units() + "/" + progress.max());
      started_ = progress.started();
   }

   @Override
   public void updateElapsed(int timestamp)
   {
      // TODO: this should not be diffing times from the client and the server
      elapsed_.setText(StringUtil.conciseElaspedTime(timestamp - started_));
   }

   @UiField Label name_;
   @UiField Label progress_;
   @UiField Label elapsed_;
   
   private int started_;
}