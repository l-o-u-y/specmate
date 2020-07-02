import { NgModule } from '@angular/core';
import { ContradictoryCondidionValidator } from './ceg/contradictory-condition-validator';
import { DuplicateIOVariableValidator } from './ceg/duplicate-io-variable-validator';
import { DuplicateNodeValidator as CEGDuplicateNodeValidator } from './ceg/duplicate-node-validator';
import { EmptyModelValidator as CEGEmptyModelValidator } from './ceg/empty-model-validator';
import { NodeCycleValidator as CEGNodeCycleValidator } from './ceg/node-cycle-validator';
import { SingleNodesValidator as CEGSingleNodesValidator } from './ceg/single-nodes-validator';
import { DuplicateComponentValidator } from './rg/duplicate-component-validator';
import { DuplicateNodeValidator as RGDuplicateNodeValidator } from './rg/duplicate-node-validator';
import { EmptyModelValidator as RGEmptyModelValidator } from './rg/empty-model-validator';
import { InvalidNodeComponentValidator } from './rg/invalid-node-component-validator';
import { NodeCycleValidator as RGNodeCycleValidator } from './rg/node-cycle-validator';
import { SingleNodesValidator as RGSingleNodesValidator } from './rg/single-nodes-validator';
import { DecisionMultipleOutgoingConnectionsValidator } from './process/decision-multiple-outgoing-connections-validator';
import { EndNodeNoOutgoingConnectionValidator } from './process/end-node-no-outgoing-connection-validator';
import { EndNodeValidator } from './process/end-node-validator';
import { HasStepsValidator } from './process/has-steps-validator';
import { MissingConditionValidator } from './process/missing-condition-validator';
import { NodeNoIncomingValidator } from './process/node-no-incoming-validator';
import { NodeNoOutgoingValidator } from './process/node-no-outgoing-validator';
import { NodeSingleOutgoingConnectionValidator } from './process/node-single-outgoing-connection-validator';
import { StartNodeNoIncomingConnectionValidator } from './process/start-node-no-incoming-connection-validator';
import { StartNodeValidator } from './process/start-node-validator';
import { InvalidNodeVariableValidator } from './ceg/invalid-node-variable-validator';
import { InvalidConditionTextValidator } from './process/invalid-condition-text';

@NgModule({
  imports: [
    // MODULE IMPORTS
  ],
  declarations: [
    // COMPONENTS IN THIS MODULE
  ],
  exports: [
    // THE COMPONENTS VISIBLE TO THE OUTSIDE
  ],
  providers: [
    // SERVICES (IN THIS CASE: VALIDATORS)
    DuplicateComponentValidator,
    InvalidNodeComponentValidator,
    CEGDuplicateNodeValidator,
    RGDuplicateNodeValidator,
    ContradictoryCondidionValidator,
    CEGNodeCycleValidator,
    RGNodeCycleValidator,
    DuplicateIOVariableValidator,
    CEGEmptyModelValidator,
    CEGSingleNodesValidator,
    RGEmptyModelValidator,
    RGSingleNodesValidator,
    EndNodeValidator,
    EndNodeNoOutgoingConnectionValidator,
    HasStepsValidator,
    NodeSingleOutgoingConnectionValidator,
    MissingConditionValidator,
    NodeNoIncomingValidator,
    NodeNoOutgoingValidator,
    StartNodeValidator,
    StartNodeNoIncomingConnectionValidator,
    DecisionMultipleOutgoingConnectionsValidator,
    InvalidNodeVariableValidator,
    InvalidConditionTextValidator
  ],
  bootstrap: [
    // COMPONENTS THAT ARE BOOTSTRAPPED HERE
  ]
})
export class ValidationModule { }
