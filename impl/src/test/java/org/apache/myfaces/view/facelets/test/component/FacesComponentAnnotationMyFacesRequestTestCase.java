/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.myfaces.view.facelets.test.component;

import javax.el.ExpressionFactory;
import javax.faces.application.StateManager;
import javax.faces.component.UIComponent;
import junit.framework.Assert;

import org.apache.myfaces.mc.test.core.AbstractMyFacesRequestTestCase;
import org.apache.myfaces.shared.config.MyfacesConfig;
import org.junit.Test;

public class FacesComponentAnnotationMyFacesRequestTestCase extends AbstractMyFacesRequestTestCase
{

    @Override
    protected boolean isScanAnnotations()
    {
        return true;
    }

    @Override
    protected void setUpWebConfigParams() throws Exception
    {
        super.setUpWebConfigParams();
        servletContext.addInitParameter("org.apache.myfaces.annotation.SCAN_PACKAGES","org.apache.myfaces.view.facelets.test.component");
        servletContext.addInitParameter(StateManager.STATE_SAVING_METHOD_PARAM_NAME, StateManager.STATE_SAVING_METHOD_CLIENT);
        servletContext.addInitParameter("javax.faces.PARTIAL_STATE_SAVING", "true");
        servletContext.addInitParameter(MyfacesConfig.INIT_PARAM_REFRESH_TRANSIENT_BUILD_ON_PSS, "auto");
    }
    
    protected ExpressionFactory createExpressionFactory()
    {
        return new org.apache.el.ExpressionFactoryImpl();
    }    
    
    @Test
    public void testUIPanel1() throws Exception
    {
        setupRequest("/testMyUIPanel1.xhtml");
        processLifecycleExecuteAndRender();
        
        UIComponent comp = facesContext.getViewRoot().findComponent("panel1");
        Assert.assertNotNull(comp);
        Assert.assertTrue(comp instanceof MyUIPanel1);
        
        tearDownRequest();
    }    

    @Test
    public void testUIPanel2() throws Exception
    {
        setupRequest("/testMyUIPanel2.xhtml");
        processLifecycleExecuteAndRender();
        
        UIComponent comp = facesContext.getViewRoot().findComponent("panel1");
        Assert.assertNotNull(comp);
        Assert.assertTrue(comp instanceof MyUIPanel2);
        
        tearDownRequest();
    }
    
    @Test
    public void testUIPanel3() throws Exception
    {
        setupRequest("/testMyUIPanel3.xhtml");
        processLifecycleExecuteAndRender();
        
        UIComponent comp = facesContext.getViewRoot().findComponent("panel3");
        Assert.assertNotNull(comp);
        Assert.assertTrue(comp instanceof MyUIPanel3);

        // Check component type
        MyUIPanel3 comp2 = (MyUIPanel3) 
            facesContext.getApplication().createComponent("myUIPanel3");
        Assert.assertNotNull(comp2);
        
        tearDownRequest();
    }
}
