import { ConverterBase } from './converter-base';
import { RGmxModelNode } from '../providers/properties/rg-mx-model-node';

export type ComponentAndModifier = { component: string, modifier: string, type: string };

export class ComponentToNameConverter extends ConverterBase<ComponentAndModifier, RGmxModelNode> {
    public convertTo(item: ComponentAndModifier): RGmxModelNode {
        if (item.component === undefined || item.modifier === undefined) {
            return name;
        }
        return new RGmxModelNode(item.component, item.modifier, item.type);
    }

    public convertFrom(value: RGmxModelNode, item: ComponentAndModifier): { component: string, modifier: string, type: string } {
        return {
            component: value.component,
            modifier: value.modifier,
            type: value.type
        };
    }
}
