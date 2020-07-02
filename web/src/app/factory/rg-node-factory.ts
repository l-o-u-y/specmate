import { Config } from '../config/config';
import { RGNode } from '../model/RGNode';
import { IContainer } from '../model/IContainer';
import { Id } from '../util/id';
import { Url } from '../util/url';
import { ElementFactoryBase } from './element-factory-base';
import { PositionableElementFactoryBase } from './positionable-element-factory-base';

export class RGNodeFactory extends PositionableElementFactoryBase<RGNode> {

    public create(parent: IContainer, commit: boolean, compoundId?: string, name?: string): Promise<RGNode> {

        compoundId = compoundId || Id.uuid;

        let id: string = Id.uuid;
        let url: string = Url.build([parent.url, id]);
        let node: RGNode = new RGNode();
        node.name = name || Config.RG_NEW_NODE_NAME + ' ' + ElementFactoryBase.getDateStr();
        node.description = Config.RG_NEW_NODE_DESCRIPTION;
        node.id = id;
        node.url = url;
        node.recycled = false;
        node.hasRecycledChildren = false;
        node.type = Config.RG_NODE_NEW_TYPE;
        node.x = this.coords.x;
        node.y = this.coords.y;
        node.tracesFrom = [];
        node.tracesTo = [];
        node.incomingConnections = [];
        node.outgoingConnections = [];

        return this.dataService.createElement(node, true, compoundId).then(() => node);
    }
}
