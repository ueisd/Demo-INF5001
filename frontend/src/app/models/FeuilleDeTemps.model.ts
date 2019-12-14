
export class FeuilleDeTemps {

    constructor() {}

    static getValidationMessagesRequete() {
        return {
            'setFillMax': [
                { type: 'required', message: 'Une heure est requise' },
                { type: 'min', message: 'Minimum 0 heures' },
                { type: 'max', message: 'Max de 24 heures' }
            ],
        };
    }
}