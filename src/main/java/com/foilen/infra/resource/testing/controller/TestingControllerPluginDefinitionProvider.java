/*
    Foilen Infra Resource Example
    https://github.com/foilen/foilen-infra-resource-testing-controller
    Copyright (c) 2018-2021 Foilen (http://foilen.com)

    The MIT License
    http://opensource.org/licenses/MIT

 */
package com.foilen.infra.resource.testing.controller;

import com.foilen.infra.plugin.v1.core.context.CommonServicesContext;
import com.foilen.infra.plugin.v1.core.context.internal.InternalServicesContext;
import com.foilen.infra.plugin.v1.core.plugin.IPPluginDefinitionProvider;
import com.foilen.infra.plugin.v1.core.plugin.IPPluginDefinitionV1;

/**
 * A plugin to test.
 */
public class TestingControllerPluginDefinitionProvider implements IPPluginDefinitionProvider {

    static private TestingControllerPluginDefinitionProvider instance;

    public static TestingControllerPluginDefinitionProvider getInstance() {
        return instance;
    }

    private TestingControllerInfiniteLoopChangesEventHandler testingControllerInfiniteLoopChangesEventHandler = new TestingControllerInfiniteLoopChangesEventHandler();
    private TestingControllerMockChangesEventHandler testingControllerMockChangesEventHandler = new TestingControllerMockChangesEventHandler();

    public TestingControllerPluginDefinitionProvider() {
        instance = this;
    }

    @Override
    public IPPluginDefinitionV1 getIPPluginDefinition() {

        IPPluginDefinitionV1 pluginDefinitionV1 = new IPPluginDefinitionV1("Foilen", "Testing Controller", "To do some specific tests", "1.0.0");
        pluginDefinitionV1.addChangesHandler(testingControllerInfiniteLoopChangesEventHandler);
        pluginDefinitionV1.addChangesHandler(testingControllerMockChangesEventHandler);
        return pluginDefinitionV1;
    }

    public TestingControllerInfiniteLoopChangesEventHandler getTestingControllerInfiniteLoopChangesEventHandler() {
        return testingControllerInfiniteLoopChangesEventHandler;
    }

    public TestingControllerMockChangesEventHandler getTestingControllerMockChangesEventHandler() {
        return testingControllerMockChangesEventHandler;
    }

    @Override
    public void initialize(CommonServicesContext commonServicesContext, InternalServicesContext internalServicesContext) {
    }

    public void setTestingControllerInfiniteLoopChangesEventHandler(TestingControllerInfiniteLoopChangesEventHandler testingControllerInfiniteLoopChangesEventHandler) {
        this.testingControllerInfiniteLoopChangesEventHandler = testingControllerInfiniteLoopChangesEventHandler;
    }

    public void setTestingControllerMockChangesEventHandler(TestingControllerMockChangesEventHandler testingControllerMockChangesEventHandler) {
        this.testingControllerMockChangesEventHandler = testingControllerMockChangesEventHandler;
    }

}
