export class AppareilService {
    appareils = [
        {
            name: "AAAMachine à laver 1",
            status: "allumé"
        },
        {
            name: "Télévision",
            status: "éteint"
        },
        {
            name: "Ordinateur",
            status: "éteint"
        },
    ];

    SwitchOnAll() {
        for(let appareil of this.appareils) {
            appareil.status = "allumé";
        }
    }

    SwitchOffAll() {
        for(let appareil of this.appareils) {
            appareil.status = "éteint";
        }
    }

    switchOnOne(index: number) {
        this.appareils[index].status = "allumé";
    }

    switchOffOne(index: number) {
        this.appareils[index].status = "éteint";
    }

}