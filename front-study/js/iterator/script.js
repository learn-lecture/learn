function makerIterator(numbers) {
    let nextIndex = 0;

    return {
        next: function() {
            return nextIndex < numbers.length ?
                { value: numbers[nextIndex++], done: false } :
                { value: undefined, done: true};
        }
    }
}

const numbersArray = [1, 2, 3];
const numbersIterator = makerIterator(numbersArray);

console.log(numbersIterator.next());
console.log(numbersIterator.next());
console.log(numbersIterator.next());
console.log(numbersIterator.next());

const symbolIterator = numbersArray[Symbol.iterator]();
console.log(symbolIterator.next());
console.log(symbolIterator.next());
console.log(symbolIterator.next());
console.log(symbolIterator.next());
