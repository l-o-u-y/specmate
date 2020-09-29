import { ConverterBase } from './converter-base';
import { RGmxModelNode } from '../providers/properties/rg-mx-model-node';

export type Component = { component: string, type: string };

export class ComponentToNameConverter extends ConverterBase<Component, RGmxModelNode> {
    public convertTo(item: Component): RGmxModelNode {
        if (item.component === undefined) {
            return name;
        }
        return new RGmxModelNode(item.component, item.type);
    }

    public convertFrom(value: RGmxModelNode, item: Component): { component: string, type: string } {
        return {
            component: value.component,
            type: value.type
        };
    }
}
