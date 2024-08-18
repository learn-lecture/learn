function* generator() {
    yield 1;
    yield 2;
    yield 3;
}

const number = generator();

console.log(number);
console.log(number.next());
console.log(number.next());
console.log(number.next());
console.log(number.next());

// lazy Evaluation
function* createIds() {
    let id = 0;
    while (true) {
        yield id++;
    }
}

const ids = createIds();
console.log(ids.next().value);
console.log(ids.return(10).value); // 해당 값을 최종 값으로 반환하고 제너레이터를 종료함.
console.log(ids.next());

function* generationArr() {
    yield* [1, 2, 3];
}

for (const number of generationArr()) {
    console.log(number);
}
