import { ProviderBase } from '../properties/provider-base';
import { ConverterBase } from '../../converters/converter-base';
import { VariableConditionToNameConverter } from '../../converters/variable-condition-name-converter';
import { ComponentToNameConverter } from '../../converters/component-to-name-converter';
import { ProcessNodeToNameConverter } from '../../converters/process-node-to-name-converter';
import { CEGmxModelNode } from '../properties/ceg-mx-model-node';
import {RGmxModelNode} from '../properties/rg-mx-model-node';

export class NodeNameConverterProvider extends ProviderBase {
    public get nodeNameConverter(): ConverterBase<any, string | CEGmxModelNode |RGmxModelNode> {
        if (this.isCEGModel) {
            return new VariableConditionToNameConverter();
        } if (this.isRGModel) {
            return new ComponentToNameConverter();
        } else if (this.isProcessModel) {
            return new ProcessNodeToNameConverter();
        }
    }
}
