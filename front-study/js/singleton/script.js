let instance;

class Counter {
    constructor() {
        if (instance) {
            return instance;
        }
        this.counter = 0;
        instance = this;
    }

    getCount() {
        return this.counter;
    }

    increment() {
        this.counter++;
    }

    decrement() {
        this.counter--;
    }

}

const testInstance = new Counter();
testInstance.increment();
testInstance.increment();

console.log(testInstance.getCount());

const testInstance2 = new Counter();
console.log(testInstance2.getCount());