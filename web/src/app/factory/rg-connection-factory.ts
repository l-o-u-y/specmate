import { Config } from '../config/config';
import { RGConnection } from '../model/RGConnection';
import { IContainer } from '../model/IContainer';
import { IModelNode } from '../model/IModelNode';
import { Proxy } from '../model/support/proxy';
import { SpecmateDataService } from '../modules/data/modules/data-service/services/specmate-data.service';
import { Id } from '../util/id';
import { Url } from '../util/url';
import { ConnectionElementFactoryBase } from './connection-element-factory-base';
import { ElementFactoryBase } from './element-factory-base';

export class RGConnectionFactory extends ConnectionElementFactoryBase<RGConnection> {

    constructor(protected source: IModelNode, protected target: IModelNode, dataService: SpecmateDataService, private negated = false, private type = 'Inheritance') {
        super(source, target, dataService);
    }

    public create(parent: IContainer, commit: boolean, compoundId?: string, name?: string): Promise<RGConnection> {
        compoundId = compoundId || Id.uuid;

        let id: string = Id.uuid;
        let url: string = Url.build([parent.url, id]);
        let connection: RGConnection = new RGConnection();
        connection.name = name || Config.RG_NEW_CONNECTION_NAME + ' ' + ElementFactoryBase.getDateStr();
        connection.description = Config.RG_NEW_CONNECTION_DESCRIPTION;
        connection.id = id;
        connection.url = url;
        connection.recycled = false;
        connection.hasRecycledChildren = false;
        connection.negate = this.negated;
        connection.type = this.type;
        connection.source = new Proxy();
        connection.source.url = this.source.url;
        connection.target = new Proxy();
        connection.target.url = this.target.url;
        connection.tracesFrom = [];
        connection.tracesTo = [];
        let proxy: Proxy = new Proxy();
        proxy.url = connection.url;
        if (!this.source.outgoingConnections) {
            this.source.outgoingConnections = [];
        }
        if (!this.target.incomingConnections) {
            this.target.incomingConnections = [];
        }
        this.source.outgoingConnections.push(proxy);
        this.target.incomingConnections.push(proxy);

        return this.dataService.createElement(connection, true, compoundId)
            .then(() => this.dataService.updateElement(this.source, true, compoundId))
            .then(() => this.dataService.updateElement(this.target, true, compoundId))
            .then(() => connection);
    }

}
