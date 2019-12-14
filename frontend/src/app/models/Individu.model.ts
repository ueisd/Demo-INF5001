import { Contact } from './Contact.model';
import { Employe } from './Employe.model';

export class Individu {
    
    public id:number;

    constructor(
        public nom: string,
        public prenom: string,
        public employe: Employe,
        public contact: Contact[],
        public ville: string,
    ) {
    }

    static getValidationMessages() {
        return {
            'prenom': [
                { type: 'required', message: 'Une Nom est requis' },
                { type: 'minlength', message: 'Minimum 3 caractères' }
            ],
            'nom': [
                { type: 'required', message: 'Un nom de famille complet est requis' },
                { type: 'minlength', message: 'Minimum 5 caractères' }
            ],
            'ville': [
                { type: 'required', message: 'Une ville est requise' },
                { type: 'minlength', message: 'Minimum 5 caractères' }
            ]
        };
    }
}