class Participant {

    constructor(name) {
        this.name = name;
        this.chatRoom = null;
        this.messages = [];
    }

    send(message, to) {
        this.chatRoom.send(message, this, to);
    }

    receive(message, from) {
        this.messages.push({message, from});
    }

    showMessage() {
        console.log(this.messages);
    }

}

class CharRoom {

    constructor() {
        this.participants = {};
    }

    enter(participant) {
        this.participants[participant.name] = participant;
        participant.chatRoom = this;
    }

    send(message, participant, to) {
        this.participants[to.name].receive(message, participant);
    }

}

const charRoom = new CharRoom();

const user1 = new Participant("user1");
const user2 = new Participant("user2");
const user3 = new Participant("user3");

charRoom.enter(user1);
charRoom.enter(user2);
charRoom.enter(user3);

console.log(charRoom);
console.log(user1);

user1.send("hi", user2);
console.log(charRoom.participants.user2.messages[0].from.name, charRoom.participants.user2.messages[0].message);