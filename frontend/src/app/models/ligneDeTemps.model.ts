import { Employe } from './Employe.model';
import { StatutLigne } from './EnumStatutLigne';

export class LigneDeTemps {
    constructor(public employe: Employe, public dateEntre: Date, public dateSortie: Date, public statut: StatutLigne ) {}
}