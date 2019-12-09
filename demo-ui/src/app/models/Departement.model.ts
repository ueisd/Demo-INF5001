import { Employe } from './Employe.model';

export class Departement {
    
    public id: Number;
    public journesOuvert: boolean[];


    constructor(
        public heure_Ouverture: Number,
        public heure_Fermeture: Number,
        public employes: Employe[]
    ) {
    }

    static getValidationMessages() {
        return {
            'heure_Ouverture': [
                { type: 'required', message: 'Unge Nom est requis' },
                { type: 'max', message: 'Maximum 24' },
                { type: 'min', message: 'Minimum 0' }
            ],
            'heure_Fermeture': [
                { type: 'required', message: 'Un taux horaire est nécéssaire' },
                { type: 'max', message: 'Maximum 24' },
                { type: 'min', message: 'Minimum 0' }
            ]
        };
    }

    public static getJoursSemaine() {
        return [
            {formControlName: "dimancheOuvert", label: "Dimanche"},
            {formControlName: "lundiOuvert",    label: "Lundi"},
            {formControlName: "mardiOuvert",    label: "Mardi"},
            {formControlName: "mercrediOuvert", label: "Mercredi"},
            {formControlName: "jeudiOuvert",    label: "Jeudi"},
            {formControlName: "vendrediOuvert", label: "Vendredi"},
            {formControlName: "samediOuvert",   label: "Samedi"},
        ];
    }
}