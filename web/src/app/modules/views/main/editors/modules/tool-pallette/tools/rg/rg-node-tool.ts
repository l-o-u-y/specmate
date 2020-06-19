import { RGNodeFactory } from '../../../../../../../../factory/rg-node-factory';
import { ElementFactoryBase } from '../../../../../../../../factory/element-factory-base';
import { RGModel } from '../../../../../../../../model/RGModel';
import { RGNode } from '../../../../../../../../model/RGNode';
import { CreateNodeToolBase } from '../create-node-tool-base';
import { ShapeProvider } from '../../../graphical-editor/providers/properties/shape-provider';

export class RGNodeTool extends CreateNodeToolBase<RGNode> {

    protected modelType: { className: string; } = RGModel;

    public icon = 'plus';
    public name = 'tools.addRgNode';
    public style = new ShapeProvider(RGModel).getStyle(RGNode);

    protected getElementFactory(coords: { x: number; y: number; }): ElementFactoryBase<RGNode> {
        return new RGNodeFactory(coords, this.dataService);
    }
}
