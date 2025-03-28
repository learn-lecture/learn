const appendTens = document.querySelector('#tens');
const appendSeconds = document.querySelector('#seconds');
const buttonStart = document.querySelector('#button-start');
const buttonStop = document.querySelector('#button-stop');
const buttonReset = document.querySelector('#button-reset');

let seconds = 0;
let tens = 0;
let interval;

buttonStart.onclick = function () {
    interval = setInterval(startTimer, 10);
}

buttonStop.onclick = function () {
    clearInterval(interval);
}

buttonReset.onclick = function () {
    clearInterval(interval);
    tens = 0;
    seconds = 0;
    appendTens.innerHTML = tens;
    appendSeconds.innerHTML = seconds;
}

function startTimer() {
    if (++tens > 99) {
        appendSeconds.innerHTML = ++seconds;
        tens = 0;
        appendTens.innerHTML = 0;
    } else {
        appendTens.innerHTML = tens;
    }
}