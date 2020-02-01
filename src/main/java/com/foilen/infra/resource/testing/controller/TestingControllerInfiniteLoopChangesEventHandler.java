/*
    Foilen Infra Resource Example
    https://github.com/foilen/foilen-infra-resource-testing-controller
    Copyright (c) 2018-2020 Foilen (http://foilen.com)

    The MIT License
    http://opensource.org/licenses/MIT

 */
package com.foilen.infra.resource.testing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.foilen.infra.plugin.v1.core.context.CommonServicesContext;
import com.foilen.infra.plugin.v1.core.eventhandler.ActionHandler;
import com.foilen.infra.plugin.v1.core.eventhandler.ChangesEventHandler;
import com.foilen.infra.plugin.v1.core.eventhandler.changes.ChangesInTransactionContext;
import com.foilen.infra.resource.example.JunitResource;
import com.foilen.smalltools.tools.SecureRandomTools;

public class TestingControllerInfiniteLoopChangesEventHandler implements ChangesEventHandler {

    private boolean alwaysUpdate = false;

    @Override
    public List<ActionHandler> computeActionsToExecute(CommonServicesContext services, ChangesInTransactionContext changesInTransactionContext) {

        List<ActionHandler> actions = new ArrayList<>();

        if (alwaysUpdate) {

            actions.add((s, changes) -> {
                List<JunitResource> junitResources = services.getResourceService().resourceFindAll(services.getResourceService().createResourceQuery(JunitResource.class));
                if (junitResources.size() < 3) {
                    for (int i = 0; i < 5; ++i) {
                        changes.resourceAdd(new JunitResource(SecureRandomTools.randomHexString(10)));
                    }
                } else {
                    JunitResource update = junitResources.get(0);
                    JunitResource delete = junitResources.get(2);

                    update.setDate(new Date());
                    changes.resourceUpdate(update);

                    changes.resourceDelete(delete);
                }
            });
        }

        return actions;

    }

    public boolean isAlwaysUpdate() {
        return alwaysUpdate;
    }

    public void setAlwaysUpdate(boolean alwaysUpdate) {
        this.alwaysUpdate = alwaysUpdate;
    }

}
