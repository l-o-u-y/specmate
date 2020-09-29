import { CEGConnection } from '../../../../../../../../../app/model/CEGConnection';
import { Type } from '../../../../../../../../../app/util/type';
import { Config } from '../../../../../../../../config/config';
import { ProcessDecision } from '../../../../../../../../model/ProcessDecision';
import { ProcessEnd } from '../../../../../../../../model/ProcessEnd';
import { ProcessStart } from '../../../../../../../../model/ProcessStart';
import { ProcessStep } from '../../../../../../../../model/ProcessStep';
import { EditorStyle } from '../../components/editor-components/editor-style';
import { NodeNameConverterProvider } from '../conversion/node-name-converter-provider';
import { CEGmxModelNode } from './ceg-mx-model-node';
import { ProviderBase } from './provider-base';
import { RGNode } from '../../../../../../../../model/RGNode';
import { CEGNode } from '../../../../../../../../model/CEGNode';
import { RGmxModelNode } from './rg-mx-model-node';
import { RGConnection } from '../../../../../../../../model/RGConnection';
import {RGConnectionType} from '../../../../../../../../model/RGConnectionType';

export type ShapeData = {
    style: string,
    size: { width: number, height: number },
    text: string | CEGmxModelNode | RGmxModelNode
};

export class ShapeProvider extends ProviderBase {

    private shapeMap: { [className: string]: ShapeData } = {};
    private styles: ((element: { className: string }) => ShapeData)[] = [];

    constructor(type: { className: string }) {
        super(type);

        this.shapeMap[RGNode.className] = {
            style: EditorStyle.BASE_RG_NODE_STYLE,
            size: {
                width: Config.RG_NODE_WIDTH,
                height: Config.RG_NODE_HEIGHT
            },
            text: new NodeNameConverterProvider(type).nodeNameConverter.convertTo({
            component: Config.RG_NODE_NEW_COMPONENT
        })
        };
        this.shapeMap[CEGNode.className] = {
            style: EditorStyle.BASE_CEG_NODE_STYLE,
            size: {
                width: Config.CEG_NODE_WIDTH,
                height: Config.CEG_NODE_HEIGHT
            },
            text: new NodeNameConverterProvider(type).nodeNameConverter.convertTo({
                variable: Config.CEG_NODE_NEW_VARIABLE,
                condition: Config.CEG_NODE_NEW_CONDITION
            })
        };

        this.shapeMap[ProcessStart.className] = {
            style: EditorStyle.BASE_PROCESS_START_STYLE,
            size: {
                width: Config.PROCESS_START_END_NODE_RADIUS * 2,
                height: Config.PROCESS_START_END_NODE_RADIUS * 2
            },
            text: new NodeNameConverterProvider(type).nodeNameConverter.convertTo({
                name: 'Start'
            })
        };

        this.shapeMap[ProcessEnd.className] = {
            style: EditorStyle.BASE_PROCESS_END_STYLE,
            size: {
                width: Config.PROCESS_START_END_NODE_RADIUS * 2,
                height: Config.PROCESS_START_END_NODE_RADIUS * 2
            },
            text: new NodeNameConverterProvider(type).nodeNameConverter.convertTo({
                name: 'End'
            })
        };

        this.shapeMap[ProcessStep.className] = {
            style: EditorStyle.BASE_PROCESS_STEP_STYLE,
            size: {
                width: Config.CEG_NODE_WIDTH,
                height: Config.CEG_NODE_HEIGHT
            },
            text: new NodeNameConverterProvider(type).nodeNameConverter.convertTo({
                name: Config.PROCESS_NEW_STEP_NAME
            })
        };

        this.shapeMap[ProcessDecision.className] = {
            style: EditorStyle.BASE_PROCESS_DECISION_STYLE,
            size: {
                width: Config.PROCESS_DECISION_NODE_DIM,
                height: Config.PROCESS_DECISION_NODE_DIM
            },
            text: new NodeNameConverterProvider(type).nodeNameConverter.convertTo({
                name: Config.PROCESS_NEW_DECISION_NAME
            })
        };

        this.styles.push((element: { className: string }) => this.shapeMap[element.className]);

        this.styles.push((element: { className: string }) => {
            if (Type.is(element, RGConnection)) {
                if ((element as RGConnection).type == 'Composition') {
                    return {
                        size: undefined,
                        style: EditorStyle.RG_CONNECTION_COMPOSITION_STYLE,
                        text: undefined
                    };
                } else if ((element as RGConnection).type == 'Inheritance') {
                    return {
                        size: undefined,
                        style: EditorStyle.RG_CONNECTION_INHERITANCE_STYLE,
                        text: undefined
                    };
                } else if ((element as RGConnection).type == 'Action') {
                    return {
                        size: undefined,
                        style: EditorStyle.RG_CONNECTION_ACTION_STYLE,
                        text: undefined
                    };
                }
            }
        });

        this.styles.push((element: { className: string }) => {
            if (Type.is(element, CEGConnection) && (element as CEGConnection).negate === true) {
                return {
                    size: undefined,
                    style: EditorStyle.CEG_CONNECTION_NEGATED_STYLE,
                    text: undefined
                };
            } else if (Type.is(element, RGConnection) && (element as RGConnection).negate === true) {
                return {
                    size: undefined,
                    style: EditorStyle.RG_CONNECTION_NEGATED_STYLE,
                    text: undefined
                };
            }
        });
    }

    private getShapeData(element: { className: string }): ShapeData[] {
        return this.styles.map(fn => fn(element)).filter(shapeData => shapeData !== undefined);
    }

    public getStyle(element: { className: string }): string {
        return this.getShapeData(element).map(shapeData => shapeData.style).join(';');
    }

    public getInitialSize(element: { className: string }): { width: number, height: number } {
        return this.getShapeData(element).find(shapeData => shapeData.size !== undefined).size;
    }

    public getInitialText(element: { className: string }): string | CEGmxModelNode | RGmxModelNode {
        return this.getShapeData(element).find(shapeData => shapeData.text !== undefined).text;
    }

    public getInitialData(style: string): ShapeData {
        for (const className in this.shapeMap) {
            if (this.shapeMap[className].style === style) {
                return this.shapeMap[className];
            }
        }
        return undefined;
    }
}
