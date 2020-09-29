import {Injectable, Injector, TemplateRef} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { SpecmateDataService } from '../../../../data/modules/data-service/services/specmate-data.service';
import { TypedModalContent } from '../components/typed-modal-content.component';
import { Dialogtype } from '../modal-dialog-type';
import { ValidationService } from 'src/app/modules/forms/modules/validation/services/validation.service';
import { ModalService } from './modal-service';
import {NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {RGModelContainer} from '../../../../views/main/editors/modules/contents-container/components/rg-model-container.component';

@Injectable()
export class ConfirmationModal {
    constructor(private modalService: ModalService,
        private dataService: SpecmateDataService,
        private translate: TranslateService,
        private validator: ValidationService) { }

    public async openSave(message: string, withCancel = true): Promise<any> {
        const modalRef = this.modalService.open(TypedModalContent);
        await this.validator.validateCurrent();
        if (this.validator.isSavingEnabled()) {
            modalRef.componentInstance.options = Dialogtype.unsavedChangesDialog(message, withCancel);
        } else {
            let displayMessage = this.translate.instant('saveError.discard') + '\n' + this.validator.getValidationResultAsString(true);
            modalRef.componentInstance.options = Dialogtype.discardCancelDialog(displayMessage, withCancel);
        }
        return modalRef.result;
    }

    public confirmDelete(title: string, message: string): Promise<any> {
        const modalRef = this.modalService.open(TypedModalContent);
        modalRef.componentInstance.options = Dialogtype.okCancelDialog(title, message);
        return modalRef.result;
    }

    public openCustom(content: any, parent: any): Promise<any> {
        const modalRef = this.modalService.open(content);
        console.log(modalRef.componentInstance)
        modalRef.componentInstance.parent = parent
        modalRef.componentInstance.options = Dialogtype.okDialog('sdf', 'qwer');
        return modalRef.result;
    }

    public openOk(title: string, message: string): Promise<any> {
        const modalRef = this.modalService.open(TypedModalContent);
        modalRef.componentInstance.options = Dialogtype.okDialog(title, message);
        return modalRef.result;
    }

    public openOkCancel(title: string, message: string): Promise<any> {
        const modalRef = this.modalService.open(TypedModalContent);
        modalRef.componentInstance.options = Dialogtype.okCancelDialog(title, message);
        return modalRef.result;
    }

    public async confirmSave(message?: string): Promise<void> {
        if (this.dataService.hasCommits) {
            await this.validator.validateCurrent();
            if (this.validator.isSavingEnabled()) {
                return this.openOkCancel('ConfirmationRequired', message || this.translate.instant('confirmSave'));
            } else {
                let displayMessage = this.translate.instant('saveError.continue') + '\n' + this.validator.getValidationResultAsString(true);
                return this.openOk('saveError.title', displayMessage);
            }
        }
        return Promise.resolve();
    }
}
