import { mxgraph } from 'mxgraph';
import { StyleChanger } from '../util/style-changer';
import { EditorStyle } from './editor-style';
import { replaceClass } from '../util/css-utils';
import { TranslateService } from '@ngx-translate/core';
import {RGConnection} from '../../../../../../../../model/RGConnection';
import {RGConnectionType} from '../../../../../../../../model/RGConnectionType';
import {CEGConnection} from '../../../../../../../../model/CEGConnection';
import {IContainer} from '../../../../../../../../model/IContainer';
import {Type} from '../../../../../../../../util/type';

declare var require: any;

const mx: typeof mxgraph = require('mxgraph')({
    mxBasePath: 'mxgraph'
});


export class EditorPopup {

    constructor(private graph: mxgraph.mxGraph, private contents: IContainer[], private translate: TranslateService) { }

    public init(): void {

        this.graph.popupMenuHandler['autoExpand'] = true;

        this.graph.popupMenuHandler.isSelectOnPopup = function (me) {
            return mx.mxEvent.isMouseEvent(me.getEvent());
        };

        // Installs context menu
        this.graph.popupMenuHandler['factoryMethod'] = this.provideMenu.bind(this);
    }

    private async provideMenu(menu: mxgraph.mxPopupMenuHandler, cell: mxgraph.mxCell, evt: PointerEvent) {

        if (cell === undefined || cell === null) {
            return;
        }

        let element: IContainer = undefined;
        let currentCell = cell;
        while (element === undefined) {
            element = this.contents.find(element => element.url === currentCell.id);
            currentCell = currentCell.parent;
        }

        currentCell = this.graph.getModel().getChildCells(this.graph.getDefaultParent()).find(graphCell => graphCell.id === element.url);

        const deleteText = this.translate.instant('delete');
        menu.addItem(deleteText, null, () => {
            this.graph.removeCells([currentCell]);
        }, undefined, 'fa fa-trash-o', undefined, undefined);

        if (Type.is(element, CEGConnection)) {

            const connection = element as CEGConnection;
            const icon = connection.negate ? 'fa fa-check' : 'fa fa-circle-o';

            const negateText = this.translate.instant('negated');
            menu.addItem(negateText, null, async () => {
                this.graph.getModel().beginUpdate();
                if (connection.negate) {
                    StyleChanger.removeStyle(cell, this.graph, EditorStyle.CEG_CONNECTION_NEGATED_STYLE);
                } else {
                    StyleChanger.addStyle(cell, this.graph, EditorStyle.CEG_CONNECTION_NEGATED_STYLE);
                }
                this.graph.getModel().endUpdate();
            }, undefined, icon, undefined, undefined);
        } else if (Type.is(element, RGConnection)) {
            const connection = element as RGConnection;
            const icon = connection.negate ? 'fa fa-check' : 'fa fa-circle-o';

            const negateText = this.translate.instant('negated');
            menu.addItem(negateText, null, async () => {
                this.graph.getModel().beginUpdate();
                if (connection.negate) {
                    StyleChanger.removeStyle(cell, this.graph, EditorStyle.RG_CONNECTION_NEGATED_STYLE);
                } else {
                    StyleChanger.addStyle(cell, this.graph, EditorStyle.RG_CONNECTION_NEGATED_STYLE);
                }
                StyleChanger.removeStyle(cell, this.graph, EditorStyle.RG_CONNECTION_COMPOSITION_STYLE);
                StyleChanger.removeStyle(cell, this.graph, EditorStyle.RG_CONNECTION_INHERITANCE_STYLE);
                StyleChanger.removeStyle(cell, this.graph, EditorStyle.RG_CONNECTION_ACTION_STYLE);
                StyleChanger.removeStyle(cell, this.graph, EditorStyle.RG_CONNECTION_CONDITION_STYLE);

                if ((element as RGConnection).type == 'Composition') {
                    StyleChanger.addStyle(cell, this.graph, EditorStyle.RG_CONNECTION_COMPOSITION_STYLE);
                } else if ((element as RGConnection).type == 'Inheritance') {
                    StyleChanger.addStyle(cell, this.graph, EditorStyle.RG_CONNECTION_INHERITANCE_STYLE);
                } else if ((element as RGConnection).type == 'Action') {
                    StyleChanger.addStyle(cell, this.graph, EditorStyle.RG_CONNECTION_ACTION_STYLE);
                } else if ((element as RGConnection).type == 'Condition') {
                    StyleChanger.addStyle(cell, this.graph, EditorStyle.RG_CONNECTION_CONDITION_STYLE);
                }
                this.graph.getModel().endUpdate();
            }, undefined, icon, undefined, undefined);
        }
    }
}
