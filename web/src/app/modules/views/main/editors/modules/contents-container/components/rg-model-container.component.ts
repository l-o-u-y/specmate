import { Component, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { RGModelFactory } from '../../../../../../../factory/rg-model-factory';
import { ModelFactoryBase } from '../../../../../../../factory/model-factory-base';
import { RGModel } from '../../../../../../../model/RGModel';
import { IContainer } from '../../../../../../../model/IContainer';
import { Id } from '../../../../../../../util/id';
import { Type } from '../../../../../../../util/type';
import { SpecmateDataService } from '../../../../../../data/modules/data-service/services/specmate-data.service';
import { NavigatorService } from '../../../../../../navigation/modules/navigator/services/navigator.service';
import { ConfirmationModal } from '../../../../../../notification/modules/modals/services/confirmation-modal.service';
import { AdditionalInformationService } from '../../../../../side/modules/links-actions/services/additional-information.service';
import { ClipboardService } from '../../tool-pallette/services/clipboard-service';
import { TestSpecificationContentContainerBase } from '../base/testspecification-generatable-content-container-base';
import { ContentsContainerService } from '../services/content-container.service';
import {GraphicalEditorService} from '../../graphical-editor/services/graphical-editor.service';
import {ModelImageService} from '../../graphical-editor/services/model-image.service';

@Component({
    moduleId: module.id.toString(),
    selector: 'rg-model-container',
    templateUrl: 'rg-model-container.component.html',
    styleUrls: ['rg-model-container.component.css']
})
export class RGModelContainer extends TestSpecificationContentContainerBase<RGModel> {


    constructor(dataService: SpecmateDataService,
                navigator: NavigatorService,
                translate: TranslateService,
                modal: ConfirmationModal,
                clipboardService: ClipboardService,
                contentService: ContentsContainerService,
                additionalInformationService: AdditionalInformationService,
                graphicalEditorService: GraphicalEditorService,
                modelImageService: ModelImageService) {
        super(dataService, navigator, translate, modal,
            clipboardService, contentService, additionalInformationService, graphicalEditorService, modelImageService);
    }

    modelDescription: string;


    protected condition = (element: IContainer) => Type.is(element, RGModel);

    public async createElement(name: string): Promise<RGModel> {
        console.log(1234123412341234);
        const factory: ModelFactoryBase = new RGModelFactory(this.dataService);
        const element = await factory.create(this.parent, true, Id.uuid, name) as RGModel;

        let description = this.modelDescription;
        if (description == undefined) {
            description = '';
        }
        description = description.trim();

        if (description.length > 0) {
            element.modelRequirements = description;
            await this.dataService.updateElement(element, true, Id.uuid);
            await this.dataService.commit(this.translate.instant('save'));
            await this.dataService.performOperations(element.url, 'generateModel');
            await this.dataService.deleteCachedContent(element.url);
            await this.dataService.readElement(element.url, false);
            const content = await this.dataService.readContents(element.url, false);
            if (content.length == 0) {
                // Nothing was generated --> Delete the empty model
                await this.dataService.deleteElement(element.url, true, Id.uuid);
                await this.dataService.commit(this.translate.instant('save'));
                await this.dataService.deleteCachedContent(element.url);
                await this.modal.openOk(this.translate.instant('RGGenerator.couldNotGenerateTitle'),
                                        this.translate.instant('RGGenerator.couldNotGenerate'));
                return undefined;
            }
        }
        return element;
    }

    private readonly objectifIndicator = 'WENN';

    public isObjectif(): boolean {
        let desc = this.modelDescription;

        if (desc == undefined) {
            desc = '';
        }
        desc = desc.trim();

        if (desc.length <= 3) {
            return false;
        }
        const relevantLength = Math.min(this.objectifIndicator.length, desc.length);
        const cmpA = this.objectifIndicator.substring(0, relevantLength);
        const cmpB = desc.substring(0, relevantLength);
        return cmpA === cmpB;
    }
}
