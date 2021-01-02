/*
    Foilen Infra Resource Example
    https://github.com/foilen/foilen-infra-resource-testing-controller
    Copyright (c) 2018-2021 Foilen (https://foilen.com)

    The MIT License
    http://opensource.org/licenses/MIT

 */
package com.foilen.infra.resource.testing.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.foilen.infra.plugin.v1.core.context.CommonServicesContext;
import com.foilen.infra.plugin.v1.core.eventhandler.ActionHandler;
import com.foilen.infra.plugin.v1.core.eventhandler.ChangesEventHandler;
import com.foilen.infra.plugin.v1.core.eventhandler.changes.ChangesInTransactionContext;
import com.foilen.infra.plugin.v1.core.eventhandler.utils.ChangesEventHandlerUtils;
import com.foilen.infra.resource.example.JunitResource;

public class TestingControllerMockChangesEventHandler implements ChangesEventHandler {

    private List<String> added = new ArrayList<>();
    private List<String> updated = new ArrayList<>();
    private List<String> deleted = new ArrayList<>();

    public void clear() {
        added.clear();
        updated.clear();
        deleted.clear();
    }

    @Override
    public List<ActionHandler> computeActionsToExecute(CommonServicesContext services, ChangesInTransactionContext changesInTransactionContext) {

        ChangesEventHandlerUtils.getResourcesOfTypeStream(changesInTransactionContext.getLastAddedResources(), JunitResource.class) //
                .forEach(r -> added.add(r.getInternalId()));
        ChangesEventHandlerUtils.getNextResourcesOfTypeStream(changesInTransactionContext.getLastUpdatedResources(), JunitResource.class) //
                .forEach(r -> added.add(r.getNext().getInternalId()));
        ChangesEventHandlerUtils.getResourcesOfTypeStream(changesInTransactionContext.getLastDeletedResources(), JunitResource.class) //
                .forEach(r -> deleted.add(r.getInternalId()));

        return Collections.emptyList();
    }

    public List<String> getAdded() {
        return added;
    }

    public List<String> getDeleted() {
        return deleted;
    }

    public List<String> getUpdated() {
        return updated;
    }

}
