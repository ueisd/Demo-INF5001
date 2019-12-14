import { Individu } from './Individu.model';

export class Employe {
    
    public id:number;
    public nom: string;
    public individu: Individu;
    public actif: boolean;


    constructor(
        public horaire: String,
        public tauxHoraire: Number,
        public heureSemaine: Number,
        public titrePoste: String
    ) {
    }

    static getValidationMessages() {
        return {
            'horaire': [
                { type: 'required', message: 'Une Nom est requis' },
                { type: 'minlength', message: 'Minimum 2 caractères' }
            ],
            'tauxHoraire': [
                { type: 'required', message: 'Un taux horaire est nécéssaire' },
                { type: 'min', message: 'Minimum 0 heures' }
            ],
            'heureSemaine': [
                { type: 'required', message: 'Une heure est requise' },
                { type: 'min', message: 'Minimum 0 heures' }
            ],
            'titrePoste': [
                { type: 'required', message: 'Un poste est requis' },
                { type: 'minlength', message: 'Minimum 5 caractères' }
            ]
        };
    }
}