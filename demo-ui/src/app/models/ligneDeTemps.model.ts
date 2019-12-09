import { Employe } from './Employe.model';

export class LigneDeTemps {
    constructor(public employe: Employe, public dateEntre: Date, public dateSortie: Date) {}
}