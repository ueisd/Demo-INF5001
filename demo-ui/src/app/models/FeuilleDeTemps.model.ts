
export class FeuilleDeTemps {

    constructor() {}

    static getValidationMessagesRequete() {
        return {
            'nbrSemaines': [
                { type: 'required', message: 'Une heure est requise' },
                { type: 'min', message: 'Minimum 0 semaines' },
                { type: 'max', message: 'Max de 14 semaines' }
            ],
        };
    }
}