import { Employe } from './Employe.model';
import { StatutLigne } from './EnumStatutLigne';

export class LigneDeTemps {
    id: number;
    constructor(public employe: Employe, public dateEntre: Date, public dateSortie: Date, public statut: StatutLigne ) {}
}